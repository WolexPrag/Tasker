package com.wsc.tasker.task;

import androidx.annotation.NonNull;

import com.wsc.tasker.event.IListOperationsObserver;
import com.wsc.tasker.event.Notifier;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class TaskSpace implements IListOperationsObserver {
    private List<Task> tasks;
    private Notifier<List<Integer>> notifierOnUpdate;
    private Notifier<Integer> notifierOnAdd;
    private Notifier<Integer> notifierOnRemove;
    private Notifier<Map<Integer, Integer>> notifierOnMove;

    public TaskSpace() {
        tasks = new ArrayList<>();
        notifierOnUpdate = new Notifier<>();
        notifierOnAdd = new Notifier<>();
        notifierOnRemove = new Notifier<>();
        notifierOnMove = new Notifier<>();
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
        notifyUpdate(tasks.stream()
                .map(tasks::indexOf)
                .collect(Collectors.toList()));
    }

    public void moveTask(Map<Integer, Integer> positions) {
        for (Map.Entry<Integer, Integer> entry : positions.entrySet()) {
            tasks.add(entry.getKey(), tasks.remove((int) entry.getValue()));
        }
        notifyOnMove(positions);
    }


    public void addTasks(Task task) {
        tasks.add(task);
        notifyAdd(tasks.indexOf(task));
    }

    public void removeTask(int position) {
        tasks.remove(position);
        notifyRemove(position);
    }


    public int getSize() {
        return tasks.size();
    }

    protected void notifyUpdate(List<Integer> positionsUpdate) {
        notifierOnUpdate.notify(positionsUpdate);
    }

    protected void notifyAdd(Integer position) {
        notifierOnAdd.notify(position);
    }

    protected void notifyRemove(Integer position) {
        notifierOnRemove.notify(position);
    }

    protected void notifyOnMove(Map<Integer, Integer> fromTo) {
        notifierOnMove.notify(fromTo);
    }

    @Override
    public void subscribeOnUpdate(Notifier.Subscriber<List<Integer>> subscriber) {
        notifierOnUpdate.subscribe(subscriber);
    }

    @Override
    public boolean tryUnsubscribeOnUpdate(Notifier.Subscriber<List<Integer>> subscriber) {
        return notifierOnUpdate.tryUnsubscribe(subscriber);
    }

    @Override
    public void subscribeOnAdd(Notifier.Subscriber<Integer> subscriber) {
        notifierOnAdd.subscribe(subscriber);
    }

    @Override
    public boolean tryUnsubscribeOnAdd(Notifier.Subscriber<Integer> subscriber) {
        return notifierOnAdd.tryUnsubscribe(subscriber);
    }

    @Override
    public void subscribeOnRemove(Notifier.Subscriber<Integer> subscriber) {
        notifierOnRemove.subscribe(subscriber);
    }

    @Override
    public boolean tryUnsubscribeOnRemove(Notifier.Subscriber<Integer> subscriber) {
        return notifierOnRemove.tryUnsubscribe(subscriber);
    }

    @Override
    public void subscribeOnMove(Notifier.Subscriber<Map<Integer, Integer>> subscriber) {
        notifierOnMove.subscribe(subscriber);
    }

    @Override
    public boolean tryUnsubscribeOnMove(Notifier.Subscriber<Map<Integer, Integer>> subscriber) {
        return notifierOnMove.tryUnsubscribe(subscriber);
    }
}
