package ru.hh.nab.example.model;

public class SingleTask {
    private Long id;
    private String title;
    private Boolean completed;

    public SingleTask(SingleTask source) {
        this.id = source.id;
        this.title = source.title;
        this.completed = source.completed;
    }

    public SingleTask(SingleTaskDTO source) {
        this.id = source.getId();
        this.title = source.getTitle();
        this.completed = source.isCompleted();
    }

    public SingleTask(
            Long id,
            String title,
            Boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public SingleTask() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
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
