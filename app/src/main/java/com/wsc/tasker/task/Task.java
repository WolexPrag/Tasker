package com.wsc.tasker.task;

import com.wsc.tasker.core.DateTeTime;

import java.util.List;

public class Task {
    private String name;
    private String description;
    private DateTeTime created;
    private List<DateTeTime> complition;

    public Task(){
        created = DateTeTime.GetCurrentDate();
    }
    public Task(DateTeTime created){
        super();
        setName(name);
    }
    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void addCompletion(DateTeTime Date) {

        complition.add(Date);
    }

    public void removeCompletion(DateTeTime Date) {
        complition.remove(Date);
    }

    public Boolean isCompletionInDate(DateTeTime Date) {
        return complition.stream().anyMatch(v -> v.equals(Date));
    }

    public DateTeTime getDateCreated() {
        return created;
    }
}
