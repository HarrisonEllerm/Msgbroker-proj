package domain;

import java.io.Serializable;

/**
 * A class that defines the properties of a summary report, associate with a
 * particular customers sales activity, within the sales service.
 *
 * @author Harry Ellerm
 */
public class Summary implements Serializable {

    private Integer numberOfSales;
    private Double totalPayment;
    private String group;
    private String uri;

    public Summary() {
    }

    /**
     * Custom constructor for class Summary
     *
     * @param numberOfSales the total number of sales
     * @param totalPayment the aggregate payment across all sales
     * @param group the group a customer belongs do, defined by the totalPayment
     * @param uri the uri associated with the summary
     */
    public Summary(Integer numberOfSales, Double totalPayment, String group,
            String uri) {
        this.numberOfSales = numberOfSales;
        this.totalPayment = totalPayment;
        this.group = group;
    }

    /**
     * @return the numberOfSales
     */
    public Integer getNumberOfSales() {
        return numberOfSales;
    }

    /**
     * @param numberOfSales the numberOfSales to set
     */
    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
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
        return "Summary{" + "numberOfSales=" + numberOfSales + ", totalPayment=" + totalPayment + ", group=" + group + ", uri=" + uri + '}';
    }

}
