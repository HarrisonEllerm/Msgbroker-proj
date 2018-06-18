package Resources;

import dao.SaleDAO;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 * A resource that defines the path for retrieving the sales related to a 
 * defined customer.
 * @author Harry Ellerm
 */
public class SaleCustomerResource extends Jooby {

    public SaleCustomerResource(SaleDAO dao) {
        path("/api/sales/customer", () -> {
            /**
             * Get all sales for a specific customer.
             *
             * @param id The sale id.
             * @return <code>200</code> with the sales associated with the customer.
             */
            get("/:id", (req) -> {
                String custID = req.param("id").value();
                if (dao.exists(custID)) {
                    return dao.getByCustID(custID);
                } else {
                    throw new Err(Status.NOT_FOUND, "No sales were associated with that customer id!");
                }
            });
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}
