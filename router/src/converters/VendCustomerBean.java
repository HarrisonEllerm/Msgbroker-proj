/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import domain.Customer;

/**
 *
 * @author harrysmac
 */
public class VendCustomerBean {
    
    public Customer getCustomer(String customer_id, String first_name,
            String last_name, String customer_group_id) {
        Customer customer = new Customer();
        customer.setId(customer_id);
        customer.setFirstName(first_name);
        customer.setLastName(last_name);
        customer.setGroup(customer_group_id);
        return customer;
    }
}
