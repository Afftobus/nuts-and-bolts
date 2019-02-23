package ru.hh.nab.example.service;

import ru.hh.nab.example.dao.DaoFactory;
import ru.hh.nab.example.model.SingleTask;
import ru.hh.nab.example.model.SingleTaskDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TodoItemService {
    private final DaoFactory daoFactory = DaoFactory.getDaoFactory("collection");

    public List<SingleTaskDTO> getAll() {
        return daoFactory.getTaskDao().getAll().stream()
                .map(SingleTaskDTO::new)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getCount() {
        Map<String, Long> result = new HashMap<>();
        result.put("active", daoFactory.getTaskDao().getCountActive());
        result.put("completed", daoFactory.getTaskDao().getCountConpleted());
        result.put("total", daoFactory.getTaskDao().getCountTotal());

        return result;
    }

    public List<SingleTaskDTO> getActive() {
        return daoFactory.getTaskDao().getActive().stream()
                .map(SingleTaskDTO::new)
                .collect(Collectors.toList());
    }

    public List<SingleTaskDTO> getComleted() {
        return daoFactory.getTaskDao().getComleted().stream()
                .map(SingleTaskDTO::new)
                .collect(Collectors.toList());
    }

    public SingleTaskDTO addTask(SingleTaskDTO task) {
        SingleTask newTask = new SingleTask(task);
        newTask = daoFactory.getTaskDao().addTask(newTask);

        //return new SingleTaskDTO(daoFactory.getTaskDao().addTask(new SingleTask(task)));
        return new SingleTaskDTO(newTask);
    }

    public boolean deleteTask(SingleTaskDTO task) {
        return daoFactory.getTaskDao().deleteTask(new SingleTask(task));
    }

    public boolean deleteTask(String taskId) {
        return daoFactory.getTaskDao().deleteTask(taskId);
    }

    public SingleTaskDTO changeTask(SingleTaskDTO task) {
        return new SingleTaskDTO(daoFactory.getTaskDao().changeTask(new SingleTask(task)));
    }

    public boolean massChange(List<SingleTaskDTO> tasks) {
        return daoFactory.getTaskDao().massChange(tasks.stream()
                .map(SingleTask::new)
                .collect(Collectors.toList()));
    }

    public List<SingleTaskDTO> find(Long id, String title, Boolean completed) {
        return daoFactory.getTaskDao().find(id, title, completed)
                .stream()
                .map(SingleTaskDTO::new)
                .collect(Collectors.toList());
    }

    public void clean() {
        daoFactory.getTaskDao().clean();
    }

}
