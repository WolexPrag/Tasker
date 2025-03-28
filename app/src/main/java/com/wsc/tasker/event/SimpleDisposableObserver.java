package com.wsc.tasker.event;

import androidx.annotation.NonNull;

import io.reactivex.rxjava3.observers.DisposableObserver;

public abstract class SimpleDisposableObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(@io.reactivex.rxjava3.annotations.NonNull T value) {
    }

    @Override
    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
