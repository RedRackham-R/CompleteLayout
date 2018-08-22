package com.lxy.completelayoutdemp;

import android.animation.LayoutTransition;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CompleteLayout mCompleteLayout;
    private CompleteLayoutAdapter mAdapter;


    int addCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCompleteLayout = findViewById(R.id.completeLayout);
        mCompleteLayout.setAdapter(mAdapter = new CompleteLayoutAdapter(this));
        mCompleteLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccent));
        mAdapter.setOnItemClickListener(new CompleteLayoutAdapter.CompleteLayoutItemClickListener() {
            @Override
            public void onItemClick(CompleteLayoutAdapter adapter, TextView textView, String text, int position) {
                mAdapter.setData("添加" + addCount);
                addCount++;
            }
        });


        mAdapter.setOnItemLongClickListner(new CompleteLayoutAdapter.CompleteLayoutItemLongClickLisnter() {
            @Override
            public void onItemLongClick(CompleteLayoutAdapter adapter, TextView textView, String text, int position) {

                mAdapter.remove(position);
            }
        });

        findViewById(R.id.addOneBtn).setOnClickListener(this);
        findViewById(R.id.addMultipleBtn).setOnClickListener(this);
        findViewById(R.id.deleteOneBtn).setOnClickListener(this);
        findViewById(R.id.deleteAll).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addOneBtn://添加一个
                mAdapter.setData("添加单个" + addCount);
                addCount++;
                break;
            case R.id.addMultipleBtn://添加多个
                List data = new ArrayList<String>();
                for (int i = 0; i < 5; i++) {
                    data.add("添加多个" + addCount);
                    addCount++;
                }
                mAdapter.setData(data);
                break;
            case R.id.deleteOneBtn://删除一个
                if (mAdapter.getData().size() != 0)
                mAdapter.remove(mAdapter.getData().size() - 1);//删除最后一个

                break;
            case R.id.deleteAll://删除所有

                mAdapter.removeAll();
                break;
        }
    }
}
