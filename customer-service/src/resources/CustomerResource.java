package resources;

import dao.CustomerDAO;
import domain.CustomerAccount;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 *
 * @author Harry Ellerm
 */
public class CustomerResource extends Jooby {

    public CustomerResource(CustomerDAO dao) {

        path("/api/customers/customer", () -> {
            /**
             * Get an customer.
             *
             * @param username The customers username.
             * @return The customer account matching the given username.
             */
            get("/:username", (req) -> {
                String name = req.param("username").value();
                if (dao.exists(name)) {
                    return dao.getByName(name);
                } else {
                    throw new Err(Status.NOT_FOUND, "No Customer Account matches that username");
                }

            });
            /**
             * Update an existing customer account.
             *
             * @param username The username of the customer account to update.
             * @param body The new details for the item.
             */
            put("/:username", (req, rsp) -> {
                String username = req.param("username").value();
                CustomerAccount customer = req.body().to(CustomerAccount.class);
                
                if (username.equals(customer.getUserName()) && dao.exists(username)) {
                      String uri = "http://" + req.hostname() + ":" + req.port() + "" + req.path();
                      customer.setUri(uri);
                    dao.updateItem(username, customer);
                    rsp.status(Status.ACCEPTED);
                } else {
                    rsp.status(Status.CONFLICT);
                }
            });
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}
