package br.com.rafaelportela.nareal.services;

import br.com.rafaelportela.nareal.Main;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SubjectsServiceTest {

    private static Main.WebServer webServer;

    @BeforeClass
    public static void setup() throws Exception {
        webServer = new Main.WebServer();
        webServer.start();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        webServer.stop();
    }

    @Test
    public void test() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://localhost:8080/services/subjects");
        HttpResponse response = httpClient.execute(get);

        assertThat(response.getStatusLine().getStatusCode(), is(200));
    }
}
