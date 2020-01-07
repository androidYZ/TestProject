package com.oneonezz.rxjava;

import com.oneonezz.utils.L;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ${Justin} on 2019/10/28.
 */
public class RxJavaTestNew {
    private static final String TAG = RxJavaTestNew.class.getSimpleName();

    public static void main(String[] args) {
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("123");
                emitter.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                L.i("oo- onSubscribe -> : ");
            }

            @Override
            public void onNext(String s) {
                L.i("oo- onNext -> : ");
            }

            @Override
            public void onError(Throwable e) {
                L.i("oo- onError -> : ");
            }

            @Override
            public void onComplete() {
                L.i("oo- onComplete -> : ");

            }
        };

        stringObservable.subscribe(observer);
    }
}
