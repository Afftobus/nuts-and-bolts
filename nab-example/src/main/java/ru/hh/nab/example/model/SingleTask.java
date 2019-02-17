package ru.hh.nab.example.model;

public class SingleTask {
    private Long id;
    private String title;
    private boolean active;
    private boolean deleted;

    public SingleTask(
            Long id,
            SingleTask source) {
        this.id = id;
        this.title = source.title;
        this.active = source.active;
        this.deleted = source.deleted;
    }

    public SingleTask(
            String title,
            boolean active) {
        this.id = 0L;
        this.title = title;
        this.active = active;
        this.deleted = false;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean delete() {
        return false;
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
        int result = (int) (id ^ (id >>> 32));
        result += 31 * result + title.hashCode();
        return result;
    }


//    @Override
//    public String toString() {
//        return "Track [title=" + title + ", singer=" + description + "]";
//    }
}
