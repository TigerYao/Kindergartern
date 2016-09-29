package com.junbaole.kindergartern.data.utils.event;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by yaohu on 16/9/27.
 */

public class RxBus {

    private static final RxBus INSTANCE = new RxBus();

    private final Subject<Object,Object> mBusSubject = new SerializedSubject<>(PublishSubject.create());

    public static RxBus getInstance(){
        return INSTANCE;
    }

    public <T> Subscription register(final Class<T> eventCalss, Action1<T> onNext){
        return mBusSubject.filter(new Func1<Object, Boolean>() {
            @Override
            public Boolean call(Object o) {
                return o.getClass().equals(eventCalss);
            }
        }).map(new Func1<Object, T>() {
            @Override
            public T call(Object o) {
                return (T)o;
            }
        }).subscribe(onNext);
    }

    public void post(Object event){
        mBusSubject.onNext(event);
    }
}
