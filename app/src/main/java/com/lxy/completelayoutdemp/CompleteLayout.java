package com.lxy.completelayoutdemp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;



public class CompleteLayout extends ViewGroup {
    private static final String TAG = "CompleteLayout";



    private static int LINE_SPACING;//行間距
    private static int COLUMN_SPACING;//列間距

    private static int TEXT_SIZE;//字体大小
    private static int TEXT_COLOR;//字体颜色
    private static int TEXT_BACKGROUND;//文字背景
    private static int TEXT_PADDING;//文字padding的padding

    private CompleteLayoutAdapter mAdapter;


    public CompleteLayout(Context context) {
        super(context);

    }


    public CompleteLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //第二个参数就是我们在styles.xml文件中的<declare-styleable>标签
        //即属性集合的标签，在R文件中名称为R.styleable+name
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CompleteLayout);


        //第一个参数为属性集合里面的属性，R文件名称：R.styleable+属性集合名称+下划线+属性名称
        //第二个参数为，如果没有设置这个属性，则设置的默认的值

        //行列间距


        COLUMN_SPACING = (int) typedArray.getDimension(R.styleable.CompleteLayout_ColumnSpacing, 32);
        LINE_SPACING = (int) typedArray.getDimension(R.styleable.CompleteLayout_LineSpacing, 32);
        //textview的设置


        TEXT_COLOR = typedArray.getColor(R.styleable.CompleteLayout_TextColor, ContextCompat.getColor(getContext(), R.color.colorGray80));
        TEXT_SIZE = typedArray.getInt(R.styleable.CompleteLayout_TextSize, 18);
        TEXT_PADDING = typedArray.getInt(R.styleable.CompleteLayout_TextSize, 18);
        TEXT_BACKGROUND = typedArray.getInt(R.styleable.CompleteLayout_TextBackground, R.drawable.selector_item_btn);


        //最后记得将TypedArray对象回收
        typedArray.recycle();
    }

    public CompleteLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CompleteLayout);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec); //重新計算所有子view的寬高

        //寬度
        int measurWidthMod = MeasureSpec.getMode(widthMeasureSpec);
        int measurWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        //高度
        int measureHeightMod = MeasureSpec.getMode(heightMeasureSpec);
        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;

        int childCount = getChildCount();//子View數量

        if (childCount == 0) {//當前沒有子view
            width = 0;
        } else {
            width = getWidthSize(measurWidthMod, measurWidthSize);//根據寬度屬性去設置寬度
            height = getHeightSize(measureHeightMod, measureHeightSize);//根据高度属性设置高度
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int lineSizeIndex = LINE_SPACING;// 当前view添加行的下标位置
        int columnSizeIndex = COLUMN_SPACING;//列的下标
        int screenWidth = getScreenWidth();//屏幕高度
        int lineCount = 0;//当前行数

        for (int i = 0; i < childCount; i++) {
            View tempChildView = getChildAt(i);
            int childViewWidth = tempChildView.getMeasuredWidth();
            int childViewHeight = tempChildView.getMeasuredHeight();


            int addedColumnIndex = columnSizeIndex + childViewWidth + COLUMN_SPACING; //判断添加之后的下标未知
            if (addedColumnIndex > screenWidth) { //如果添加以后下标超过当前屏幕宽度 则换行 重置下标
                columnSizeIndex = COLUMN_SPACING;
                lineCount++;

            }
            lineSizeIndex = (lineCount * (childViewHeight + LINE_SPACING));


            int left = (columnSizeIndex == COLUMN_SPACING ? columnSizeIndex : columnSizeIndex + COLUMN_SPACING);//第一列不用增加间隔

            int top = (lineSizeIndex == LINE_SPACING ? lineSizeIndex : lineSizeIndex + LINE_SPACING); //第一行不用增加间隔
            int right = left + childViewWidth;
            int bottom = top + childViewHeight;

            tempChildView.layout(left, top, right, bottom); //添加view

            //设置下标
            columnSizeIndex = right;
        }
    }


    /**
     * 設置容器的寬度
     *
     * @param measurWidthMod  寬度模式 wrap_content match_parent
     * @param measurWidthSize 寬度測量數值
     */


    private int getWidthSize(int measurWidthMod, int measurWidthSize) {
        int width = 0;
        switch (measurWidthMod) {
            case MeasureSpec.AT_MOST://wrap_content
                int childCount = getChildCount();
                int screenSize = getScreenWidth(); //獲取屏幕寬度

                int totalChildWidth = 0;//所有子View的寬度
                int spacting = LINE_SPACING * (childCount + 1);//間距大小
                for (int i = 0; i < childCount; i++) {
                    totalChildWidth += getChildAt(i).getMeasuredWidth();
                }

                if (totalChildWidth + spacting > screenSize) { //所有子View的寬度和間距寬度 大於當前屏幕寬度
                    width = screenSize;
                } else {
                    width = totalChildWidth + spacting;
                }
                break;
            case MeasureSpec.EXACTLY://固定大小 或者 match_content
                width = measurWidthSize;
                break;
            default:
                width = measurWidthSize;
        }
        return width;
    }


    /**
     * 設置容器的高度
     *
     * @param measurHeightMod  高度模式 wrap_content match_parent
     * @param measurHeightSize 高度測量數值
     * @return
     */
    private int getHeightSize(int measurHeightMod, int measurHeightSize) {
        int height = 0;
        switch (measurHeightMod) {
            case MeasureSpec.AT_MOST://wrap_content
                int childCount = getChildCount();
                int totalChildHeight = 0;
                int spacting = COLUMN_SPACING * (childCount + 1);//列間距
                for (int i = 0; i < childCount; i++) {
                    totalChildHeight += getChildAt(i).getMeasuredHeight();
                }
                height = totalChildHeight + spacting;//高度直接設置就行了

                break;
            case MeasureSpec.EXACTLY://固定大小 或者 match_content
                height = measurHeightSize;
                break;
            default:
                height = measurHeightSize;
        }
        return height;
    }

    /**
     * 得到屏幕宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }


    /**
     * 移除所有子View
     */
    public void removeAllChildViews() {
        removeAllViews();
    }

    public void setAdapter(CompleteLayoutAdapter adapter) {
        mAdapter = adapter;
        mAdapter.bindCompleteLayout(this);
    }


    public void addTextView(String text, final int position) {
        final TextView tempTextView = new TextView(getContext());
        tempTextView.setTextSize(TEXT_SIZE);
        tempTextView.setTextColor(TEXT_COLOR);
        tempTextView.setBackgroundResource(TEXT_BACKGROUND);
        tempTextView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tempTextView.setPadding(TEXT_PADDING,TEXT_PADDING,TEXT_PADDING,TEXT_PADDING);

        tempTextView.setTag(position);

        if (mAdapter == null) {
            try {
                throw new Exception("Please set CompleteLayoutAdapter Frist！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tempTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter != null) {
                    mAdapter.getItemClickListener().onItemClick(mAdapter, (TextView) v, ((TextView) v).getText().toString(), (Integer) v.getTag());
                }
            }
        });

        tempTextView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mAdapter != null) {
                    mAdapter.getItemLongClickLisnter().onItemLongClick(mAdapter, (TextView) v, ((TextView) v).getText().toString(), (Integer) v.getTag());
                    return true;
                }
                return false;
            }
        });


        tempTextView.setText(text+position);

        addView(tempTextView);
    }


}
