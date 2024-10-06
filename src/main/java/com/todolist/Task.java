package com.todolist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    public Task(String title, String content, Priority priority, LocalDateTime executionDate) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.executionDate = executionDate;
        if (executionDate.isBefore(LocalDateTime.now()))
            finalized = true;
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

    //изменять статус можно только пока executionDate > now
    //если executionDate < now, то finalized=true всегда
    public void setFinalized(boolean finalized) {
        if (executionDate.isAfter(LocalDateTime.now()))
            this.finalized = finalized;
        else
            this.finalized = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return Objects.equals(title, task.title) && Objects.equals(content, task.content) && Objects.equals(executionDate, task.executionDate) && priority == task.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, executionDate, priority);
    }
}
