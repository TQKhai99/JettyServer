import org.eclipse.jetty.server.Server;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class Jetty {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        try{
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            server.setHandler(context);
            context.addServlet(new ServletHolder(new Api()),"/api");

            server.start();
            System.out.println("server started successful");
        }
        catch(Exception ex){
            System.out.println("server started failed. exception message:"+ex.getMessage());
        }
    }
}