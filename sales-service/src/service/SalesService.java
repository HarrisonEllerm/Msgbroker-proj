package service;

import Resources.SaleCustomerResource;
import Resources.SaleListResource;
import Resources.SaleResource;
import Resources.SalesSummaryResource;
import dao.SaleDAO;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.apitool.ApiTool;
import org.jooby.json.Gzon;

/**
 * A main class that runs the service.
 * @author ellha629
 */
public class SalesService extends Jooby {

    public SalesService() {

        SaleDAO dao = new SaleDAO();

        use(new Gzon());
        use(new SaleListResource(dao));
        use(new SaleCustomerResource(dao));
        use(new SaleResource(dao));
        use(new SalesSummaryResource(dao));
        use(new ApiTool().swagger());

    }

    public static void main(String[] args) throws IOException {

        SalesService server = new SalesService();
        server.port(8080);

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
