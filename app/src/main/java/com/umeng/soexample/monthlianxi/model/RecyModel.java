package com.umeng.soexample.monthlianxi.model;


import com.umeng.soexample.monthlianxi.callback.MyCallBack;

public interface RecyModel {
    void getData(String url,MyCallBack callBack);
}
