package ru.hh.nab.example;

import org.springframework.web.bind.annotation.CrossOrigin;
import ru.hh.nab.example.model.SingleTask;

import ru.hh.nab.example.dao.DaoFactory;

import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@CrossOrigin(origins = "*", maxAge = 2000, allowCredentials = "true")
public class ExampleResource {

    private final DaoFactory daoFactory = DaoFactory.getDaoFactory("collection");

    @GET
    @Path("/hello")
    public String hello(@DefaultValue("world") @QueryParam("name") String name) {
        daoFactory.populateTestData();
        return String.format("Hello, %s!", name);
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public SingleTask getSingleTask() {

        SingleTask track = new SingleTask();
        track.setTitle("Enter Sandman");
        track.setDescription("Metallica");
        return track;
    }

    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SingleTask> getAll() {
        return daoFactory.getTaskDao().getAll();
    }

    @GET
    @Path("/get_active")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SingleTask> getActive() {
        return daoFactory.getTaskDao().getActive();
    }

    @GET
    @Path("/get_completed")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SingleTask> getCompleted() {
        return daoFactory.getTaskDao().getComleted();
    }


    @PATCH
    @Path("/set_done")
    public String setDone(@DefaultValue("world") @QueryParam("name") String name) {
        return String.format("Hello, %s!", name);
    }

    @POST
    @Path("/add_new")
    @Produces(MediaType.APPLICATION_JSON)
    public String addNew(String jsonRequest) {
        System.out.println(jsonRequest);
        return jsonRequest;
    }

}
