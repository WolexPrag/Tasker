package com.wsc.tasker.MVVM;

import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskSpace;

import java.util.List;

@SuppressWarnings("unused")
public class LocalMainModeViewModel implements IMainModeViewModel {
    private TaskSpace taskSpace;

    @Override
    public void Init(TaskSpace taskSpace) {
        this.taskSpace = taskSpace;
    }

    @Override
    public List<Task> getCopyTasks() {
        return taskSpace.getCopyTask();
    }

    @Override
    public void subscribeOnUpdateTasks(Notifier.Subscriber<List<Task>> subscriber) {
        taskSpace.subscribeOnUpdateTasks(subscriber);
    }

    @Override
    public void unsubscribeOnUpdateTasks(Notifier.Subscriber<List<Task>> subscriber) {
        taskSpace.subscribeOnUpdateTasks(subscriber);
    }

    @Override
    public void subscribeOnAddTask(Notifier.Subscriber<Integer> subscriber) {
        taskSpace.subscribeOnAddTask(subscriber);
    }

    @Override
    public void unsubscribeOnAddTask(Notifier.Subscriber<Integer> subscriber) {
        taskSpace.tryUnsubscribeOnAddTask(subscriber);
    }

    @Override
    public void subscribeOnRemoveTask(Notifier.Subscriber<Integer> subscriber) {
        taskSpace.subscribeOnRemoveTask(subscriber);
    }

    @Override
    public void unsubscribeOnRemoveTask(Notifier.Subscriber<Integer> subscriber) {
        taskSpace.tryUnsubscribeOnRemoveTask(subscriber);
    }

    @Override
    public void subscribeOnSetTask(Notifier.Subscriber<Integer> subscriber) {
        taskSpace.subscribeOnSetTask(subscriber);
    }

    @Override
    public void unsubscribeOnUpdateTask(Notifier.Subscriber<Integer> subscriber) {
        taskSpace.tryUnsubscribeOnSetTask(subscriber);
    }
}
