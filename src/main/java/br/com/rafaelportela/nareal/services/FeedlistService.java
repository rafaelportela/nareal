package br.com.rafaelportela.nareal.services;


import br.com.rafaelportela.nareal.model.Activity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class FeedlistService {

    @GET
    @Path("activities")
    public List<Activity> list() {

        List<Activity> list = new ArrayList<Activity>();
        list.add(new Activity("title", "content"));

        return list;
    }
}
