package com.wsc.tasker.MVVM;

import com.wsc.tasker.event.ITaskListChangeObserver;
import com.wsc.tasker.task.TaskSpace;

public interface IModeViewModel extends ITaskListChangeObserver {
    default void init(TaskSpace taskSpace){
        setTaskSpace(taskSpace);
    }
    void setTaskSpace(TaskSpace taskSpace);
}
