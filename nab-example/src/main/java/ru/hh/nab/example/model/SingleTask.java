package ru.hh.nab.example.model;

public class SingleTask {
    private String id;
    private String title;
    private boolean completed;

    public SingleTask(SingleTask source) {
        this.id = source.id;
        this.title = source.title;
        this.completed = source.completed;
    }

    public SingleTask(
            String id,
            String title,
            boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public SingleTask() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SingleTask task = (SingleTask) o;

        if (!id.equals(task.id)) {
            return false;
        }

        return title.equals(task.title);
    }

    @Override
    public int hashCode() {
        int result = 31 * id.hashCode();
        result += 31 * result + title.hashCode();
        return result;
    }
}
