package com.general.safebox.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.general.safebox.R;
import com.general.safebox.model.ProfileRowInfo;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileRow extends LinearLayout {

    public static final int NO_IMAGE = 0;

    @BindView(R.id.profile_image) ImageView image;
    @BindView(R.id.profile_title) TextView titleTextView;
    @BindView(R.id.profile_subtitle) TextView subtitleTextView;
    @BindView(R.id.profile_extra) TextView extraTextView;

    public ProfileRow(Context context) {
        super(context);
        init(context);
    }

    public ProfileRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProfileRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ProfileRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.profile_row, this, true);
        ButterKnife.bind(this, v);
    }

    public void set(ProfileRowInfo profileRowInfo) {
        initTextView(titleTextView, profileRowInfo.getTitleRes());
        initTextView(subtitleTextView, profileRowInfo.getSubtitleRes());
        initTextView(extraTextView, profileRowInfo.getExtraRes());

        int imageRes = profileRowInfo.getImageRes();
        if(imageRes == NO_IMAGE) {
            image.setVisibility(View.GONE);
        } else {
            image.setImageResource(imageRes);
        }
    }

    private void initTextView(TextView textView, int stringRes) {
        String text = getContext().getResources().getString(stringRes);
        if(text.isEmpty()) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
        }
    }
}

