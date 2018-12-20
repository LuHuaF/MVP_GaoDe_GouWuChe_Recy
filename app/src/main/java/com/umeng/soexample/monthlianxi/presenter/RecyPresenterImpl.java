package com.umeng.soexample.monthlianxi.presenter;

import com.umeng.soexample.monthlianxi.callback.MyCallBack;
import com.umeng.soexample.monthlianxi.model.RecyModelImpl;
import com.umeng.soexample.monthlianxi.view.RecyView;


public class RecyPresenterImpl implements RecyPresenter {
    private RecyView iView;
    private RecyModelImpl modelImpls;

    public RecyPresenterImpl(RecyView iView) {
        this.iView = iView;
        modelImpls = new RecyModelImpl();
    }

    @Override
    public void startRequest(String url) {
        modelImpls.getData(url, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                iView.setSuccess(success);
            }

            @Override
            public void setError(Object error) {
                iView.setError(error);
            }

        });
    }

    public void sets() {
        if (modelImpls != null) {
            modelImpls = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
