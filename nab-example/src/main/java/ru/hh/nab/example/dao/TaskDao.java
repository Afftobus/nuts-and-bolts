package ru.hh.nab.example.dao;

import ru.hh.nab.example.model.SingleTask;

import java.util.List;

public interface TaskDao {

    List<SingleTask> getAll();

    List<SingleTask> getActive();

    List<SingleTask> getComleted();

    boolean addTask(SingleTask task);

    boolean deleteTask(SingleTask task);

    boolean deleteTask(int taskId);

    boolean changeTask(SingleTask task);

    boolean setActive(boolean isActive);

    boolean massChange(String jsonRequest);

    boolean removeCompleted();
}
