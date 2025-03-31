package com.wsc.tasker.MVVM.addMode;

import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskForTest;
import com.wsc.tasker.task.TaskSpace;

public class TestTaskAddModeViewModel implements IAddModeViewModel{
    private TaskSpace taskSpace;
    @Override
    public void init(TaskSpace taskSpace) {
        this.taskSpace = taskSpace;
    }

    @Override
    public void setTaskSpace(TaskSpace taskSpace) {

    }

    private Task getTestTask(){
        return new TaskForTest();
    }
    @Override
    public void acceptAdd() {
        taskSpace.addTasks(getTestTask());
    }

    @Override
    public void subscribeOnSetList(Notifier.Subscriber subscriber) {

    }

    @Override
    public void tryUnsubscribeSetList(Notifier.Subscriber subscriber) {

    }

    @Override
    public void subscribeOnAddItem(Notifier.Subscriber subscriber) {

    }

    @Override
    public void tryUnsubscribeOnAddItem(Notifier.Subscriber subscriber) {

    }

    @Override
    public void subscribeOnRemoveItem(Notifier.Subscriber subscriber) {

    }

    @Override
    public void tryUnsubscribeOnRemoveItem(Notifier.Subscriber subscriber) {

    }

    @Override
    public void subscribeOnUpdateItem(Notifier.Subscriber subscriber) {

    }

    @Override
    public void tryUnsubscribeOnUpdateItem(Notifier.Subscriber subscriber) {

    }
}
