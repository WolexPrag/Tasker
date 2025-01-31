package com.wsc.tasker;

import com.wsc.tasker.task.Task;

import java.util.List;

public abstract class Model {


    public Model(){

    }
    public abstract void LoadTasks(List<Task> tasks);
}
