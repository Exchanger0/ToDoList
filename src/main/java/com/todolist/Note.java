package com.todolist;

import java.util.Objects;

public class Note {
    public enum Priority {
        HIGH("priority-high"), MEDIUM("priority-medium"), LOW("priority-low");
        public final String cssClass;

        Priority(String cssClass) {
            this.cssClass = cssClass;
        }
    }

    protected String title;
    protected String content;
    protected Priority priority;

    public Note(String title, String content, Priority priority) {
        this.title = title;
        this.content = content;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(title, note.title) && Objects.equals(content, note.content) && priority == note.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, priority);
    }
}
