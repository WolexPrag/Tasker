package com.wsc.tasker.MVVM.filterMode;

import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskSpace;

import java.util.Collections;
import java.util.List;

public class LocalFilterModeViewModel implements IFilterModeViewModel{
    private TaskSpace taskSpace;
    private List<Task> sortedTask;

    private List<Task> sort(List<Task> tasks){
        return tasks
    }


    @Override
    public void setTaskSpace(TaskSpace taskSpace) {
        this.taskSpace = taskSpace;
    }

    @Override
    public List<Task> getSortedTasks(List<Task> tasks) {
        return Collections.emptyList();
    }

    @Override
    public void subscribeOnSetTasks() {

    }
}
