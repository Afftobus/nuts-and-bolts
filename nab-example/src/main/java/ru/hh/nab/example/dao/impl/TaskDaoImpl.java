package ru.hh.nab.example.dao.impl;

import ru.hh.nab.example.dao.TaskDao;
import ru.hh.nab.example.model.SingleTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TaskDaoImpl implements TaskDao {

    private ConcurrentHashMap<String, SingleTask> allTasks = new ConcurrentHashMap<>(1000, 0.75f, 6);

    public List<SingleTask> getAll() {
        return new ArrayList<>(allTasks.values());
    }

    public List<SingleTask> getActive() {
        List<SingleTask> all = new ArrayList<>(allTasks.values());

        return all.stream()
                .filter(value -> !value.isCompleted())
                .collect(Collectors.toList());
    }

    public List<SingleTask> getComleted() {
        List<SingleTask> all = new ArrayList<>(allTasks.values());

        return all.stream()
                .filter(value -> !value.isCompleted())
                .collect(Collectors.toList());
    }

    public boolean addTask(SingleTask task) {
        SingleTask newTask = new SingleTask(task);
        return allTasks.put(newTask.getId(), newTask) != null;
    }

    public boolean deleteTask(SingleTask task) {
        return allTasks.remove(task.getId()) != null;
    }

    public boolean deleteTask(String taskId) {
        return allTasks.remove(taskId) != null;
    }

    public boolean changeTask(SingleTask task) {
        SingleTask oldTask = allTasks.get(task.getId());
        if (task.getTitle() != null) {
            oldTask.setTitle(task.getTitle());
        }
        if (task.isCompleted() != null) {
            oldTask.setCompleted(task.isCompleted());
        }
        return allTasks.replace(task.getId(), oldTask) != null;
    }

    public boolean massChange(List<SingleTask> tasks) {
        allTasks.clear();
        for (SingleTask task : tasks) {
            allTasks.put(task.getId(), task);
        }

        return true;
    }

    public long getCountActive() {
        List<SingleTask> all = new ArrayList<>(allTasks.values());

        return all.stream()
                .filter(value -> !value.isCompleted())
                .count();
    }

    public long getCountConpleted() {
        List<SingleTask> all = new ArrayList<>(allTasks.values());

        return all.stream()
                .filter(value -> value.isCompleted())
                .count();
    }

    public long getCountTotal() {
        return allTasks.values().size();
    }


    public void clean() {
        allTasks.clear();
    }

}
