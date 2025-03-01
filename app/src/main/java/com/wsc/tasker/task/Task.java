package com.wsc.tasker.task;
import com.wsc.tasker.core.DateTeTime;

public abstract class Task {
    private DateTeTime created;
    private String description;
    private String name;

    public Task(){
        created = DateTeTime.now();
    }

    public DateTeTime getDateCreated(){
        return created;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public abstract boolean isComplete();
}
