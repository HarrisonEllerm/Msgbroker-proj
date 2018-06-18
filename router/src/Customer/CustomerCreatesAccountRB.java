/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import converters.CustomerConverter;
import domain.CustomerAccount;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 *
 * @author ellha629
 */
public class CustomerCreatesAccountRB extends RouteBuilder {
    
    private Config conf = new Config();

    @Override
    public void configure() throws Exception {
        //CREATE HTTP ENDPOINT      
        from(conf.getJettyCustEndpoint())
                .setExchangePattern(ExchangePattern.InOnly)
                .to("jms:queue:jetty-queue");

        //MULTICAST
        from("jms:queue:jetty-queue")
                .multicast()
                .to("jms:queue:customer-service", "jms:queue:json-conversion-to-object-queue");

        //SEND TO CUSTOMER ACCOUNTS SERVICE 
        from("jms:queue:customer-service")
                .removeHeaders("*")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to(conf.getCustServPath());

        //CONVERT JSON TO CUSTOMER ACCOUNT OBJECT
        from("jms:queue:json-conversion-to-object-queue")
                .unmarshal().json(JsonLibrary.Gson, CustomerAccount.class)
                .to("jms:queue:customer-account-queue");
        
        //CONVERT CUSTOMER ACCOUNT OBJECT TO CUSTOMER OBJECT
        from("jms:queue:customer-account-queue")
                .removeHeaders("*")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .bean(CustomerConverter.class,
                        "createCustomer(${body.firstName},${body.lastName}"
                                + ",${body.email}, ${body.userName})")
                .to("jms:queue:customer-account-object-queue");
        
        //CONVERT CUSTOMER OBJECT BACK TO JSON TO FIRE AT VEND
        from("jms:queue:customer-account-object-queue")
                .marshal().json(JsonLibrary.Gson)
                .to("jms:queue:vend-customer-json");
        
        //FIRE AT VEND
        from("jms:queue:vend-customer-json")
                .removeHeaders("*")
                .setHeader("Authorization", constant(conf.getVendAuth()))
                .setHeader(Exchange.CONTENT_TYPE).constant("application/json")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .to(conf.getVendCustPath())
                .to("jms:queue:vend-createcustomer-response");

    }

}
