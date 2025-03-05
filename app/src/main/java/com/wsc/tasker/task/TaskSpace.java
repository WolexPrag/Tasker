package com.wsc.tasker.task;

import androidx.annotation.NonNull;

import com.wsc.tasker.event.Notifier;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.functions.Consumer;

public class TaskSpace {
    private List<Task> tasks;
    private Notifier<List<Task>> notifierOnUpdate;
    public TaskSpace(){
        tasks = new ArrayList<>();
        notifierOnUpdate = new Notifier<>();
    }
    public TaskSpace(TaskSpace taskSpace){
        this.tasks = new ArrayList<>(taskSpace.tasks);

    }
    private void notifyUpdate(){

    }
    @NonNull
    public List<Task> getCopyTask(){
        return new ArrayList<>(tasks);
    }
    public void setTasks(List<Task> tasks){

    }
    public void editTask(Consumer<List<Task>> action) throws Throwable {
        action.accept(tasks);

    }

}
