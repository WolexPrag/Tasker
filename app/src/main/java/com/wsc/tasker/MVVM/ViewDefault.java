package com.wsc.tasker.MVVM;
import androidx.recyclerview.widget.RecyclerView;

import com.wsc.tasker.Task;
import com.wsc.tasker.task.TaskAdapter;

import java.util.List;

public class ViewDefault extends View{
    RecyclerView recyclerView;
    TaskAdapter adapter;

    @Override
    public void displayTasks(List<Task> tasks) {

    }
}
