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

public class LocalMainModeViewModel extends ViewModel implements IMainModeViewModel {
    private TaskSpace model;
    private List<Task> tasks;
    private INotifier<List<Task>> updateTaskNotifier;
    private ISubscriber<List<Task>> updateTaskSubscriber;

    public LocalMainModeViewModel(@NonNull TaskSpace model) {
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
    public void createNewTask() {
        addTask(new Task().setName("New Task"));
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
    public void unsubscribeOnUpdateTasks(@NonNull ISubscriber<List<Task>> subscriber) throws Exception {
        updateTaskNotifier.unsubscribe(subscriber);
    }

    private void addTask(@NonNull Task task) {
        tasks.add(task);
        invokeUpdateTask();
    }

    private void setTasks(@NonNull List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        invokeUpdateTask();
    }

}
