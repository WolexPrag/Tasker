package com.wsc.tasker.task;

import java.util.ArrayList;
import java.util.List;

public class TestTaskSpaceGiver implements ITaskSpaceGiver{
    @Override
    public TaskSpace get() {
        TaskSpace ret = new TaskSpace();
        List<Task> testData = new ArrayList<>(100);
        for (int i = 0;i < 100;i++){
            Task testTask = new TaskForTest();
            testData.add(testTask);
        }


        return ret;
    }
}
