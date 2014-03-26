package br.com.rafaelportela.nareal.services;

import br.com.rafaelportela.nareal.Main;
import br.com.rafaelportela.nareal.model.Subject;
import br.com.rafaelportela.nareal.repository.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static br.com.rafaelportela.nareal.services.Resource.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SubjectsServiceTest {

    private static Main.WebServer webServer;

    @BeforeClass
    public static void setup() throws Exception {
        webServer = new Main.WebServer();
        webServer.setupEnvironment("mongodb://localhost:27017/nareal-test");
        webServer.start();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        webServer.stop();
    }

    @Test
    public void respondsWith200() throws IOException {
        HttpResponse response = getResponse("");

        assertThat(response.getStatusLine().getStatusCode(), is(200));
    }

    @Test
    public void postReturns201WhenCreatingSubject() throws Exception {
        HttpResponse response = postResponse(new Subject());
        assertThat(response.getStatusLine().getStatusCode(), is(201));
    }

    @Test
    public void postReturnsTheCreatedSubjectEntity() throws IOException {
        Subject created = post(new Subject("Programming"));

        assertThat(created.getTitle(), is("Programming"));
    }

    @Test
    public void getSubject() throws Exception {
        Subject created = post(new Subject("Software Testing"));

        Subject subject = get(created.getId());

        assertThat(subject.getTitle(), is(equalTo(created.getTitle())));
    }
}

class Resource {

    public static final String RESOURCE_URL = "http://localhost:8080/services/subjects/";

    static Subject get(String id) throws IOException {
        HttpResponse response = getResponse(id);

        Subject subject = new ObjectMapper()
                .readValue(EntityUtils.toString(response.getEntity()), Subject.class);

        return subject;
    }

    static HttpResponse getResponse(String id) throws IOException {
        HttpGet get = new HttpGet(RESOURCE_URL + id);
        get.setHeader("Accept", "application/json");

        HttpClient httpClient = new DefaultHttpClient();
        return httpClient.execute(get);
    }

    static Subject post(Subject subject) throws IOException {
        HttpResponse response = postResponse(subject);

        Subject created = new ObjectMapper()
                .readValue(EntityUtils.toString(response.getEntity()), Subject.class);
        return created;
    }

    static HttpResponse postResponse(Subject subject) throws IOException {
        HttpPost post = new HttpPost(RESOURCE_URL);

        post.setEntity(new StringEntity(
                new ObjectMapper().writeValueAsString(subject)));

        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept", "application/json");

        HttpClient httpClient = new DefaultHttpClient();
        return httpClient.execute(post);
    }
}
