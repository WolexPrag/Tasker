package com.wsc.tasker.event;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public interface ISubscriber<T> {
    class Subscription<T>{
        public Disposable disposable;
        public INotifier<T> notifier;
        public Subscription(Disposable disposable,INotifier<T> notifier){
            this.disposable = disposable;
            this.notifier = notifier;
        }
    }
    boolean isSubscribedOn(INotifier<T> notifier);
    DisposableObserver<T> getDisposableObserver();
    Disposable getDisposable(INotifier<T> notifier) throws Exception;
    void setSubscription(Subscription<T> subscription);

}
