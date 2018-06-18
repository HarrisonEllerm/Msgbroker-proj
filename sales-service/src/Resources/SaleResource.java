/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import dao.SaleDAO;
import domain.Sale;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 *
 * @author Harry Ellerm
 */
public class SaleResource extends Jooby {
    
      public SaleResource(SaleDAO dao) {
        path("/api/sales/sale", () -> {
            /**
             * Get a specific sale based on the sale id.
             *
             * @param id The sale id.
             * @return <code>200</code> with the sale associated with the id.
             */
            get("/:id", (req) -> {
                String saleID = req.param("id").value();
                if (dao.saleIdExists(saleID)) {
                    return dao.getBySaleID(saleID);
                } else {
                    throw new Err(Status.NOT_FOUND, "No sales were associated with that id!");
                }
            });
        }).produces(MediaType.json).consumes(MediaType.json);
    }  
}
