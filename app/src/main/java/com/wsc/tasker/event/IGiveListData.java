package com.wsc.tasker.event;

import com.wsc.tasker.task.Task;

import java.util.List;

public interface IGiveListData <T>{
    List<T> getCopyData();
    T getCopyItem(Integer position);
}
