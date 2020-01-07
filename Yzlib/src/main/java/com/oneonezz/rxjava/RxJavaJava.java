package com.oneonezz.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ${Justin} on 2019/10/28.
 */
public class RxJavaJava {
    private static final String TAG = RxJavaJava.class.getSimpleName();

    public static void main(String[] args) {
        //创建被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                emitter.onNext("1111");
                emitter.onNext("2222");
                emitter.onNext("3333");
                emitter.onNext("4444");
                //emitter.onError(new Throwable("abc"));
                //emitter.onComplete();
            }
        });

        //创建观察者
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {//关闭线程
                System.out.println("onSubscribe: " );
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: "+ s );
            }

            @Override
            public void onError(Throwable e) {//失败
                System.out.println("onError: "+e.getMessage() );
            }

            @Override
            public void onComplete() {//成功
                System.out.println("onComplete: " );
            }
        };
        //被观察者订阅观察者
        observable.subscribe(observer);

//        //线程切换
//        observable
//                //被订阅者在子线程中
//                .subscribeOn(Schedulers.io())
//                //订阅者在主线程中
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//
//        //观察中可以重复指定线程
//        observable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())//主
//                .observeOn(Schedulers.io())//子
//                .observeOn(AndroidSchedulers.mainThread())//主
//                .subscribe(observer);

    }
}
