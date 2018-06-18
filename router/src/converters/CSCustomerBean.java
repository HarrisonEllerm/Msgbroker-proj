/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import domain.CustomerAccount;

/**
 *
 * @author harrysmac
 */
public class CSCustomerBean {
    
    private static final String VEND_REG_GROUP = "0afa8de1-147c-11e8-edec-2b197906d816";
    
    public CustomerAccount createCustomerAccount(String username, String firstName, String lastName,
            String email, String group) {
        CustomerAccount account = new CustomerAccount();
        account.setUserName(username);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);
        
        if (group.equals(VEND_REG_GROUP)) {
            account.setGroup("regular customer");
        } else {
            account.setGroup("vip customer");
        }
        return account;
    }
    
}
