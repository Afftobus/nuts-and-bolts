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
    private AtomicLong currentID = new AtomicLong(0);

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

    public SingleTask addTask(SingleTask task) {
        Long ID = currentID.incrementAndGet();

        SingleTask newTask = new SingleTask(
                ID,
                task.getTitle(),
                task.isCompleted()
        );

        allTasks.put(ID, newTask);
        return newTask;
    }

    public boolean deleteTask(SingleTask task) {
        return allTasks.remove(task.getId()) != null;
    }

    public boolean deleteTask(String taskId) {
        return allTasks.remove(taskId) != null;
    }

    public SingleTask changeTask(SingleTask task) {
        SingleTask oldTask = allTasks.get(task.getId());
        if (task.getTitle() != null) {
            oldTask.setTitle(task.getTitle());
        }
        if (task.isCompleted() != null) {
            oldTask.setCompleted(task.isCompleted());
        }
        return allTasks.replace(task.getId(), oldTask);
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

    public List<SingleTask> find(Long id, String title, Boolean completed) {
        List<SingleTask> all = new ArrayList<>(allTasks.values());


        if (id != null) {
            System.out.println(id);
            all = all.stream()
                    .filter(item -> item.getId().equals(id))
                    .collect(Collectors.toList());
        }

        if (title != null) {
            System.out.println(title);
            all = all.stream()
                    .filter(item -> item.getTitle().equals(title))
                    .collect(Collectors.toList());
        }

        if (completed != null) {
            System.out.println(completed);
            all = all.stream()
                    .filter(item -> item.isCompleted().equals(completed))
                    .collect(Collectors.toList());
        }

        return all;
    }

    public void clean() {
        allTasks.clear();
    }

}
