package com.wsc.tasker.MVVM;

import androidx.annotation.NonNull;

import com.wsc.tasker.event.ISubscriber;
import com.wsc.tasker.task.Task;

import java.util.List;

public interface IMainModeViewModel {
    void createNewTask();

    @NonNull
    List<Task> getCopyTasks();

    void subscribeOnUpdateTasks(@NonNull ISubscriber<List<Task>> subscriber);

    void unsubscribeOnUpdateTasks(@NonNull ISubscriber<List<Task>> subscriber) throws Exception;
}
