package br.com.rafaelportela.nareal.services;

import br.com.rafaelportela.nareal.Main;
import br.com.rafaelportela.nareal.model.Subject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
    public void respondsWith200() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://localhost:8080/services/subjects");
        HttpResponse response = httpClient.execute(get);

        assertThat(response.getStatusLine().getStatusCode(), is(200));
    }

    private HttpResponse postSubject() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8080/services/subjects");

        post.setEntity(new StringEntity("{ \"title\": \"Programming\" }", "utf-8"));
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");

        return httpClient.execute(post);
    }

    @Test
    public void postReturns201WhenCreatingSubject() throws Exception {
        HttpResponse response = postSubject();
        assertThat(response.getStatusLine().getStatusCode(), is(201));
    }

    @Test
    public void postReturnsTheCreatedSubjectEntity() throws IOException {
        HttpResponse response = postSubject();

        Subject created = new ObjectMapper().readValue(
                response.getEntity().getContent(),
                Subject.class);

        assertThat(created.getTitle(), is("Programming"));
    }
}
