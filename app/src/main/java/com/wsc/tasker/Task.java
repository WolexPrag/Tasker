package com.wsc.tasker;
import com.wsc.tasker.observer.MonoEventListener;
import com.wsc.tasker.observer.MonoEventSource;

public abstract class Task {
    protected String _name;
    protected String _description;
    public MonoEventSource onChange;
    public void setName(String name){
        _name = name;
    }
    public String getName(){
        return _name;
    }
    public void setDescription(String description){
        _description = description;
    }
    public String getDescription() {
        return _description;
    }

}
