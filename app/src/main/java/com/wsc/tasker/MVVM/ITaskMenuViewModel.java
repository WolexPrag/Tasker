package com.wsc.tasker.MVVM;

import androidx.annotation.NonNull;

import com.wsc.tasker.event.ISubscriber;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskSpace;

import java.util.List;
import java.util.function.Predicate;

import io.reactivex.rxjava3.functions.Consumer;

public interface ITaskMenuViewModel {
    void addTask(@NonNull Task task);

    void removeTask(@NonNull Predicate<Task> condition);

    void editTask(@NonNull java.util.function.Consumer<Task> action, @NonNull Predicate<Task> condition);

    void setTasks(@NonNull List<Task> tasks);

    @NonNull
    List<Task> getCopyTasks();

    void subscribeOnUpdateTasks(@NonNull ISubscriber<List<Task>> subscriber);

    void unsubscribeOnUpdateTasks(@NonNull ISubscriber<List<Task>> subscriber);
}
