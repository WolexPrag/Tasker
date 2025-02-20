package com.wsc.tasker.MVVM;

import androidx.annotation.NonNull;

import com.wsc.tasker.task.Task;

import java.util.List;

public interface IEditorModeViewModel {
    void addSelectedTask(@NonNull Task task);
    void removeSelectedTask(@NonNull Task task);
    List<Task> getCopySelectedTasks();
}
