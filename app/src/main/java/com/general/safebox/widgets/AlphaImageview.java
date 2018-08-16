package com.general.safebox.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class AlphaImageview extends ImageView {

    public AlphaImageview(Context context) {
        super(context);
    }

    public AlphaImageview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlphaImageview(Context context, AttributeSet attrs, int defStyleAttr) {
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
