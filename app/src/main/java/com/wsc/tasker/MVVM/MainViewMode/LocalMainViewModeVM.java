package com.wsc.tasker.MVVM.MainViewMode;

import com.wsc.tasker.event.Empty;
import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskSpace;

import java.util.List;
import java.util.Map;

public class LocalMainViewModeVM implements IMainViewModeViewModel {
    private TaskSpace taskSpace;
    private Notifier<Empty> notifierOnSelectTaskSpace;
    public LocalMainViewModeVM(){
        notifierOnSelectTaskSpace = new Notifier<>();
    }
    public LocalMainViewModeVM(TaskSpace taskSpace){
        super();
        selectTaskSpace(taskSpace);
    }

    @Override
    public void selectTaskSpace(TaskSpace taskSpace) {
        this.taskSpace = taskSpace;
    }

    @Override
    public List<Task> getCopyData() {
        return taskSpace.getCopyTask();
    }

    @Override
    public Task getCopyItem(Integer position) {
        return taskSpace.getCopyItem(position);
    }

    @Override
    public void subscribeOnSelectTaskSpace(Notifier.Subscriber<Empty> subscriber) {
        notifierOnSelectTaskSpace.subscribe(subscriber);
    }

    @Override
    public boolean tryUnsubscribeOnSelectTaskSpace(Notifier.Subscriber<Empty> subscriber) {
        return notifierOnSelectTaskSpace.tryUnsubscribe(subscriber);
    }

    @Override
    public void subscribeOnUpdate(Notifier.Subscriber<List<Integer>> subscriber) {
        taskSpace.subscribeOnUpdate(subscriber);
    }

    @Override
    public boolean tryUnsubscribeOnUpdate(Notifier.Subscriber<List<Integer>> subscriber) {
        return taskSpace.tryUnsubscribeOnUpdate(subscriber);
    }

    @Override
    public void subscribeOnAdd(Notifier.Subscriber<Integer> subscriber) {
        taskSpace.subscribeOnAdd(subscriber);
    }

    @Override
    public boolean tryUnsubscribeOnAdd(Notifier.Subscriber<Integer> subscriber) {
        return taskSpace.tryUnsubscribeOnAdd(subscriber);
    }

    @Override
    public void subscribeOnRemove(Notifier.Subscriber<Integer> subscriber) {
        taskSpace.subscribeOnAdd(subscriber);
    }

    @Override
    public boolean tryUnsubscribeOnRemove(Notifier.Subscriber<Integer> subscriber) {
        return taskSpace.tryUnsubscribeOnAdd(subscriber);
    }

    @Override
    public void subscribeOnMove(Notifier.Subscriber<Map<Integer, Integer>> subscriber) {
        taskSpace.subscribeOnMove(subscriber);
    }

    @Override
    public boolean tryUnsubscribeOnMove(Notifier.Subscriber<Map<Integer, Integer>> subscriber) {
        return taskSpace.tryUnsubscribeOnMove(subscriber);
    }
}
