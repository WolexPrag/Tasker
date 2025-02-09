package com.wsc.tasker.event;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class LocalNotifier<T1> implements INotifier<T1> {
    private final PublishSubject<T1> subject;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LocalNotifier() {
        subject = PublishSubject.create();
    }

    @Override
    public void subscribe(ISubscriber<T1> subscriber) {
        Disposable disposable = subject.subscribe();
        subscriber.setSubscription(disposable,this);
        compositeDisposable.add(disposable);

    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        Disposable disposable = subscriber.getSubscription(this);
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            compositeDisposable.remove(disposable);
        }
    }

    @Override
    public void notify(T1 value) {
        subject.onNext(value);
    }

    @Override
    public void clearSubscriptions() {
        compositeDisposable.clear();
    }
}
