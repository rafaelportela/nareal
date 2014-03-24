package br.com.rafaelportela.nareal;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

    public static void main(String[] args) throws Exception {
        WebServer webServer = new WebServer();
        webServer.start();
        webServer.join();
    }

    public static class WebServer {

        private Server server;

        public void start() throws Exception {
            String webappDirLocation = "src/main/webapp/";

            server = new Server(8080);
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

    }
}