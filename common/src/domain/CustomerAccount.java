package domain;

import java.io.Serializable;

/**
 * A class that stores the information relevant to a customer, used within the
 CustomerAccount service.
 * @author Harry Ellerm
 */
public class CustomerAccount implements Serializable {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String group;
    private String uri;
    private String customField1;
   
    
    public CustomerAccount(){}

    /**
     * Custom constructor for class CustomerAccount
     *
     * @param username the username of the customer
     * @param firstName the customer's first name
     * @param lastName the customer's last name
     * @param email the customer's email address
     * @param group the group the customer belongs to
     * @param uri the uri associated with the customer
     */
    public CustomerAccount(String username, String firstName, String lastName,
            String email, String group, String uri) {
        this.userName = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.group = group;
        this.uri = uri;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Customer{" + "userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", group=" + group + ", uri=" + uri + '}';
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

}
