package com.umeng.soexample.monthlianxi.view;


public interface RecyView<T> {
    void setSuccess(T data);
    void setError(T erros);
}
