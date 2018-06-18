package Resources;

import dao.SaleDAO;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 * A resource that defines the path for retrieving a summary of a particular
 * customers sales activity.
 * @author Harry Ellerm
 */
public class SalesSummaryResource extends Jooby {

    public SalesSummaryResource(SaleDAO dao) {
        path("/api/sales/summary", () -> {
            /**
             * Get summary of the sale data for a specific customer.
             *
             * @param id The customers id.
             * @return <code>200</code> with the summary of sale data for the customer.
             */
            get("/:id", (req) -> {
                String name = req.param("id").value();
                if (dao.exists(name)) {
                    return dao.getSummary(name);
                } else {
                    throw new Err(Status.NOT_FOUND, "No sale matches that id");
                }
            });
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}
