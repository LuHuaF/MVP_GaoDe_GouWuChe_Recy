package com.umeng.soexample.monthlianxi.model;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.umeng.soexample.monthlianxi.bean.News;
import com.umeng.soexample.monthlianxi.callback.MyCallBack;
import com.umeng.soexample.monthlianxi.util.OkHttpUtils;

import java.io.IOException;


public class RecyModelImpl implements RecyModel {
    private MyCallBack callBack;
    @Override
    public void getData(String url, MyCallBack callBack) {
        this.callBack = callBack;
        new MyTask().execute(url);
    }

    class MyTask extends AsyncTask<String ,Void ,News> {
        @Override
        protected News doInBackground(String... strings) {
            try {
                String jsonStr = OkHttpUtils.getInstance().get(strings[0]);
                Gson gson = new Gson();
                News myData = gson.fromJson(jsonStr, News.class);
                return myData;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(News news) {
            super.onPostExecute(news);
            callBack.setSuccess(news);
        }
    }
}
