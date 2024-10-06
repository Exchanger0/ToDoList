package com.todolist;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task extends Note {
    protected LocalDateTime executionDate;
    protected volatile boolean finalized;

    public Task(String title, String content, Priority priority, LocalDateTime executionDate) {
        super(title, content, priority);
        this.executionDate = executionDate;
        if (executionDate.isBefore(LocalDateTime.now()))
            finalized = true;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
        this.executionDate = executionDate;
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
}
