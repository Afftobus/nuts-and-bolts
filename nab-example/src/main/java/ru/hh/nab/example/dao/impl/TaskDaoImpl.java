package ru.hh.nab.example.dao.impl;

import ru.hh.nab.example.dao.TaskDao;
import ru.hh.nab.example.model.SingleTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class TaskDaoImpl implements TaskDao {

    private ConcurrentHashMap<Long, SingleTask> allTasks = new ConcurrentHashMap<>(1000, 0.75f, 6);
    private AtomicLong currentId = new AtomicLong(0);

    public List<SingleTask> getAll() {
        return new ArrayList<>(allTasks.values());
    }

    public List<SingleTask> getActive() {
        List<SingleTask> all = new ArrayList<>(allTasks.values());

        return all.stream()
                .filter(value -> value.isActive())
                .collect(Collectors.toList());
    }

    public List<SingleTask> getComleted() {
        List<SingleTask> all = new ArrayList<>(allTasks.values());

        return all.stream()
                .filter(value -> !value.isActive())
                .collect(Collectors.toList());
    }

    public boolean addTask(SingleTask task) {
        SingleTask newTask = new SingleTask(currentId.incrementAndGet(), task);
        allTasks.put(newTask.getId(), newTask);
        return false;
    }

    public boolean deleteTask(SingleTask task) {
        return false;
    }

    public boolean deleteTask(int taskId) {
        return false;
    }

    public boolean changeTask(SingleTask task) {
        return false;
    }

    public boolean setActive(boolean isActive) {
        return false;
    }

    public boolean removeCompleted() {
        return false;
    }

    public boolean massChange(String jsonRequest)
    {
        return false;
    }

    public boolean clean() {
        return false;
    }

}
