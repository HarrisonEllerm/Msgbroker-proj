/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import converters.CSCustomerBean;
import converters.VendCustomerBean;
import converters.VendSummaryBean;
import domain.Sale;
import domain.Summary;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 *
 * @author harrysmac
 */
public class CustomerMakesASaleRB extends RouteBuilder {
    
    private Config conf = new Config();
    
    @Override
    public void configure() throws Exception {
        from("imaps://outlook.office365.com?username=" + conf.getEmail() 
                + "&password=" + getPassword("Enter your E-Mail password")
                + "&searchTerm.subject=Vend:SaleUpdate"
                + "&debugMode=false" 
                + "&folderName=INBOX") 
                .convertBodyTo(String.class)
                .to("jms:queue:vend-new-sale");

        //Convert JSON from Vend into sale object
        from("jms:queue:vend-new-sale")
                .unmarshal().json(JsonLibrary.Gson, Sale.class)
                .setProperty("customer_group_id").simple("${body.cust.groupID}")
                .setProperty("customer_id").simple("${body.cust.id}")
                .setProperty("customer_first_name").simple("${body.cust.firstName}")
                .setProperty("customer_last_name").simple("${body.cust.lastName}")
                .setProperty("customer_email").simple("${body.cust.email}")
                .setProperty("customer_username").simple("${body.cust.customField1}")
                .log("debug1")
                .log("${body}")
                .to("jms:queue:sale-object-queue");

        //Covert sale object back to JSON to send to sales service
        from("jms:queue:sale-object-queue")
                .marshal().json(JsonLibrary.Gson)
                .to("jms:queue:vend-customer-json");

        //Send to sales service
        from("jms:queue:vend-customer-json")
                .removeHeaders("*")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to(conf.getSaleServPath())
                .to("jms:queue:sales-service-create-sale-response");

        //Query sales service for customer sales summary, using the response
        //from the post to Sales Service as the prompt.
        from("jms:queue:sales-service-create-sale-response")
                .removeHeaders("*") // remove headers to stop them being sent to the service
                .setBody(constant(null)) // can't pass a body in a GET request
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .recipientList() // used when recipients uri needs to be generated dynamically
                .simple(conf.getSaleSumServPath()+"/${exchangeProperty.customer_id}")
                .to("jms:queue:sales-summary");

        //Convert from JSON into summary object that contains Vend group
        from("jms:queue:sales-summary")
                .unmarshal().json(JsonLibrary.Gson, Summary.class)
                .bean(VendSummaryBean.class,
                        "getVendSummary(${body.numberOfSales}, ${body.totalPayment}, ${body.group})")
                .to("jms:queue:vend-summary-obj");

        //If the customer group has changed update both VEND REST sevice and Customer Account Service
        from("jms:queue:vend-summary-obj")
                .choice()
                .when().simple("${body.group} != ${exchangeProperty.customer_group_id}")
                    .setProperty("customer_updated_group_id").simple("${body.group}")
                    .log("Summary detected changes, updating customer group")
                    .to("jms:queue:customer-group-changed-queue")
                .otherwise()
                    .log("Summary did not detect any changes, not updating customer group")
                    .to("jms:queue:customer-group-unchanged-queue");

        //Multicast 
        from("jms:queue:customer-group-changed-queue")
                .multicast()
                .to("jms:queue:vend-customer-queue", "jms:queue:sales-service-customer-queue");
        
        //Customer object sitting in Vend Queue
        from("jms:queue:vend-customer-queue")
                .bean(VendCustomerBean.class,
                        "getCustomer(${exchangeProperty.customer_id}, "
                        + "${exchangeProperty.customer_first_name},"
                        + "${exchangeProperty.customer_last_name},"
                        + "${exchangeProperty.customer_updated_group_id})")
                .to("jms:queue:vend-customer-obj-queue");
        
        //Convert Customer object back to JSON to fire at VEND
        from("jms:queue:vend-customer-obj-queue")
                .marshal().json(JsonLibrary.Gson)
                .to("jms:queue:vend-customerchanged-json");
        
        //Fire Customer object to VEND with changed customer group
        from("jms:queue:vend-customerchanged-json")
                .removeHeaders("*")
                .setHeader("Authorization", constant(conf.getVendAuth()))
                .setHeader(Exchange.CONTENT_TYPE).constant("application/json")
                .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
                .recipientList().simple(conf.getVendCustPath() + "/${exchangeProperty.customer_id}")
                .to("jms:queue:vend-customerchanged-response");
        
        //Convert to customer account object
        from("jms:queue:sales-service-customer-queue")
                .bean(CSCustomerBean.class, 
                        "createCustomerAccount(${exchangeProperty.customer_username}, "
                                + "${exchangeProperty.customer_first_name},"
                                + "${exchangeProperty.customer_last_name},"
                                + "${exchangeProperty.customer_email},"
                                + "${exchangeProperty.customer_updated_group_id})")
                .to("jms:queue:sales-service-customer-account-obj");      
        
        //Covert customer account object back to JSON to send to customer service
        from("jms:queue:sales-service-customer-account-obj")
                .marshal().json(JsonLibrary.Gson)
                .to("jms:queue:sales-service-customer-account-json");
        
        //Fire to Customer Accounts Service with changed customer group 
        from("jms:queue:sales-service-customer-account-json")
                .removeHeaders("*")
                .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .recipientList().simple(conf.getCustServPath() + "/customer/${exchangeProperty.customer_username}")
                .to("jms:queue:customer-account-service-put-response");
 
    }

    public static String getPassword(String prompt) {
        JPasswordField txtPasswd = new JPasswordField();
        int resp = JOptionPane.showConfirmDialog(null, txtPasswd, prompt,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resp == JOptionPane.OK_OPTION) {
            String password = new String(txtPasswd.getPassword());
            return password;
        }
        return null;
    }

}
