package com.sjc.wanandroid.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sjc.wanandroid.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *关键字控件
 *@author :Created by sjc on 2018/6/25 13:28
 */
public class FlowGroupView extends ViewGroup {
    /**
     * 储存所有的view 按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<>();
    /**
     * 记录每一行的高度
     */
    private List<Integer> mLineHeight = new ArrayList<>();
    /**
     * 包含的关键字是否填充满一行
     */
    private boolean isFill = false;
    /**
     * 关键字字体大小
     */
    private int textSize;
    private OnItemClickListener listener;

    public FlowGroupView(Context context, AttributeSet attrs,
                          int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FlowGroupView,
                defStyleAttr, 0);
        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.FlowGroupView_isFill:
                    isFill = ta.getBoolean(attr, false); // 默认不填充
                    break;
                case R.styleable.FlowGroupView_textSize:
                    textSize = ta.getDimensionPixelSize(attr, (int) dip2px(20)); // 默认字体大小为20
                    break;
                default:
                    break;
            }
        }
        ta.recycle();
    }

    public FlowGroupView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public FlowGroupView(Context context) {
        this(context,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 置空 view 容器 和 lineHeight 容器  重新赋值
        //因为OnMeasure方法会走两次，第一次是实例化这个对象的时候高度和宽度都是0
        //之后走了OnSizeChange()方法后 又走了一次OnMeasure，所以要把第一次加进去的数据清空。
        mAllViews.clear();
        mLineHeight.clear();
        //得到上级容器为其推荐的宽高和计算模式
        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int specHeighMode = MeasureSpec.getMode(heightMeasureSpec);
        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int specHeighSize = MeasureSpec.getSize(heightMeasureSpec);
        // 计算出所有的 child 的 宽和高
//      measureChildren(specWidthSize, specHeighSize);
        // 记录如果是 warp_content 是设置的宽和高
        int width = 0;
        int height = 0;
        // 得到子view的个数
        int cCount = getChildCount();

        /**
         * 记录每一行的宽度，width不断取最大宽度
         */
        int lineWidth = 0;
        /**
         * 每一行的高度，累加至height
         */
        int lineHeight = 0;

        // 存储每一行所有的childView
        List<View> lineViews = new ArrayList<View>();

        for (int i = 0; i < cCount; i++) {
            // 得到每个子View
            View child = getChildAt(i);
            // 测量每个子View的宽高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 当前子view的lp
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            // 子view的宽和高
            int cWidth = 0;
            int cheight = 0;
            // 当前子 view 实际占的宽
            cWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            cheight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            // 需要换行
            if (lineWidth + cWidth > specWidthSize && i != 0) { //第一个不需要换行
                width = Math.max(lineWidth, width);// 和上一行的宽度相比,取最大值
                height += lineHeight;//换行的时候把上一行的高度累加起来

                //换行的时候才把上一个view的list和高度加进去
                mAllViews.add(lineViews);
                mLineHeight.add(lineHeight);

                if (isFill) {

                    reMeasureChild(specWidthSize, lineWidth, lineViews);
                }

                lineWidth = cWidth; // 开启新行的时候重新累加width
                lineHeight = cheight;

                lineViews = new ArrayList<>();
                // 换行的时候把该 view 放进 集合里
                lineViews.add(child);// 这个  view(child) 是下一行的第一个view

            } else {
                // 不需要换行
                lineWidth += cWidth;//
                // 不需要换行时 把子View add 进集合
                lineViews.add(child);
                lineHeight = lineHeight < cheight ? cheight : lineHeight; //其实不用比较.因为同一行的view高度必然是相同的
            }
            if (i == cCount - 1) {
                // 如果是最后一个view
                width = Math.max(width, lineWidth);// 每次换行的时候比较的都是上一行的宽度,在最后一个view的时候对最后一行的宽度和前几行最大宽度相比较,取出最大值
                height += cheight; //每次换行的时候加的是上一行的高度,在最后一个view的时候加上这个view的高度其实就是加的最后一行的高度
            }
        }
        if (isFill&&cCount!=0) {
            reMeasureChild(specWidthSize, lineWidth, lineViews);
        }
        // 循环结束后 把最后一行内容add进集合中
        mLineHeight.add(lineHeight); // 记录最后一行
        mAllViews.add(lineViews);


        // MeasureSpec.EXACTLY 表示设置了精确的值
        // 如果 mode 是 MeasureSpec.EXACTLY 时候，则不是 warp_content 用计算来的值，否则则用上级布局分给它的值
        setMeasuredDimension(
                specWidthMode == MeasureSpec.EXACTLY ? specWidthSize : width,
                specHeighMode == MeasureSpec.EXACTLY ? specHeighSize : height
        );
    }

    /**
     * 重新测量子控件的宽高
     *
     * @param specWidthSize
     * @param lineWidth
     * @param lineViews
     */
    private void reMeasureChild(int specWidthSize, int lineWidth, List<View> lineViews) {
        if (lineViews.size()==0) {
            return;
        }
        int i2 = (specWidthSize - lineWidth) / lineViews.size();
        for (int i1 = 0; i1 < lineViews.size(); i1++) {
            View view = lineViews.get(i1);
            LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = i2 > 0 ? view.getMeasuredWidth() + i2 : view.getMeasuredWidth();
        }
    }

    /**
     * 所有childView的位置的布局
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 当前行的最大高度
        int lineHeight = 0;
        // 存储每一行所有的childView
        List<View> lineViews;
        int left = 0;
        int top = 0;
        // 得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++) {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            // 当前行的最大高度
            lineHeight = mLineHeight.get(i);

            // 遍历当前行所有的View
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                //计算childView的left,top,right,bottom
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
            }
            left = 0;
            top += lineHeight;
        }
    }

    /**
     * 这个一定要设置，否则会包强转错误
     * 设置它支持 marginLayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public void addList(List<String> stringList) {
        mAllViews.clear();
        mLineHeight.clear();
        if (stringList != null) {
            for (int i = 0; i < stringList.size(); i++) {
                final String s = stringList.get(i);
                TextView textView = new TextView(getContext());
                ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams
                        (ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
                params.setMargins(13, 13, 13, 13);
                textView.setLayoutParams(params);
                textView.setBackgroundResource(R.drawable.flowgroupview_bg);
                textView.setGravity(Gravity.CENTER);
                textView.setText(s);
                textView.getPaint().setTextSize(textSize);
                final int finalI = i;
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener!=null) {
                            listener.onItemClick(finalI,s);
                        }
                    }
                });
                setRandomColor(textView);
                addView(textView);
            }
        }
    }

    /**
     * 设置随机色
     *
     * @param textView
     */
    private void setRandomColor(TextView textView) {
        Random random = new Random();
        int red, green, blue;
        red = random.nextInt(255);
        green = random.nextInt(255);
        blue = random.nextInt(255);
        textView.setTextColor(Color.rgb(red, green, blue));

    }

    private float dip2px(float dipValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position,String content);
    }
}