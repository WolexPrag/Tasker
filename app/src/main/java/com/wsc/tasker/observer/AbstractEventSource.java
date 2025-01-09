package com.wsc.tasker.observer;

import java.util.ArrayList;
import java.util.List;

public class AbstractEventSource<T1> {
    protected List<T1> _listeners;
    public AbstractEventSource(){
        _listeners = new ArrayList<>();
    }
    public AbstractEventSource(ArrayList<T1> listeners){
        _listeners = listeners;
    }
    public List<T1> getCopyListeners(){
        return new ArrayList<>(_listeners);
    }
    public void addListener(T1 listener){
        _listeners.add(listener);
    }
    public void removeListener(T1 listener){
        if(_listeners.contains(listener)){
            throw new IllegalStateException("this listener isn't located in this EventSource");
        }
        _listeners.remove(listener);
    }
}
