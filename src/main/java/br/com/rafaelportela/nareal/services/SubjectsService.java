package br.com.rafaelportela.nareal.services;


import br.com.rafaelportela.nareal.model.Subject;
import br.com.rafaelportela.nareal.repository.Repository;
import com.mongodb.MongoClient;
import org.jongo.Jongo;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.UnknownHostException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Path("subjects")
@Produces("application/json")
public class SubjectsService {

    @GET
    public List<Subject> list() throws Exception {
        Subject subject = new Subject("First subject");
        return newArrayList(subject);
    }

    @GET
    @Path("{id}")
    public Subject get(@PathParam("id") String id) throws UnknownHostException {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        Jongo jongo = new Jongo(mongoClient.getDB("nareal-test"));

        Repository repository = new Repository(jongo);
        Subject subjec = repository.getBy(id);

        return subjec;
    }

    @POST
    @Consumes("application/json")
    public Response create(Subject subject) throws UnknownHostException {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        Jongo jongo = new Jongo(mongoClient.getDB("nareal-test"));

        Repository repository = new Repository(jongo);
        repository.save(subject);

        return Response
                .status(201)
                .entity(subject)
                .build();
    }
}
