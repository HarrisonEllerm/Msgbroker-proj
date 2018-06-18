package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that defines the properties of a customer within the sales service.
 * THIS IS FOR VEND
 * @author Harry Ellerm
 */
public class Customer implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("customer_group_id")
    private String groupID;
    @SerializedName("custom_field_1")
    private String customField1;
    
    
    /*
      The 'sales' map needs to be transient as the sale refers to the customer, 
      and a customer contains that same sale (loop). This relationship exists to 
      dual purpose the customer domain class as both a model and a mechanism for 
      tracking sales for customers. The GSON converter gets stuck in a loop however,
      trying to convert the objects to JSON. The fix here is to use the keyword
      transient, to stop GSON from trying to convert the 'sales' object in the
      customer class into JSON. 
     */
    private transient Map<String, Sale> sales = new HashMap<>();

    public Customer() {}

    /**
     * Custom constructor for class Customer
     *
     * @param id the id of the customer
     * @param firstName the customer's first name
     * @param lastName the customer's last name
     * @param email the customer's email address
     * @param groupID
     */
//    public Customer(String id, String firstName, String lastName, String email, String groupID) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.groupID = groupID;
//    }
    
    public Customer(String first_name, String last_name, String email, String groupID, String customField1) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.groupID = groupID;
        this.customField1 = customField1;
    }

    /**
     * Adds a sale to the list of sales associated with a customer
     * <p>
     *
     * @param sale the sale to add
     *
     */
    public void addSale(Sale sale) {
        this.sales.put(sale.getId(), sale);
    }

    /**
     * Finds a sale from the list of sales associated with a customer via the
     * sale's id
     * <p>
     *
     * @param saleID the id of the sale
     * @return Sale the sale found
     */
    public Sale findSale(String saleID) {
        return this.sales.get(saleID);
    }

    /**
     * Deletes a sale from the list of sales associated with a customer
     * <p>
     *
     * @param sale the sale to delete
     */
    public void deleteSale(Sale sale) {
        this.sales.remove(sale.getId(), sale);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the groupID
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroup(String groupID) {
        this.groupID = groupID;
    }

    /**
     * @return the customField1
     */
    public String getCustomField1() {
        return customField1;
    }

    /**
     * @param customField1 the customField1 to set
     */
    public void setCustomField1(String customField1) {
        this.customField1 = customField1;
    }

    @Override
    public String toString() {
        return "Customer{" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", groupID=" + groupID + ", customField1=" + customField1 + '}';
    }

}
