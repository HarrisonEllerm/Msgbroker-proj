/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import domain.Summary;

/**
 *
 * @author harrysmac
 */
public class VendSummaryBean {
    
    private static final String VEND_REG_GROUP = "0afa8de1-147c-11e8-edec-2b197906d816";
    private static final String VEND_VIP_GROUP = "0afa8de1-147c-11e8-edec-201e0f00872c";
    
    public Summary getVendSummary(Integer numOfSales, Double totalPayment, String groupFromSalesService) {
          
        if(groupFromSalesService != null && groupFromSalesService.trim().equals("Regular Customer")) {
           
            Summary summ = new Summary();
            summ.setNumberOfSales(numOfSales);
            summ.setTotalPayment(totalPayment);
            summ.setGroup(VEND_REG_GROUP);
            return summ;
          
        } else {
            
            Summary summ = new Summary();
            summ.setNumberOfSales(numOfSales);
            summ.setTotalPayment(totalPayment);
            summ.setGroup(VEND_VIP_GROUP);
            return summ;
        }
    }
}
