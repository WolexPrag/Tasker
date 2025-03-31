package com.wsc.tasker.event;

import java.util.List;

public interface IListChangeObserver<T> {
    void subscribeOnSetList(Notifier.Subscriber<List<T>> subscriber);
    void tryUnsubscribeSetList(Notifier.Subscriber<List<T>> subscriber);
    void subscribeOnAddItem(Notifier.Subscriber<Integer> subscriber);
    void tryUnsubscribeOnAddItem(Notifier.Subscriber<Integer> subscriber);
    void subscribeOnRemoveItem(Notifier.Subscriber<Integer> subscriber);
    void tryUnsubscribeOnRemoveItem(Notifier.Subscriber<Integer> subscriber);
    void subscribeOnUpdateItem(Notifier.Subscriber<Integer> subscriber);
    void tryUnsubscribeOnUpdateItem(Notifier.Subscriber<Integer> subscriber);
}
