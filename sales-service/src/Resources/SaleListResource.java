package Resources;

import dao.SaleDAO;
import domain.Sale;
import java.util.Arrays;
import org.jooby.Err;
import org.jooby.MediaType;
import org.jooby.Status;
import org.jooby.Jooby;

/**
 * A resource that defines the paths related to retrieving all sales, and
 * creating a sale.
 *
 * @author Harry Ellerm
 */
public class SaleListResource extends Jooby {

    public SaleListResource(SaleDAO dao) {
     
        
        path("/api/sales", () -> {
            /**
             * Gets all of the sales in the sales list.
             *
             * @return <code>200</code> with the sales in the sales list.
             */
            get(() -> {
                return dao.getList();
            });
            /**
             * Adds a sale to the sales list.
             *
             * @param body The product to add to the sale.
             * @return <code>201</code> with the sale's details (including the
             * URI) if successful or <code>422</code> if a sale already exists
             * with the same ID.
             */
            post((req, rsp) -> {
                
                System.out.println("Post called!");
                
                Sale sale = req.body(Sale.class);
                //if a sale with the input id does not exist
                if (dao.getBySaleID(sale.getId()) == null) {
                    // construct the URI for this sale
                    String uri = "http://" + req.hostname() + ":" + req.port() + "" + req.path() + "/sale/" + sale.getId();
                    // tell the product about its URI
                    sale.setUri(uri);
                    // store the product
                    dao.addItem(sale);
                    // return a response containing the product
                    rsp.status(Status.CREATED).send(sale);
                } else {
                    throw new Err(Status.FORBIDDEN, "A sale with that id already exists! Please use another id");
                }
            });
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}
