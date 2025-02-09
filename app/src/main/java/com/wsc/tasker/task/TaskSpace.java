package com.wsc.tasker.task;

import com.wsc.tasker.event.INotifier;
import com.wsc.tasker.event.ISubscriber;
import com.wsc.tasker.event.LocalNotifier;

import java.util.ArrayList;
import java.util.List;

public class TaskSpace {
    private List<Task> tasks;
    private INotifier<List<Task>> taskUpdateNotifier;

    public TaskSpace() {
        taskUpdateNotifier = new LocalNotifier<>();
    }

    public TaskSpace(List<Task> tasks) {
        taskUpdateNotifier = new LocalNotifier<>();
        setTasks( new ArrayList<>(tasks));
    }
    private void invokeUpdate(){
        taskUpdateNotifier.notify(getCopyTask());
    }
    public void subscribe(ISubscriber<List<Task>> subscriber){
        taskUpdateNotifier.subscribe(subscriber);
    }

    public List<Task> getCopyTask() {
        return new ArrayList<>(tasks);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        invokeUpdate();
    }

}



