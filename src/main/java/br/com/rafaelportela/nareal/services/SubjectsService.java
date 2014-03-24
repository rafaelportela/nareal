package br.com.rafaelportela.nareal.services;


import br.com.rafaelportela.nareal.model.Subject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Path("subjects")
@Produces(MediaType.APPLICATION_JSON)
public class SubjectsService {

    @GET
    public List<Subject> list() throws Exception {

        Subject subject = new Subject("First subject");

        return newArrayList(subject);
    }
}
