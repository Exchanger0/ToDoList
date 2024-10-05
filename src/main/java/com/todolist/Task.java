package com.todolist;

import java.time.LocalDateTime;

public class Task {
    public enum Priority {
        HIGH("priority-high"), MEDIUM("priority-medium"), LOW("priority-low");
        public final String cssClass;

        Priority(String cssClass) {
            this.cssClass = cssClass;
        }
    }

    private String title;
    private String content;
    private LocalDateTime executionDate;
    private Priority priority;
    private boolean finalized;

    public Task(String title, String content, Priority priority) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.executionDate = LocalDateTime.now();
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

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
        this.executionDate = executionDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }
}
