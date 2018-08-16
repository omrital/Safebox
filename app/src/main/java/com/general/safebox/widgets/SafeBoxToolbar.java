package com.general.safebox.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import com.general.safebox.R;
import com.general.safebox.enums.ToolbarNavigationAction;

public class SafeBoxToolbar extends Toolbar {

    public static int NAV_ACTION_ID = 150;

    public SafeBoxToolbar(Context context) {
        super(context);
        init();
    }

    public SafeBoxToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SafeBoxToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setTitleTextColor(getContext().getResources().getColor(R.color.white));
    }

    public void setNavigationAction(ToolbarNavigationAction action) {
        switch (action) {
            case NONE: {
                setNavigationIcon(R.drawable.ic_pass);
                setNavigationContentDescription(null);
                break;
            }
            case BACK: {
                setNavigationIcon(R.drawable.ic_back);
                setNavigationContentDescription(R.string.action_back);
                break;
            }
        }
    }
}
