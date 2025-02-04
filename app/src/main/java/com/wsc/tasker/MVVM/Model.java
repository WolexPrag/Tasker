package com.wsc.tasker.MVVM;

import com.wsc.tasker.Task;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.subjects.PublishSubject;


public class Model {
    private PublishSubject<List<Task>> tasksUpdate;
    private List<Task> tasks;

    public Model() {
        tasksUpdate = PublishSubject.create();
    }

    public Disposable subscribeOnTasksUpdate(DisposableObserver<List<Task>> action) {
        return tasksUpdate.subscribeWith(action);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        tasksUpdate.onNext(getCopyTasks());
    }

    public List<Task> getCopyTasks() {
        return new ArrayList<>(tasks);
    }
}
