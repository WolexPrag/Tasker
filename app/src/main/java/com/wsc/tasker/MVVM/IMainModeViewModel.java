package com.wsc.tasker.MVVM;

import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.Task;

import java.util.List;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public interface IMainModeViewModel {
    List<Task> getCopyTasks();
    void subscribeOnUpdateTask(Notifier.Subscriber<List<Task>> subscriber);
    void unsubscribeOnUpdateTask(Notifier.Subscriber<List<Task>> subscriber);
}
