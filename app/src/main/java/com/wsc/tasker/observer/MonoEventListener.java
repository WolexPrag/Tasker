package com.wsc.tasker.observer;

@FunctionalInterface
public interface MonoEventListener<Output, Input>{
    Output handleEvent(Input value);
}
