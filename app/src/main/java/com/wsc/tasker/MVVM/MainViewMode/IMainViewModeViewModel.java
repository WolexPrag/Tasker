package com.wsc.tasker.MVVM.MainViewMode;

import com.wsc.tasker.event.Empty;
import com.wsc.tasker.event.IListTaskOperationsHandler;
import com.wsc.tasker.event.Notifier;
import com.wsc.tasker.task.TaskSpace;

public interface IMainViewModeViewModel extends IListTaskOperationsHandler {
    void selectTaskSpace(TaskSpace taskSpace);
    void subscribeOnSelectTaskSpace(Notifier.Subscriber<Empty> subscriber);
    boolean tryUnsubscribeOnSelectTaskSpace(Notifier.Subscriber<Empty> subscriber);
}
