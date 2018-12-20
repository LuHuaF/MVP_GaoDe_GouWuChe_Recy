package com.umeng.soexample.monthlianxi.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.umeng.soexample.monthlianxi.bean.MyData;
import com.umeng.soexample.monthlianxi.callback.MyCallBack;

import java.io.IOException;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ModelImpl implements Model {
    private MyCallBack myCallBack;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MyData bean = (MyData) msg.obj;
            myCallBack.setSuccess(bean);
            myCallBack.setError(bean);
        }
    };


    @Override
    public void setStringStr(String urls, MyCallBack myCallBack) {
        this.myCallBack = myCallBack;

        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder().url(urls).build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Reader reader = response.body().charStream();
                MyData bean = new Gson().fromJson(reader, MyData.class);
                handler.sendMessage(handler.obtainMessage(0,bean));
            }
        });
    }
}
