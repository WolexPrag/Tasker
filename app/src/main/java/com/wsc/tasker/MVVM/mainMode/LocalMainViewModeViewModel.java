package com.wsc.tasker.MVVM.mainMode;

import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskSpace;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class LocalMainViewModeViewModel implements IMainViewModeViewModel {
    private TaskSpace taskSpace;

    @Override
    public List<Task> getCopyTasks() {
        return Collections.emptyList();
    }

    @Override
    public void setTaskSpace(TaskSpace taskSpace) {
        this.taskSpace = taskSpace;
    }

    @Override
    public void subscribeOnSetList(Notifier.Subscriber<List<Task>> subscriber) {

    }

    @Override
    public void tryUnsubscribeSetList(Notifier.Subscriber<List<Task>> subscriber) {

    }

    @Override
    public void subscribeOnAddItem(Notifier.Subscriber<Integer> subscriber) {

    }

    @Override
    public void tryUnsubscribeOnAddItem(Notifier.Subscriber<Integer> subscriber) {

    }

    @Override
    public void subscribeOnRemoveItem(Notifier.Subscriber<Integer> subscriber) {

    }

    @Override
    public void tryUnsubscribeOnRemoveItem(Notifier.Subscriber<Integer> subscriber) {

    }

    @Override
    public void subscribeOnUpdateItem(Notifier.Subscriber<Integer> subscriber) {

    }

    @Override
    public void tryUnsubscribeOnUpdateItem(Notifier.Subscriber<Integer> subscriber) {

    }
}
