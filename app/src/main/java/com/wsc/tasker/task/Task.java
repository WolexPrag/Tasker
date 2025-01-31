package com.wsc.tasker.task;

import com.wsc.tasker.core.DateTeTime;

import java.util.List;

public class Task {
    private String _name;
    private String _description;
    private DateTeTime _created;
    private List<DateTeTime> _complition;

    public Task(){
        _created = DateTeTime.GetCurrentDate();
    }
    public Task(String name){
        super();
        setName(name);
    }
    public void setName(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getDescription() {
        return _description;
    }

    public void addCompletion(DateTeTime Date) {

        _complition.add(Date);
    }

    public void removeCompletion(DateTeTime Date) {
        _complition.remove(Date);
    }

    public Boolean isCompletionInDate(DateTeTime Date) {
        return _complition.stream().anyMatch(v -> v.equals(Date));
    }

    public DateTeTime getDateCreated() {
        return _created;
    }
}
