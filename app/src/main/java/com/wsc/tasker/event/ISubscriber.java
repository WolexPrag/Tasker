package com.wsc.tasker.event;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public interface ISubscriber<T> {
    void setSubscription(Disposable disposable, INotifier<T> source);

    Disposable getSubscription(INotifier<T> notifier);
    boolean isSourceSubscribed(INotifier<T> notifier);

    DisposableObserver<T> getObserver();

}
