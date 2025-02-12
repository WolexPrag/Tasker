package com.wsc.tasker.task;

import androidx.annotation.NonNull;

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
        setTasks(new ArrayList<>());
    }

    public TaskSpace(List<Task> tasks) {
        taskUpdateNotifier = new LocalNotifier<>();
        setTasks(new ArrayList<>(tasks));
    }

    private void invokeUpdate() {
        taskUpdateNotifier.notify(getCopyTask());
    }

    public void subscribe(@NonNull ISubscriber<List<Task>> subscriber) {
        taskUpdateNotifier.subscribe(subscriber);
    }

    @NonNull
    public List<Task> getCopyTask() {
        return new ArrayList<>(tasks);
    }


    public void addTask(@NonNull Task task) {
        tasks.add(task);
        invokeUpdate();
    }

    public void removeTask(@NonNull Task task) {
        tasks.remove(task);
        invokeUpdate();
    }

    public void setTasks(@NonNull List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        invokeUpdate();
    }

}



