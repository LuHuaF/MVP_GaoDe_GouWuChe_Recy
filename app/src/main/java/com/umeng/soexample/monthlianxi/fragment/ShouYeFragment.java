package com.umeng.soexample.monthlianxi.fragment;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.monthlianxi.gaode.Main1Activity;
import com.umeng.soexample.monthlianxi.R;
import com.umeng.soexample.monthlianxi.adapter.RecyAdapter;
import com.umeng.soexample.monthlianxi.base.BaseFragment;
import com.umeng.soexample.monthlianxi.bean.News;
import com.umeng.soexample.monthlianxi.presenter.RecyPresenterImpl;
import com.umeng.soexample.monthlianxi.view.RecyView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;


public class ShouYeFragment extends BaseFragment implements RecyView {
    private BGABanner mBGABanner;
    private RecyclerView recy;
    private String mUrl = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private List<News.DataBean> datas = new ArrayList<>();
    private RecyAdapter adapter;
    private TextView gao;
    private RecyPresenterImpl recyPresenter;
    private String[] picUrl = {
            "http://f.expoon.com/sub/news/2016/01/21/887844_230x162_0.jpg",
            "http://f.expoon.com/sub/news/2016/01/21/580828_230x162_0.jpg",
            "http://f.expoon.com/sub/news/2016/01/21/745921_230x162_0.jpg"
};
    private String[] picDes = {"","",""};
    private List<String> imgList;

    @Override
    protected void initData() {
        initViews();
        adapter = new RecyAdapter(datas,getActivity());
        recy.setAdapter(adapter);
        recyPresenter = new RecyPresenterImpl(this);
        recyPresenter.startRequest(mUrl);
        imgList = new ArrayList<>();
        List<String> imgDesc = new ArrayList<>();

        for (int a = 0; a < picUrl.length; a++) {
            imgList.add(picUrl[a]);
            imgDesc.add(picDes[a]);
        }
        mBGABanner.setData(imgList, imgDesc);
        mBGABanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                Glide.with(getActivity()).load(imgList.get(position)).into((ImageView) itemView);
            }
        });

    }

    private void initViews() {
        mBGABanner = getActivity().findViewById(R.id.banner);
        recy = getActivity().findViewById(R.id.recy);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recy.setLayoutManager(staggeredGridLayoutManager);
        gao = getActivity().findViewById(R.id.gao);
        gao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Main1Activity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_shouye;
    }


    @Override
    public void setSuccess(Object data) {
        News news = (News) data;
        datas.addAll(news.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setError(Object erros) {

    }
}
