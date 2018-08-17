package com.general.safebox.widgets;

import android.animation.Animator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.general.safebox.R;
import com.general.safebox.model.PasswordInfo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePasswordRow extends LinearLayout {

    @BindView(R.id.description) EditText descriptionEditText;
    @BindView(R.id.password) EditText passwordEditText;

    private OnRowDeletedListener onRowDeletedListener;

    @OnClick(R.id.removeButton)
    public void onRemoveButtonClick() {
        animate().alpha(0.0f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) { }

            @Override
            public void onAnimationEnd(Animator animator) {
                ((ViewGroup) getParent()).removeView(UpdatePasswordRow.this);
                if(onRowDeletedListener != null) {
                    onRowDeletedListener.onRowDeleted();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) { }

            @Override
            public void onAnimationRepeat(Animator animator) { }
        });
    }

    public UpdatePasswordRow(Context context) {
        super(context);
        init(context);
    }

    public UpdatePasswordRow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UpdatePasswordRow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public UpdatePasswordRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.update_password_row_content, this, true);
        ButterKnife.bind(this, v);
    }

    public void set(PasswordInfo passwordInfo, OnRowDeletedListener listener) {
        descriptionEditText.setText(passwordInfo.getDescription());
        passwordEditText.setText(passwordInfo.getPassword());
        onRowDeletedListener = listener;
    }

    public PasswordInfo getPasswordInfo() {
        String description = descriptionEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(description.isEmpty() && password.isEmpty()) {
            return null;
        }
        return new PasswordInfo(description, password);
    }

    public interface OnRowDeletedListener {
        void onRowDeleted();
    }
}
