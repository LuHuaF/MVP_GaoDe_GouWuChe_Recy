package com.umeng.soexample.monthlianxi.fragment;


import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.umeng.soexample.monthlianxi.R;
import com.umeng.soexample.monthlianxi.adapter.MyAdapter;
import com.umeng.soexample.monthlianxi.base.BaseFragment;
import com.umeng.soexample.monthlianxi.bean.MyData;
import com.umeng.soexample.monthlianxi.presenter.IPresenterImpl;
import com.umeng.soexample.monthlianxi.view.IView;

import java.util.List;


public class GwcFragment extends BaseFragment implements IView {
    private CheckBox Check_All;
    private TextView All_Price;
    private TextView Go_To_JS;
    private ExpandableListView Expand_View;

    private String mUrl = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
    private MyAdapter mAdapter;
    private IPresenterImpl iPresenter;

    @Override
    protected void initData() {
        Check_All = getActivity().findViewById(R.id.Check_All);
        All_Price = getActivity().findViewById(R.id.All_Price);
        Go_To_JS = getActivity().findViewById(R.id.Go_To_JS);
        Expand_View = getActivity().findViewById(R.id.Expand_View);
        //去掉自带的小箭头
        Expand_View.setGroupIndicator(null);
        iPresenter = new IPresenterImpl(this);
        iPresenter.getString(mUrl);

    }
    //刷新底部数据
    private void flushBottomLayout() {
        boolean allGoods = mAdapter.isAllGoods();
        Check_All.setChecked(allGoods);
        float allGoodsPrice = mAdapter.getAllGoodsPrice();
        int allGoodsNumber = mAdapter.getAllGoodsNumber();
        All_Price.setText("价格：" + allGoodsPrice);
        Go_To_JS.setText("去结算（" + allGoodsNumber + "）");
    }


    @Override
    public void getData(Object data) {
        MyData bean = (MyData) data;
        List<MyData.DataBean> list = bean.getData();
        mAdapter = new MyAdapter(list,getActivity());
        Expand_View.setAdapter(mAdapter);
        for (int i = 0; i <list.size() ; i++) {
            Expand_View.expandGroup(i);
        }
        mAdapter.setCallback(new MyAdapter.AdapterCallback() {
            @Override
            public void setGroupCheck(int groupPosition) {
                //是否被全选中
                boolean childAllCheck = mAdapter.isChildAllCheck(groupPosition);
                mAdapter.childAllCheck(groupPosition,!childAllCheck);
                mAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }

            @Override
            public void setChildCheck(int groupPosition, int childPosition) {
                //得到你要点击的商品Child是否被选中
                boolean childChecked = mAdapter.isChildChecked(groupPosition, childPosition);
                mAdapter.setChildChecked(groupPosition,childPosition,!childChecked);
                mAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }

            @Override
            public void setNumber(int groupPosition, int childPosition, int number) {
                mAdapter.setShangPingNumber(groupPosition, childPosition, number);
                mAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }
        });
        Check_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allGoods = mAdapter.isAllGoods();
                mAdapter.setAllGoodsIsChecked(!allGoods);
                mAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_gwc;
    }


}

