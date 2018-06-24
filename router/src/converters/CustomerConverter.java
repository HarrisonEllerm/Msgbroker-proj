/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import domain.Customer;

/**
 * Converts a Customer Account to a Customer to be used in Vend
 * @author ellha629
 */
public class CustomerConverter {
    
    private String vendCustCode = "0afa8de1-147c-11e8-edec-2b197906d816";
    
    public Customer createCustomer(String firstName, String lastName,
            String email, String customField1) {
        return new Customer(firstName, lastName, email, 
                vendCustCode, customField1);
    }
   
}
