package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * A class that defines the properties of an item associated with a sale, within
 * the sales service.
 *
 * @author Harry Ellerm
 */
public class SaleItem implements Serializable {

    @SerializedName("id")
    private String productId;
    @SerializedName("quantity")
    private Double quantity;
    @SerializedName("price")
    private Double price;

    public SaleItem() {}

    /**
     * Custom constructor for class SaleItem
     *
     * @param productId the productId of the sale item
     * @param quantity the quantity of the product being sold
     * @param price the price of the product
     */
    public SaleItem(String productId, Double quantity, Double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the quantity
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SaleItem{" + "productId=" + productId + ", quantity=" + quantity + ", price=" + price + '}';
    }

}
