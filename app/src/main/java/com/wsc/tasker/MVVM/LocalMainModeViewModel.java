package com.wsc.tasker.MVVM;

import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.Task;

import java.util.Collections;
import java.util.List;

public class LocalMainModeViewModel implements IMainModeViewModel{
    private List<Task> tasks;
    private
    @Override
    public List<Task> getCopyTasks() {
        return Collections.emptyList();
    }

    @Override
    public void subscribeOnUpdateTask(Notifier.Subscriber<List<Task>> subscriber) {

    }

    @Override
    public void unsubscribeOnUpdateTask(Notifier.Subscriber<List<Task>> subscriber) {

    }
}
