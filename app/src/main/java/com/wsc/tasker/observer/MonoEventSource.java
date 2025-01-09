package com.wsc.tasker.observer;

import java.util.List;
import java.util.ArrayList;

public class MonoEventSource<Output,Input> extends AbstractEventSource<MonoEventListener<Output,Input>>{
    public MonoEventSource(){
        _listeners = new ArrayList<>();
    }
    public MonoEventSource(ArrayList<MonoEventListener<Output,Input>> subscribers){
        _listeners = subscribers;
    }

    public List<Output> triggerEvent(Input value){
        List<Output> results = new ArrayList<>();
        for (MonoEventListener<Output, Input> listener : _listeners)
        {
            results.add(listener.handleEvent(value));
        }
        return results;
    }
    protected class EventNotifier {
        public List<Output> notifyListeners(Input value) {
            return triggerEvent(value);
        }
    }
}

