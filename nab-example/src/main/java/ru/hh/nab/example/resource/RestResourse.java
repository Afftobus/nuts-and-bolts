package ru.hh.nab.example.resource;

import ru.hh.nab.example.model.SingleTaskDTO;
import ru.hh.nab.example.service.TodoItemService;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
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
    private Long id;

    RestResourse() {
        todoItemService.addTask(new SingleTaskDTO(null, "TEST FIRST TASK", false));
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
    public SingleTaskDTO updateItem(SingleTaskDTO task) {
        return todoItemService.changeTask(task);
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
    public SingleTaskDTO addNew(SingleTaskDTO task) {
        return todoItemService.addTask(task);
    }


    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SingleTaskDTO> find(@QueryParam("id") Long id, @QueryParam("title") String title, @QueryParam("completed") Boolean completed) {
        return todoItemService.find(id, title, completed);
    }

}
