package com.wsc.tasker.MVVM.mainMode;

import com.wsc.tasker.MVVM.IModeViewModel;
import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskSpace;

import java.util.List;

@SuppressWarnings("unused")
public interface IMainViewModeViewModel extends IModeViewModel {
    List<Task> getCopyTasks();
}
