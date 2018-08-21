package com.lxy.completelayoutdemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CompleteLayout mCompleteLayout;
    private CompleteLayoutAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCompleteLayout = findViewById(R.id.completeLayout);
        mCompleteLayout.setAdapter(mAdapter = new CompleteLayoutAdapter(this));
        mAdapter.setOnItemClickListener(new CompleteLayoutAdapter.CompleteLayoutItemClickListener() {
            @Override
            public void onItemClick(CompleteLayoutAdapter adapter, TextView textView, String text, int position) {
                Toast.makeText(MainActivity.this, "点击了第"+position+"个按钮  当前按钮内容为："+text, Toast.LENGTH_SHORT).show();
            }
        });


        mAdapter.setOnItemLongClickListner(new CompleteLayoutAdapter.CompleteLayoutItemLongClickLisnter() {
            @Override
            public void onItemLongClick(CompleteLayoutAdapter adapter, TextView textView, String text, int position) {
                Toast.makeText(MainActivity.this, "长按第"+position+"个按钮  当前按钮内容为："+text, Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setData("大黄蜂");
        mAdapter.setData("鱼");
        mAdapter.setData("狮子");
        mAdapter.setData("猴子");
        mAdapter.setData("美洲豹");
        mAdapter.setData("苍蝇");
        mAdapter.setData("大象");
        mAdapter.setData("鲸鱼");
        mAdapter.setData("大黄蜂");
        mAdapter.setData("美洲豹");
        mAdapter.setData("苍蝇");
        mAdapter.setData("大象");
        mAdapter.setData("鲸鱼");
        mAdapter.setData("大黄蜂");
        mAdapter.setData("鱼");
        mAdapter.setData("狮子");
        mAdapter.setData("猴子");
        mAdapter.setData("美洲豹");
        mAdapter.setData("苍蝇");
        mAdapter.setData("大象");
        mAdapter.setData("鲸鱼");
        mAdapter.setData("大黄蜂");
        mAdapter.setData("鱼");
        mAdapter.setData("狮子");
        mAdapter.setData("犀牛");
        mAdapter.setData("乌鸦");
        mAdapter.setData("海鸥");
        mAdapter.setData("犀牛");
        mAdapter.setData("乌鸦");
        mAdapter.setData("海鸥");
        mAdapter.setData("犀牛");
        mAdapter.setData("乌鸦");
        mAdapter.setData("海鸥");



    }

}
