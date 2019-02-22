package ru.hh.nab.example.resource;

import ru.hh.nab.example.model.SingleTaskDTO;
import ru.hh.nab.example.service.TodoItemService;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PATCH;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/rest")
public class RestResourse {
    private final TodoItemService todoItemService = new TodoItemService();

    RestResourse() {
        todoItemService.addTask(new SingleTaskDTO("123123", "TEST FIRST TASK", false));
    }

    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SingleTaskDTO> getAll() {
        return todoItemService.getAll();
    }

    @GET
    @Path("/get_count")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Long> getCount() {
        return todoItemService.getCount();
    }

    @PATCH
    @Path("/update_item")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateItem(SingleTaskDTO task) {
        if (todoItemService.changeTask(task)) {
            return "{\"result\":\"success\"}";
        } else {
        return "{\"result\":\"error\"}";
        }
    }


    @GET
    @Path("/get_active")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SingleTaskDTO> getActive() {
        return todoItemService.getActive();
    }

    @GET
    @Path("/get_completed")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SingleTaskDTO> getCompleted() {
        return todoItemService.getComleted();
    }

    @POST
    @Path("/mass_change")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String massChange(List<SingleTaskDTO> tasks) {
        if (todoItemService.massChange(tasks)) {
            return "{\"result\":\"success\"}";
        } else {
            return "{\"result\":\"error\"}";
        }
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
