package ru.hh.nab.example.dao;

import ru.hh.nab.example.model.SingleTask;

import java.util.List;

public interface TaskDao {

    List<SingleTask> getAll();

    List<SingleTask> getActive();

    List<SingleTask> getComleted();

    boolean addTask(SingleTask task);

    boolean deleteTask(SingleTask task);

    boolean deleteTask(String taskId);

    boolean changeTask(SingleTask task);

    boolean massChange(SingleTask[] tasks);
}
