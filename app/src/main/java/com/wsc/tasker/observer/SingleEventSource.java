package com.wsc.tasker.observer;

import java.util.ArrayList;
import java.util.List;

public class SingleEventSource<Input> extends AbstractEventSource<SingleEventListener<Input>> {
    public SingleEventSource(){
        _listeners = new ArrayList<>();
    }
    public SingleEventSource(ArrayList<MonoEventListener<Output,Input>> subscribers){
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
