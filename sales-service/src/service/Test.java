/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import domain.Sale;

/**
 *
 * @author ellha629
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Gson gson = new Gson();
        
        String json = "{\"id\":\"fa7d04ba-c21e-a32e-11e8-28ee04d4e5d0\",\"source\":\"USER\",\"source_id\":null,\"sale_date\":\"2018-03-16T07:45:54Z\",\"status\":\"CLOSED\",\"user_id\":\"0afa8de1-147c-11e8-edec-104f6535a398\",\"customer_id\":\"0afa8de1-147c-11e8-edec-25c09e4a6f05\",\"register_id\":\"0afa8de1-1450-11e8-edec-056e6ecf4cd9\",\"market_id\":\"1\",\"invoice_number\":\"2\",\"short_code\":\"e1qpje\",\"totals\":{\"total_price\":\"216.34782\",\"total_loyalty\":\"0.00000\",\"total_tax\":\"32.45218\",\"total_payment\":\"248.80000\",\"total_to_pay\":\"0.00000\"},\"note\":\"\",\"updated_at\":\"2018-03-16T07:46:58+00:00\",\"created_at\":\"2018-03-16 07:46:58\",\"customer\":{\"id\":\"0afa8de1-147c-11e8-edec-25c09e4a6f05\",\"customer_code\":\"Doris-9CR9\",\"customer_group_id\":\"0afa8de1-1450-11e8-edec-056e6ec340ed\",\"first_name\":\"Doris\",\"last_name\":\"Dolores\",\"company_name\":\"\",\"do_not_email\":false,\"email\":\"doris@example.net\",\"phone\":\"\",\"mobile\":\"\",\"fax\":\"\",\"balance\":\"0.000\",\"loyalty_balance\":\"0.00000\",\"enable_loyalty\":true,\"points\":0,\"note\":\"\",\"year_to_date\":\"339.79999\",\"sex\":\"F\",\"date_of_birth\":null,\"custom_field_1\":\"\",\"custom_field_2\":\"\",\"custom_field_3\":\"\",\"custom_field_4\":\"\",\"updated_at\":\"2018-03-12 06:45:59\",\"created_at\":\"2018-03-12 06:43:05\",\"deleted_at\":null,\"contact_first_name\":\"Doris\",\"contact_last_name\":\"Dolores\"},\"user\":{\"id\":\"0afa8de1-147c-11e8-edec-104f6535a398\",\"name\":\"mgeorge\",\"display_name\":\"Mark George\",\"email\":\"mark.george@otago.ac.nz\",\"outlet_id\":null,\"target_daily\":null,\"target_weekly\":null,\"target_monthly\":null,\"created_at\":\"2018-02-12 23:49:42\",\"updated_at\":\"2018-02-13 03:50:57\"},\"register_sale_products\":[{\"id\":\"fa7d04ba-c21e-a32e-11e8-28ee0a158093\",\"product_id\":\"0afa8de1-147c-11e8-edec-056e6fdc68ff\",\"quantity\":1,\"price\":\"43.39130\",\"discount\":\"0.00000\",\"loyalty_value\":\"0.00000\",\"price_set\":false,\"promotions\":{},\"tax\":\"6.50870\",\"price_total\":\"43.3913\",\"tax_total\":\"6.5087\",\"tax_id\":\"0afa8de1-1450-11e8-edec-056e6ec70277\"},{\"id\":\"fa7d04ba-c21e-a32e-11e8-28ee0b94a687\",\"product_id\":\"0afa8de1-147c-11e8-edec-056e7088c06b\",\"quantity\":1,\"price\":\"172.95652\",\"discount\":\"0.00000\",\"loyalty_value\":\"0.00000\",\"price_set\":false,\"promotions\":{},\"tax\":\"25.94348\",\"price_total\":\"172.95652\",\"tax_total\":\"25.94348\",\"tax_id\":\"0afa8de1-1450-11e8-edec-056e6ec70277\"}],\"register_sale_payments\":[{\"id\":\"fa7d04ba-c21e-a32e-11e8-28ee0e5c2afa\",\"payment_date\":\"2018-03-16T07:45:54Z\",\"amount\":\"248.8\",\"retailer_payment_type_id\":\"0afa8de1-1450-11e8-edec-056e6ed01696\",\"payment_type_id\":1,\"retailer_payment_type\":{\"id\":\"0afa8de1-1450-11e8-edec-056e6ed01696\",\"name\":\"Cash\",\"payment_type_id\":\"1\",\"config\":\"{\\\"rounding\\\":\\\"0.10\\\",\\\"algorithm\\\":\\\"round-mid-down\\\"}\"},\"payment_type\":{\"id\":\"1\",\"name\":\"Cash\",\"has_native_support\":false},\"register_sale\":{\"id\":\"fa7d04ba-c21e-a32e-11e8-28ee04d4e5d0\",\"source\":\"USER\",\"source_id\":null,\"sale_date\":\"2018-03-16T07:45:54Z\",\"status\":\"CLOSED\",\"user_id\":\"0afa8de1-147c-11e8-edec-104f6535a398\",\"customer_id\":\"0afa8de1-147c-11e8-edec-25c09e4a6f05\",\"register_id\":\"0afa8de1-1450-11e8-edec-056e6ecf4cd9\",\"market_id\":\"1\",\"invoice_number\":\"2\",\"short_code\":\"e1qpje\",\"totals\":{\"total_price\":\"216.34782\",\"total_loyalty\":\"0.00000\",\"total_tax\":\"32.45218\",\"total_payment\":\"248.80000\",\"total_to_pay\":\"0.00000\"},\"note\":\"\",\"updated_at\":\"2018-03-16T07:46:58+00:00\",\"created_at\":\"2018-03-16 07:46:58\"}}],\"taxes\":[{\"id\":\"6ecd4ad7-056e-11e8-adec-0afa8de11450\",\"name\":\"GST\",\"rate\":\"0.15000\",\"tax\":32.45218}]}";
             
        Sale sale = gson.fromJson(json, Sale.class);
        
        System.out.println(sale);
        
    }
    
}
