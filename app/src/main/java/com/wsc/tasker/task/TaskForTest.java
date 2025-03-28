package com.wsc.tasker.task;

public class TaskForTest extends Task{
    public TaskForTest(){
        super();
    }
    @Override
    public Task getCopy() {
        return new TaskForTest();
    }

    @Override
    public boolean isComplete() {
        return true;
    }
}
