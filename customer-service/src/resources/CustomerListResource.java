package resources;

import dao.CustomerDAO;
import domain.CustomerAccount;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 *
 * @author Harry Ellerm
 */
public class CustomerListResource extends Jooby {

    public CustomerListResource(CustomerDAO dao) {

        path("/api/customers", () -> {
            /**
             * Gets all of the customers in the customer list.
             *
             * @return <code>200</code> with the customers in the list.
             */
            get(() -> {
                return dao.getList();
            });
            /**
             * Adds a customer to the customers list.
             *
             * @param body The customer to add to the customer list.
             * @return <code>201</code> with the customer's details (including
             * the URI) if successful or <code>422</code> if a customer already
             * exists with the same ID.
             */
            post((req, rsp) -> {
                CustomerAccount cust = req.body(CustomerAccount.class);
                String uri = "http://" + req.hostname() + ":" + req.port() + "" + req.path() + "/customer/" + cust.getUserName();
                cust.setUri(uri);
                dao.addItem(cust);
                rsp.status(Status.CREATED).send(cust);
            });
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}
