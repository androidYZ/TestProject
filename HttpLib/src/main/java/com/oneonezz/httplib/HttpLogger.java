package com.oneonezz.httplib;

import okhttp3.logging.HttpLoggingInterceptor;

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        System.out.println("T.T->log: " + message);
        //okHttp的详细日志会打印出来
    }
}