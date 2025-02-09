package com.wsc.tasker.event;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class LocalSingleSubscriber <T1> implements ISubscriber{
    private Observer<T1> observer;
    private Disposable disposable;
    private INotifier<T1> source;

    public LocalSingleSubscriber(Observer<T1> observer){
        this.observer = observer;
    }

    @Override
    public void setSubscription(Disposable disposable, INotifier source) {
        this.disposable = disposable;
        this.source = source;
    }

    @Override
    public Disposable getSubscription(INotifier notifier) {
        return disposable;
    }

    @Override
    public boolean isSourceSubscribed(INotifier notifier) {
        return source.equals(notifier);
    }

    @Override
    public Observer getObserver() {
        return observer;
    }
}
