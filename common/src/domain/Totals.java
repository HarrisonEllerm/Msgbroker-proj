package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * A class that holds information related to the total amount spend in a 
 * sale.
 * @author Harry Ellerm
 * 
 */
public class Totals implements Serializable {
    
    @SerializedName("total_price")
    private Double totalPrice;
    @SerializedName("total_tax")
    private Double totalTax;
    @SerializedName("total_payment")
    private Double totalPayment;
    
    public Totals() {}

    /**
     * Custom constructor for class Totals
     *
     * @param totalPrice the totalPrice associated with the sale
     * @param totalTax the totalTax associated with the sale
     * @param totalPayment the totalPayment associated with the sale
     */
    public Totals(Double totalPrice, Double totalTax, Double totalPayment) {
        this.totalPrice = totalPrice;
        this.totalTax = totalTax;
        this.totalPayment = totalPayment;
    }

    /**
     * @return the totalPrice
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the totalTax
     */
    public Double getTotalTax() {
        return totalTax;
    }

    /**
     * @param totalTax the totalTax to set
     */
    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    /**
     * @return the totalPayment
     */
    public Double getTotalPayment() {
        return totalPayment;
    }

    /**
     * @param totalPayment the totalPayment to set
     */
    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    @Override
    public String toString() {
        return "Totals{" + "totalPrice=" + totalPrice + ", totalTax=" + totalTax + ", totalPayment=" + totalPayment + '}';
    }

}
