package com.wsc.tasker.task;

import androidx.annotation.NonNull;

import com.wsc.tasker.event.Notifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class TaskSpace {
    private List<Task> tasks;
    private Notifier<List<Task>> notifierOnUpdateTasks;
    private Notifier<Integer> notifierOnAddTask;
    private Notifier<Integer> notifierOnRemoveTask;
    private Notifier<Integer> notifierOnSetTask;

    public TaskSpace() {
        tasks = new ArrayList<>();
        notifierOnUpdateTasks = new Notifier<>();
        notifierOnAddTask = new Notifier<>();
        notifierOnRemoveTask = new Notifier<>();
        notifierOnSetTask = new Notifier<>();
    }

    private TaskSpace(TaskSpace taskSpace) {
        super();
        setTasks(taskSpace.getCopyTask());

    }

    public TaskSpace getCopy() {
        return new TaskSpace(this);
    }


    @NonNull
    public List<Task> getCopyTask() {
        return new ArrayList<>(tasks);
    }

    public Task getCopyItem(int position) {
        return tasks.get(position).getCopy();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyUpdateTasks();
    }

    public void moveTask(int fromPosition, int toPosition) {
        Collections.swap(tasks, fromPosition, toPosition);
    }

    public void addTasks(Task task) {
        tasks.add(task);
        notifyAddTask(tasks.indexOf(task));
    }

    public void removeTask(int position) {
        tasks.remove(position);
        notifyRemoveTask(position);
    }

    private void notifyUpdateTasks() {
        notifierOnUpdateTasks.notify(getCopyTask());
    }

    public void subscribeOnUpdateTasks(Notifier.Subscriber<List<Task>> subscriber) {
        notifierOnUpdateTasks.subscribe(subscriber);
    }

    public boolean tryUnsubscribeOnUpdateTasks(Notifier.Subscriber<List<Task>> subscriber) {
        return notifierOnUpdateTasks.tryUnsubscribe(subscriber);
    }

    private void notifyAddTask(int position) {
        notifierOnAddTask.notify(position);
    }

    public void subscribeOnAddTask(Notifier.Subscriber<Integer> subscriber) {
        notifierOnAddTask.subscribe(subscriber);
    }

    public boolean tryUnsubscribeOnAddTask(Notifier.Subscriber<Integer> subscriber) {
        return notifierOnAddTask.tryUnsubscribe(subscriber);
    }

    private void notifyRemoveTask(int position) {
        notifierOnRemoveTask.notify(position);
    }

    public void subscribeOnRemoveTask(Notifier.Subscriber<Integer> subscriber) {
        notifierOnRemoveTask.subscribe(subscriber);
    }

    public boolean tryUnsubscribeOnRemoveTask(Notifier.Subscriber<Integer> subscriber) {
        return notifierOnRemoveTask.tryUnsubscribe(subscriber);
    }

    private void notifySetTask(int position) {
        notifierOnSetTask.notify(position);
    }

    public void subscribeOnSetTask(Notifier.Subscriber<Integer> subscriber) {
        notifierOnSetTask.subscribe(subscriber);
    }

    public boolean tryUnsubscribeOnSetTask(Notifier.Subscriber<Integer> subscriber) {
        return notifierOnSetTask.tryUnsubscribe(subscriber);
    }

    public int getSize() {
        return tasks.size();
    }
}
