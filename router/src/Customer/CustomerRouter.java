/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 *
 * @author ellha629
 */
public class CustomerRouter {
    
    private static Config conf = new Config();
     
    public static void main(String[] args) {
        
        try {
            // create default context
            CamelContext camel = new DefaultCamelContext();

            // register ActiveMQ as the JMS handler
            ActiveMQConnectionFactory activeMqFactory = new ActiveMQConnectionFactory(conf.getActiveMQConf());
            JmsComponent jmsComponent = JmsComponent.jmsComponent(activeMqFactory);
            camel.addComponent("jms", jmsComponent);
            
            
            //Add the Routes
            CustomerCreatesAccountRB route1 = new CustomerCreatesAccountRB();
            camel.addRoutes(route1);
            
            CustomerMakesASaleRB route2 = new CustomerMakesASaleRB();
            camel.addRoutes(route2);
        
            // transfer the entire exchange, or just the body and headers?
            jmsComponent.setTransferExchange(true);

            // trust all classes being used to send serialised domain objects
            activeMqFactory.setTrustAllPackages(true);

            // turn exchange tracing on or off (false is off)
            camel.setTracing(false);

            // enable stream caching so that things like loggers don't consume the messages
            camel.setStreamCaching(true);
        
            // start routing
            System.out.println("Starting router...");
            camel.start();
            System.out.println("... ready.  Press enter to shutdown.");
            System.in.read();
            camel.stop();
        } catch (Exception ex) {
            Logger.getLogger(CustomerRouter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
