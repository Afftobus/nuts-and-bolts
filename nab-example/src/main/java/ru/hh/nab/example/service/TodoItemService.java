package ru.hh.nab.example.service;

import ru.hh.nab.example.dao.DaoFactory;
import ru.hh.nab.example.model.SingleTask;
import ru.hh.nab.example.model.SingleTaskDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TodoItemService {
    private final DaoFactory daoFactory = DaoFactory.getDaoFactory("collection");

    public List<SingleTaskDTO> getAll() {
        return daoFactory.getTaskDao().getAll().stream()
                .map(SingleTaskDTO::new)
                .collect(Collectors.toList());
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

    public boolean addTask(SingleTaskDTO task) {
        return daoFactory.getTaskDao().addTask(new SingleTask(task));
    }

    public boolean deleteTask(SingleTaskDTO task) {
        return daoFactory.getTaskDao().deleteTask(new SingleTask(task));
    }

    public boolean deleteTask(String taskId) {
        return daoFactory.getTaskDao().deleteTask(taskId);
    }

    public boolean changeTask(SingleTaskDTO task) {
        return daoFactory.getTaskDao().changeTask(new SingleTask(task));
    }

    public boolean massChange(List<SingleTaskDTO> tasks) {
        return daoFactory.getTaskDao().massChange(tasks.stream()
                .map(SingleTask::new)
                .collect(Collectors.toList()));
    }

    public void clean() {
        daoFactory.getTaskDao().clean();
    }

}
