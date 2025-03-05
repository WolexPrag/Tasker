package com.wsc.tasker.event;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.subjects.PublishSubject;

@SuppressWarnings("unused")
public class Notifier<TypeData>{
    private PublishSubject<TypeData> subject;
    private CompositeDisposable disposable;
    public Notifier(){
        subject = PublishSubject.create();
    }
    public void notifiy(TypeData value){
        subject.onNext(value);
    }
    public void subscribe(@NonNull Subscriber<TypeData> subscriber){
        disposable.add(subject.subscribeWith(subscriber.disposableObserver));
    }
    public boolean tryUnsubscribe(@NonNull Subscriber<TypeData> subscriber){
        if(!disposable.delete(subscriber.disposableObserver)){
            return false;
        }
        subscriber.disposableObserver.dispose();
        return true;
    }
    public static class Subscriber<TypeData> {
        private final DisposableObserver<TypeData> disposableObserver;
        public Subscriber(DisposableObserver<TypeData> disposableObserver) {
            this.disposableObserver = disposableObserver;
        }
    }
}
