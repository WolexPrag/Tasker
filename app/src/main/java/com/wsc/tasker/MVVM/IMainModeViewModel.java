package com.wsc.tasker.MVVM;

import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskSpace;

import java.util.List;

@SuppressWarnings("unused")
public interface IMainModeViewModel {

    void Init(TaskSpace taskSpace);

    List<Task> getCopyTasks();

    void subscribeOnUpdateTasks(Notifier.Subscriber<List<Task>> subscriber);

    void unsubscribeOnUpdateTasks(Notifier.Subscriber<List<Task>> subscriber);

    void subscribeOnAddTask(Notifier.Subscriber<Integer> subscriber);

    void unsubscribeOnAddTask(Notifier.Subscriber<Integer> subscriber);

    void subscribeOnRemoveTask(Notifier.Subscriber<Integer> subscriber);

    void unsubscribeOnRemoveTask(Notifier.Subscriber<Integer> subscriber);

    void subscribeOnSetTask(Notifier.Subscriber<Integer> subscriber);

    void unsubscribeOnUpdateTask(Notifier.Subscriber<Integer> subscriber);
}
