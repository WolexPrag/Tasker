package com.wsc.tasker.MVVM;

import com.wsc.tasker.event.INotifier;
import com.wsc.tasker.event.ISubscriber;
import com.wsc.tasker.event.LocalNotifier;
import com.wsc.tasker.event.LocalSingleSubscriber;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskSpace;

import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class LocalTasksMenuViewModel extends ViewModel implements ITaskMenuViewModel {
    private TaskSpace model;
    private List<Task> tasks;
    private INotifier<List<Task>> updateTaskNotifier;
    private ISubscriber<List<Task>> updateTaskSubscriber;


    public LocalTasksMenuViewModel( @NonNull TaskSpace model) {
        this.model = model;
        updateTaskNotifier = new LocalNotifier<>();
        updateTaskSubscriber = new LocalSingleSubscriber<>(new DisposableObserver<List<Task>>() {
            @Override
            public void onNext(@NonNull List<Task> tasks) {
                setTasks(tasks);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        setTasks(model.getCopyTask());
        model.subscribe(updateTaskSubscriber);
    }

    private void invokeUpdateTask() {
        updateTaskNotifier.notify(getCopyTasks());
    }

    @Override
    public void editTask(@NonNull Consumer<Task> action, @NonNull Predicate<Task> condition) {
        tasks.stream().filter(condition).forEach(action);
        invokeUpdateTask();
    }

    @Override
    public void addTask(@NonNull Task task) {
        tasks.add(task);
        invokeUpdateTask();
    }

    @Override
    public void removeTask(@NonNull Predicate<Task> condition) {
        tasks.removeIf(condition);
        invokeUpdateTask();
    }

    @Override
    public void setTasks(@NonNull List<Task> tasks) {
        tasks = new ArrayList<>(tasks);
        invokeUpdateTask();
    }

    @Override
    @NonNull
    public List<Task> getCopyTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public void subscribeOnUpdateTasks(@NonNull ISubscriber<List<Task>> subscriber) {
        updateTaskNotifier.subscribe(subscriber);
    }

    @Override
    public void unsubscribeOnUpdateTasks(@NonNull ISubscriber<List<Task>> subscriber) {
        updateTaskNotifier.unsubscribe(subscriber);
    }
}
