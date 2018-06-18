package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * A class that defines the properties of a sale, within the sales service.
 *
 * @author Harry Ellerm
 */
public class Sale implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("sale_date")
    private String saleDate;
    private String uri;
    @SerializedName("customer")
    private Customer cust;
    @SerializedName("totals") 
    private Totals total;
    @SerializedName("register_sale_products")
    private List<SaleItem> saleItems;
    @SerializedName("custom_field_1")
    private String username;
    

    public Sale() {}

    /**
     * Custom constructor for class Sale
     *
     * @param id the id of the sale
     * @param saleDate the date of the sale
     * @param uri the uri of the sale
     * @param saleItems the sale item involved with the sale
     */
    public Sale(String id, String saleDate, String uri, List<SaleItem> saleItems) {
        this.id = id;
        this.saleDate = saleDate;
        this.uri = uri;
        this.saleItems = saleItems;
    }
    
    public Sale(String id, String saleDate, List<SaleItem> saleItems) {
        this.id = id;
        this.saleDate = saleDate;
        this.saleItems = saleItems;
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
     * @return the saleDate
     */
    public String getSaleDate() {
        return saleDate;
    }

    /**
     * @param saleDate the saleDate to set
     */
    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
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

    /**
     * @return the customer
     */
    public Customer getCust() {
        return cust;
    }

    /**
     * @param cust the customer to set
     */
    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public Totals getTotal() {
        return total;
    }

    public void setTotal(Totals total) {
        this.total = total;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", saleDate=" + saleDate + ", uri=" + uri + ", cust=" + cust + ", total=" + total + ", saleItems=" + saleItems + ", username=" + username + '}';
    }
   
}
