package br.com.rafaelportela.nareal.services;


import br.com.rafaelportela.nareal.model.Subject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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

    @POST
    @Consumes("application/json")
    public Response create(Subject subject) {

        return Response
                .status(201)
                .entity(subject)
                .build();
    }
}
