package com.lxy.completelayoutdemp;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public  class CompleteLayoutAdapter {
    private Context mContext;
    private ArrayList<String> data = new ArrayList<>();
    private CompleteLayout mCompleteLayout;


    private CompleteLayoutItemClickListener mItemClickListener;
    private CompleteLayoutItemLongClickLisnter mItemLongClickLisnter;



    public CompleteLayoutAdapter(Context context) {
        mContext = context;
    }


    public CompleteLayoutAdapter(Context mContext, List<String> textList) {
        mContext = mContext;
        if (textList != null){
            data.addAll(textList);
        }

    }


    /** 添加一个数据
     * @param text
     */
    public void setData(String text) {
        data.add(text);
        mCompleteLayout.addTextView(text,data.size()-1);
    }

    /** 设置新的数据
     * @param text
     */
    public void setNewData(String text) {
        data.clear();
        mCompleteLayout.removeAllChildViews();
        if (text != null && !text.isEmpty()){
            data.add(text);
            mCompleteLayout.addTextView(text,data.size()-1);
        }
    }

    /** 添加多个数据
     * @param textList
     */
    public void setData(List<String> textList) {
        if (textList == null || textList.size() == 0){
            return;
        }
        data.addAll(textList);
        for (int i = 0;i<textList.size();i++){
            mCompleteLayout.addTextView(textList.get(i),data.size()-textList.size()-i);
        }

    }


    /**  设置新的数据
     * @param textList
     */
    public void setNewData(List<String> textList) {
        data.clear();
        mCompleteLayout.removeAllChildViews();
        if (textList != null && textList.size() != 0){
            this.data.addAll(textList);
            for (int i = 0;i<textList.size();i++){
                mCompleteLayout.addTextView(textList.get(i),i);
            }
        }
    }


    /**
     * 获取数据
     * @return
     */
    public List<String> getData(){
        return data;
    }

    /**
     * 直接绑定布局
     */
    public void bindCompleteLayout(CompleteLayout completeLayout){
        mCompleteLayout = completeLayout;
    }


    /**
     * 点击监听
     * @param itemClickListener
     */
    public void setOnItemClickListener(CompleteLayoutItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    /**
     * 长按监听
     * @param itemClongClickListener
     */
    public void setOnItemLongClickListner(CompleteLayoutItemLongClickLisnter itemClongClickListener){
        mItemLongClickLisnter = itemClongClickListener;
    }


    public CompleteLayoutItemClickListener getItemClickListener() {
        return mItemClickListener;
    }

    public CompleteLayoutItemLongClickLisnter getItemLongClickLisnter() {
        return mItemLongClickLisnter;
    }


    interface CompleteLayoutItemClickListener{
        void onItemClick(CompleteLayoutAdapter adapter, TextView textView,String text, int position);

    }


    interface CompleteLayoutItemLongClickLisnter{
        void onItemLongClick(CompleteLayoutAdapter adapter,TextView textView,String text,int position);
    }





}
