package com.general.safebox.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class AlphaFrameLayout extends FrameLayout {

    public AlphaFrameLayout(Context context) {
        super(context);
    }

    public AlphaFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlphaFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            setAlpha(0.5f);
            return true;
        } else if(event.getAction() == MotionEvent.ACTION_UP){
            setAlpha(1.0f);
            callOnClick();
            return true;
        } else{
            setAlpha(1.0f);
            return true;
        }
    }
}
