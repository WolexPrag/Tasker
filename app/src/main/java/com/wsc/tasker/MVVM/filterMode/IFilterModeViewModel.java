package com.wsc.tasker.MVVM.filterMode;

import com.wsc.tasker.MVVM.IModeViewModel;
import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.Task;

import java.util.List;

public interface IFilterModeViewModel extends IModeViewModel {
    List<Task> getSortedTasks(List<Task> tasks);
    void subscribeOnSetTasks(Notifier.Subscriber<List<>>);

}
