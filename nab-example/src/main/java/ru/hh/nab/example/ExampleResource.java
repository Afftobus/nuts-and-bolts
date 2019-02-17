package ru.hh.nab.example;

import ru.hh.nab.example.model.SingleTask;

import ru.hh.nab.example.dao.DaoFactory;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class ExampleResource {

    private final DaoFactory daoFactory = DaoFactory.getDaoFactory("collection");

    @GET
    @Path("/hello")
    public String hello(@DefaultValue("world") @QueryParam("name") String name) {
        return String.format("Hello, %s!", name);
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

    @POST
    @Path("/mass_change")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String massChange(SingleTask[] tasks) {
        System.out.println("public SingleTask massChange");
        daoFactory.getTaskDao().massChange(tasks);
        return "{\"result\":\"success\"}";
    }

    @POST
    @Path("/add_new")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addNew(String jsonRequest) {
        System.out.println(jsonRequest);
        return jsonRequest;
    }

}
