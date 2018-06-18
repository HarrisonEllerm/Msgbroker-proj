package service;

import dao.CustomerDAO;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.apitool.ApiTool;
import org.jooby.json.Gzon;
import resources.CustomerListResource;
import resources.CustomerResource;

/**
 * The actual Customer service.
 * @author Harry Ellerm
 */
public class Service extends Jooby {

    public Service() {
        CustomerDAO dao = new CustomerDAO();
        use(new Gzon());
        use(new CustomerListResource(dao));
        use(new CustomerResource(dao));
        use(new ApiTool().swagger());

    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Service server = new Service();
        server.port(8088);
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop service.");
        });
        System.in.read();
        System.exit(0);
    }
}
