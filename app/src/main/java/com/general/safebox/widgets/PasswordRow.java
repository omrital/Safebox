package com.general.safebox.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.general.safebox.R;
import com.general.safebox.model.PasswordInfo;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PasswordRow extends LinearLayout {

    @BindView(R.id.description) TextView descriptionTextView;
    @BindView(R.id.password) TextView passwordTextView;

    public PasswordRow(Context context) {
        super(context);
        init(context);
    }

    public PasswordRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PasswordRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public PasswordRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.password_row_content, this, true);
        ButterKnife.bind(this, v);
    }

    public void set(PasswordInfo passwordInfo) {
        descriptionTextView.setText(passwordInfo.getDescription());
        passwordTextView.setText(passwordInfo.getPassword());
    }
}
