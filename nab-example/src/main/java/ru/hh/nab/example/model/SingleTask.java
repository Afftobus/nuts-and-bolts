package ru.hh.nab.example.model;

public class SingleTask {
    private Long id;
    private String title;
    private String description;
    private String date;
    private int creator;
    private boolean active;

    public SingleTask(
            Long id,
            SingleTask source) {
        this.id = id;
        this.title = source.title;
        this.description = source.description;
        this.date = source.date;
        this.creator = source.creator;
        this.active = source.active;
    }

    public SingleTask(
            String title,
            String description,
            String date,
            int creator,
            boolean active) {
        this.id = 0L;
        this.title = title;
        this.description = description;
        this.date = date;
        this.creator = creator;
        this.active = active;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

        if (!title.equals(task.title)) {
            return false;
        }

        if (!date.equals(task.date)) {
            return false;
        }

        return description.equals(task.description);
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
