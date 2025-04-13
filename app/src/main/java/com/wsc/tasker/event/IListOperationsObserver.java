package com.wsc.tasker.event;

import java.util.List;
import java.util.Map;

public interface IListOperationsObserver {
     void subscribeOnUpdate(Notifier.Subscriber<List<Integer>> subscriber);

     boolean tryUnsubscribeOnUpdate(Notifier.Subscriber<List<Integer>> subscriber);

     void subscribeOnAdd(Notifier.Subscriber<Integer> subscriber);

     boolean tryUnsubscribeOnAdd(Notifier.Subscriber<Integer> subscriber);

     void subscribeOnRemove(Notifier.Subscriber<Integer> subscriber);

     boolean tryUnsubscribeOnRemove(Notifier.Subscriber<Integer> subscriber);

     void subscribeOnMove(Notifier.Subscriber<Map<Integer, Integer>> subscriber);

     boolean tryUnsubscribeOnMove(Notifier.Subscriber<Map<Integer, Integer>> subscriber);
}
