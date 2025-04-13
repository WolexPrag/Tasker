package com.wsc.tasker.event;

import java.util.List;
import java.util.Map;

public abstract class ListOperationsSubscriber {
    protected Notifier.Subscriber<List<Integer>> subscriberOnUpdate;
    protected Notifier.Subscriber<Integer> subscriberOnAdd;
    protected Notifier.Subscriber<Integer> subscriberOnRemove;
    protected Notifier.Subscriber<Map<Integer, Integer>> subscriberOnMove;

    public ListOperationsSubscriber() {
        subscriberOnUpdate = new Notifier.Subscriber<List<Integer>>(new SimpleDisposableObserver<List<Integer>>() {
            @Override
            public void onNext(List<Integer> value) {
                onUpdate(value);
            }
        });
        subscriberOnAdd = new Notifier.Subscriber<>(new SimpleDisposableObserver<Integer>() {
            @Override
            public void onNext(Integer value) {

            }
        });
    }

    public void subscribe(IListOperationsObserver observer) {
        observer.subscribeOnUpdate(subscriberOnUpdate);
        observer.subscribeOnAdd(subscriberOnAdd);
        observer.subscribeOnRemove(subscriberOnRemove);
        observer.subscribeOnMove(subscriberOnMove);
    }

    public void onUpdate(List<Integer> positionsUpdate) {

    }
    public void onAdd(Integer positionAdd){

    }
    public void onRemove(Integer positionRemove){

    }
    public void onMove(Map<Integer,Integer> positionFromTo){

    }
}
