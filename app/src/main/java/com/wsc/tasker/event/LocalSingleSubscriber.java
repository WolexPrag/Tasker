package com.wsc.tasker.event;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class LocalSingleSubscriber <T1> implements ISubscriber{
    private DisposableObserver<T1> observer;
    private Disposable disposable;
    private INotifier<T1> source;

    public LocalSingleSubscriber(DisposableObserver<T1> observer){
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
    public DisposableObserver<T1> getObserver() {
        return observer;
    }


}
