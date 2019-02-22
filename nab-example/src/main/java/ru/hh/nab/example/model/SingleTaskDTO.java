package ru.hh.nab.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleTaskDTO {

    @JsonProperty(required = true)
    private String id;

    @JsonProperty(required = true)
    private String title;

    @JsonProperty(required = true)
    private boolean completed;

    public SingleTaskDTO(SingleTaskDTO source) {
        this.id = source.id;
        this.title = source.title;
        this.completed = source.completed;
    }

    public SingleTaskDTO(SingleTask source) {
        this.id = source.getId();
        this.title = source.getTitle();
        this.completed = source.isCompleted();
    }

    public SingleTaskDTO(
            String id,
            String title,
            boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public SingleTaskDTO() {
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

        SingleTaskDTO task = (SingleTaskDTO) o;

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
