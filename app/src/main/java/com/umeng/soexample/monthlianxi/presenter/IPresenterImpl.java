package com.umeng.soexample.monthlianxi.presenter;

import com.umeng.soexample.monthlianxi.callback.MyCallBack;
import com.umeng.soexample.monthlianxi.model.ModelImpl;
import com.umeng.soexample.monthlianxi.view.IView;


public class IPresenterImpl implements IPresenter {
    private IView iView;
    private ModelImpl model;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        model = new ModelImpl();
    }

    @Override
    public void getString(String urls) {
        model.setStringStr(urls, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                iView.getData(success);
            }

            @Override
            public void setError(Object error) {
                iView.getData(error);
            }
        });

    }
}
