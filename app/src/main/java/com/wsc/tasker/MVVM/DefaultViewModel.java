package com.wsc.tasker.MVVM;

import com.wsc.tasker.Task;
import androidx.lifecycle.ViewModel;
import java.util.List;

import io.reactivex.rxjava3.observers.DisposableObserver;

public abstract class DefaultViewModel {
    private Model _model;
    private DisposableObserver<List<Task>> tasksUpdateObserver;
    private List<Task> tasks;
    private List<Task> selected;
    public DefaultViewModel(Model model){
        _model = model;
    }

}
