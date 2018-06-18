package dao;

import domain.Sale;
import domain.Summary;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A DAO class that uses collections to store sale information, while the
 * service is running.
 *
 * @author Harry Ellerm
 */
public class SaleDAO {

    private static Map<String, List<Sale>> SALEMAP = new HashMap<>();
    private static Map<String, Sale> saleIdMap = new HashMap<>();
    private static List<Sale> SALELIST = new ArrayList<>();
    private static final Integer VIPLEVEL = 5000;
    
    /**
     * Returns a List of all the sales within the collection.
     * <p>
     *
     * @return the sale list
     */
    public List<Sale> getList() {
        return SaleDAO.getSALELIST();
    }

    /**
     * Adds a sale to the List of sales. Also adds the sale to a map which has
     * the customer's id who is associated with this sale as the key.
     * <p>
     *
     * @param sale a sale being added
     *
     */
    public void addItem(Sale sale) {
        getSALELIST().add(sale);
        getSaleIdMap().put(sale.getId(), sale);
        if (getSALEMAP().containsKey(sale.getCust().getId())) {
            List<Sale> sales = getSALEMAP().get(sale.getCust().getId());
            sales.add(sale);
        } else {
            List<Sale> sales = new ArrayList<>();
            sales.add(sale);
            getSALEMAP().put(sale.getCust().getId(), sales);
        }
    }

    /**
     * Returns a List of all the sales within the collection, associated with a
     * particular customer.
     * <p>
     *
     * @param customerID the customer's id
     * @return the sale list
     */
    public List<Sale> getByCustID(String customerID) {
        return getSALEMAP().get(customerID);
    }

    /**
     * Deletes all of the sales associated with a particular customer.
     * <p>
     *
     * @param customerID the customer's id
     *
     */
    public void deleteAllCustSales(String customerID) {
        getSALEMAP().remove(customerID);
        Iterator<Sale> i = getSALELIST().iterator();
        while (i.hasNext()) {
            String custID = i.next().getCust().getId();
            if (custID.equals(customerID)) {
                i.remove();
            }
        }
    }

    /**
     * Identifies if a customer has sale(s) registered against them.
     * <p>
     *
     * @param custID the customer's id
     * @return a boolean representing if a customer has sale(s) registered
     * against them
     *
     */
    public boolean exists(String custID) {
        return getSALEMAP().containsKey(custID);
    }

    /**
     * Identifies if a sale with a given id exists.
     * <p>
     *
     * @param saleID the sale's id
     * @return a boolean representing if the sale exists
     *
     */
    public boolean saleIdExists(String saleID) {
        return getSaleIdMap().containsKey(saleID);
    }

    public Sale getBySaleID(String saleID) {
        return getSaleIdMap().get(saleID);
    }
    
    
    /**
     * Produces a summary of a given customer's sale information.
     * <p>
     *
     * @param customerID the customer's id
     * @return Summary the summary object containing summary information
     *
     */
    public Summary getSummary(String customerID) {
        Summary sum = new Summary();
        Integer numOfSales = 0;
        Double totalPayment = 0.0;
        List<Sale> sales = getByCustID(customerID);
        for (Sale sale : sales) {
            numOfSales++;
            totalPayment += sale.getTotal().getTotalPayment();
        }
        sum.setNumberOfSales(numOfSales);
        sum.setTotalPayment(totalPayment);
        if (totalPayment <= VIPLEVEL) {
            sum.setGroup("Regular Customer");
        } else {
            sum.setGroup("VIP Customer");
        }
        return sum;
    }

    /**
     * @return the SALEMAP
     */
    public static Map<String, List<Sale>> getSALEMAP() {
        return SALEMAP;
    }

    /**
     * @param aSALEMAP the SALEMAP to set
     */
    public static void setSALEMAP(Map<String, List<Sale>> aSALEMAP) {
        SALEMAP = aSALEMAP;
    }

    /**
     * @return the SALELIST
     */
    public static List<Sale> getSALELIST() {
        return SALELIST;
    }

    /**
     * @param aSALELIST the SALELIST to set
     */
    public static void setSALELIST(List<Sale> aSALELIST) {
        SALELIST = aSALELIST;
    }

    /**
     * @return the saleIdMap
     */
    public static Map<String, Sale> getSaleIdMap() {
        return saleIdMap;
    }

    /**
     * @param aSaleIdMap the saleIdMap to set
     */
    public static void setSaleIdMap(Map<String, Sale> aSaleIdMap) {
        saleIdMap = aSaleIdMap;
    }
}
