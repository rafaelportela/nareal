package br.com.rafaelportela.nareal;

import br.com.rafaelportela.nareal.repository.Repository;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoURI;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.jongo.Jongo;

import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws Exception {
        WebServer webServer = new WebServer();

        String uri = System.getenv("MONGOHQ_URL");
        if (uri != null) {
            webServer.setupEnvironment(uri);
        }

        webServer.start();
        webServer.join();
    }

    public static class WebServer {

        private Server server;
        public void start() throws Exception {
            String webappDirLocation = "src/main/webapp/";

            String port = System.getenv("PORT");
            if (port == null) {
                port = "8080";
            }

            server = new Server(Integer.parseInt(port));

            WebAppContext root = new WebAppContext();

            root.setContextPath("/");
            root.setDescriptor(webappDirLocation + "WEB-INF/web.xml");
            root.setResourceBase(webappDirLocation);
            root.setParentLoaderPriority(true);

            server.setHandler(root);
            server.start();
        }

        public void join() throws InterruptedException {
            server.join();
        }

        public void stop() throws Exception {
            server.stop();
        }

        public void setupEnvironment(String mongoUri) throws UnknownHostException {
            Repository.setupEnvironment(mongoUri);
        }
    }
}