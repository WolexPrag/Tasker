package com.wsc.tasker.MVVM.SelectMode;

import com.wsc.tasker.event.IListOperationsHandler;

import java.util.List;

public interface ISelectModeViewModel extends IListOperationsHandler<Integer> {
    List<Integer> getCopySelectedPositions();
}
