package com.wsc.tasker.MVVM;

import androidx.lifecycle.ViewModel;

import com.wsc.tasker.Task;
import java.util.List;
public abstract class View {
    private ViewModel _viewModel;
    public void init(ViewModel viewModel){
        _viewModel = viewModel;
    }
    public abstract void displayTasks(List<Task> tasks);
}
