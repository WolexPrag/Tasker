package com.wsc.tasker.event;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.subjects.PublishSubject;

@SuppressWarnings("unused")
public class Notifier<TypeData>{
    private PublishSubject<TypeData> subject;
    private CompositeDisposable disposable;
    public Notifier(){
        subject = PublishSubject.create();
        disposable = new CompositeDisposable();
    }
    public void notify(TypeData value){
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
        private final SimpleDisposableObserver<TypeData> disposableObserver;
        public Subscriber(SimpleDisposableObserver<TypeData> disposableObserver) {
            this.disposableObserver = disposableObserver;
        }
    }
}
