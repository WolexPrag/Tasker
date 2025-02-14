package com.wsc.tasker.event;

import java.lang.reflect.Executable;
import java.util.List;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class LocalSingleSubscriber <T1> implements ISubscriber<T1>{
    private DisposableObserver<T1> observer;
    private Subscription subscription;

    public LocalSingleSubscriber(DisposableObserver<T1> observer){
        this.observer = observer;
    }


    @Override
    public boolean isSubscribedOn(INotifier<T1> notifier) {
        return subscription.notifier.equals(notifier);
    }

    @Override
    public DisposableObserver getDisposableObserver() {
        return observer;
    }

    @Override
    public void setSubscription(Subscription<T1> subscription) {
        this.subscription = subscription;
    }

    @Override
    public Disposable getDisposable(INotifier<T1> notifier) throws Exception {
        if(!subscription.notifier.equals(notifier)){
            throw new Exception("This subscriber isn't belong this notifier");
        }
        return subscription.disposable;
    }

}
