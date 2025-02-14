package com.wsc.tasker.event;


public interface INotifier<T> {
    void subscribe(ISubscriber<T> subscriber);
    void unsubscribe(ISubscriber<T> subscriber) throws Exception;
    void notify(T data);
    void clearSubscriptions();
}
