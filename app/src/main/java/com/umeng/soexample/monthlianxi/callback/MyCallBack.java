package com.umeng.soexample.monthlianxi.callback;

public interface MyCallBack<T> {
    void setSuccess(T success);
    void setError(T error);
}
