package com.general.safebox.screens.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.general.safebox.R;
import com.general.safebox.Utils.AlertPresenter;
import com.general.safebox.Utils.Keyboard;
import com.general.safebox.core.BaseFragment;
import com.general.safebox.enums.ToolbarNavigationAction;
import com.general.safebox.widgets.AlphaFrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends BaseFragment {

    private boolean isFirstTime;
    private String savedPassword;

    @BindView(R.id.passwordInfoText) TextView passwordInfoText;
    @BindView(R.id.passwordInput) EditText passwordInput;
    @BindView(R.id.enterButton) AlphaFrameLayout enterButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        savedPassword = preferences.getUserPassword();
        if(savedPassword.isEmpty()) {
            isFirstTime = true;
        }
        initView();
        return view;
    }

    private void initView() {
        initInfoText();
        initPassword();
        initEnterButton();
    }

    private void initInfoText() {
        if(isFirstTime) {
            passwordInfoText.setText(R.string.login_password_info_first_time);
        } else {
            passwordInfoText.setText(R.string.login_password_info);
        }
    }

    private void initPassword() {
        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                passwordInput.setTextColor(getResources().getColor(R.color.black));
                String text = editable.toString();
                enterButton.setVisibility(text.isEmpty()? View.GONE : View.VISIBLE);
            }
        });
    }

    private void initEnterButton() {
        enterButton.setOnClickListener(view -> {
            String password = passwordInput.getText().toString();

            if(isFirstTime) {
                preferences.saveUserPassword(password);
                navigator.passwordsList();
            } else {
                if(password.equals(savedPassword)) {
                    Keyboard.hide(getActivity(), getView());
                    navigator.passwordsList();
                } else {
                    AlertPresenter.showAlert(getActivity(), R.string.login_password_alert_title, R.string.login_password_alert_message, R.string.login_password_alert_ok);
                    passwordInput.setTextColor(getResources().getColor(R.color.red));
                }
            }
        });
    }

    @Override
    protected int getMenuRes() {
        return R.menu.login_menu;
    }

    @Override
    protected int getToolbarTitleRes() {
        return R.string.login_toolbar;
    }

    @Override
    protected ToolbarNavigationAction getNavigationAction() {
        return ToolbarNavigationAction.NONE;
    }

    @Override
    protected void onToolbarActionClick(int actionId) { }

    @Override
    protected boolean isAddToStack() {
        return false;
    }

    @Override
    protected boolean isAnimated() {
        return false;
    }
}
