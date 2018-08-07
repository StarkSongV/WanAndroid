package com.sjc.wanandroid.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import com.sjc.wanandroid.R;

/**
 * Created by sjc on 2018/5/31 14:33
 */

public class ClearEditText extends AppCompatEditText implements TextWatcher, View.OnFocusChangeListener {
    private Drawable clearDraw;
    private boolean hasFocus;//是否是有焦点
    private boolean isShow;//清除按钮是否显示
    public ClearEditText(Context context) {
        super(context);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initClearImages();
        addTextChangedListener(this);
        setOnFocusChangeListener(this);
//        //默认设置隐藏图标
//        setClearDrawVisibility(getText().toString().length()>0);
//        setSelection(getText().toString().length());//将光标移至文字末尾
    }

    private void initClearImages() {
        clearDraw = getCompoundDrawables()[2];
        if (clearDraw == null) {
            clearDraw = getResources().getDrawable(R.mipmap.delete_icon);
        }
        clearDraw.setBounds(0, 0, clearDraw.getIntrinsicWidth(), clearDraw.getIntrinsicHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isShow&&event.getAction() == MotionEvent.ACTION_UP) {
            boolean b = event.getX() > (getWidth() - getTotalPaddingRight())
                    && getX() < (getWidth() - getPaddingRight());
            if (b) {
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (hasFocus) {
            setClearDrawVisibility(s.length() > 0);
        }else{
            setClearDrawVisibility(false);
        }
    }


    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            setSelection(getText().toString().length());//将光标移至文字末尾
            setClearDrawVisibility(getText().toString().length()>0);
        }else{
            setClearDrawVisibility(false);
        }
    }

    private void setClearDrawVisibility(boolean isShow) {
        this.isShow = isShow;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], isShow ? clearDraw : null
                , getCompoundDrawables()[3]);
    }

    /**
     * 开始晃动动画
     */
    public void startShakeAnimation(){
        if(getAnimation() == null){
            this.setAnimation(shakeAnimation(4));
        }
        this.startAnimation(getAnimation());
    }

    /**
     * 晃动动画
     * @param counts 0.5秒钟晃动多少下
     * @return
     */
    private Animation shakeAnimation(int counts){
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(500);
        return translateAnimation;
    }
}
