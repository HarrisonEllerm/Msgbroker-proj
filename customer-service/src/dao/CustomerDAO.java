package dao;

import domain.CustomerAccount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


    
/**
 * A DAO class that uses collections to store customer information, while the
 * service is running.
 *
 * @author Harry Ellerm
 */
public class CustomerDAO {
 
    private static final Map<String, CustomerAccount> CUSTMAP = new HashMap<>();

    /**
     * Returns a List of all customers within the collection.
     * <p>
     *
     * @return the customer list
     */
    public List<CustomerAccount> getList() {
        return new ArrayList(CUSTMAP.values());
    }
 
    /**
     * Adds a customer to the customer collection.
     * <p>
     *
     * @param customer the customer being added
     */
    public void addItem(CustomerAccount customer) {
        customer.setGroup("regular customer");
        CUSTMAP.put(customer.getUserName(), customer);
    }
 
    /**
     * Retrieves a customer from the customer collection, via name.
     * <p>
     *
     * @param itemName the customer's userName
     * @return the customer's account
     */
    public CustomerAccount getByName(String itemName) {
        return CUSTMAP.get(itemName);
    }
 
    /**
     * Deletes a customer from the customer collection.
     * <p>
     *
     * @param name the userName of the customer being deleted
     */
    public void delete(String name) {
        CUSTMAP.remove(name);
    }
 
    /**
     * Updates a customer's account from the customer collection.
     * <p>
     *
     * @param name the userName of the customer being updated
     * @param updatedCustomer the customer being updated
     */
    public void updateItem(String name, CustomerAccount updatedCustomer) {
        CUSTMAP.put(name, updatedCustomer);
    }
 
    /**
     * Test to see whether a customer exists within the customer collection.
     * <p>
     *
     * @param itemName the userName of the customer
     * @return whether or not the customer exists
     */
    public boolean exists(String itemName) {
        return CUSTMAP.containsKey(itemName);
    }
}
    
