package com.umeng.soexample.monthlianxi.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.monthlianxi.R;
import com.umeng.soexample.monthlianxi.bean.News;

import java.util.List;
import java.util.Random;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {
    private List<News.DataBean> mList;
    private Context context;
    private TextView titlse;
    private ImageView imgs;

    public RecyAdapter(List<News.DataBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        News.DataBean dataBean = mList.get(i);
        /*瀑布流效果
        ViewGroup.LayoutParams params = viewHolder.itemView.getLayoutParams();
        Random random = new Random();
        int height = random.nextInt(300)+300;
        params.height = height;
        viewHolder.itemView.setLayoutParams(params);*/
        Glide.with(context).load(dataBean.getPic_url()).into(imgs);
        titlse.setText(dataBean.getNews_title()+"");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgs = itemView.findViewById(R.id.imgs);
            titlse = itemView.findViewById(R.id.titlse);
        }
    }
}
