package com.general.safebox.screens.update;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.general.safebox.R;
import com.general.safebox.Utils.Keyboard;
import com.general.safebox.core.BaseFragment;
import com.general.safebox.enums.ToolbarNavigationAction;
import com.general.safebox.model.PasswordInfo;
import com.general.safebox.widgets.SafeBoxToolbar;
import com.general.safebox.widgets.UpdatePasswordRow;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePasswordsFragment extends BaseFragment {

    @BindView(R.id.passwordsList) LinearLayout passwordsList;
    @BindView(R.id.emptyState) TextView emptyState;

    private ArrayList<UpdatePasswordRow> rows = new ArrayList<>();

    @OnClick(R.id.AddPasswordButton)
    public void onAddPasswordClick() {
        addRow(new PasswordInfo("", ""));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_update_passwords, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        ArrayList<PasswordInfo> passwords = preferences.getPasswords();
        if(passwords.isEmpty()) {
            emptyState.setVisibility(View.VISIBLE);
        } else {
            emptyState.setVisibility(View.GONE);
            buildPasswordsList(passwords);
        }
    }

    private void buildPasswordsList(ArrayList<PasswordInfo> passwords) {
        for(PasswordInfo info : passwords) {
            addRow(info);
        }
    }

    private void addRow(PasswordInfo info) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        UpdatePasswordRow row = (UpdatePasswordRow) inflater.inflate(R.layout.update_password_row, null);
        row.set(info);
        rows.add(row);
        passwordsList.addView(row);
    }

    private void savePasswords() {
        ArrayList<PasswordInfo> passwords = new ArrayList<>();

        for(UpdatePasswordRow row : rows) {
            PasswordInfo info = row.getPasswordInfo();
            if(info != null) {
                passwords.add(row.getPasswordInfo());
            }
        }
        if(!passwords.isEmpty()) {
            preferences.savePasswords(passwords);
        }
    }

    @Override
    protected int getMenuRes() {
        return R.menu.update_passwords_menu;
    }

    @Override
    protected int getToolbarTitleRes() {
        return R.string.update_passwords_toolbar;
    }

    @Override
    protected ToolbarNavigationAction getNavigationAction() {
        return ToolbarNavigationAction.BACK;
    }

    @Override
    protected void onToolbarActionClick(int actionId) {
        Keyboard.hide(getActivity(), getView());

        switch (actionId) {
            case R.id.save: {
                savePasswords();
                Toast.makeText(getActivity(), R.string.update_passwords_saved_toast, Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.settings: {
                navigator.settings();
                break;
            }
            case R.id.about: {
                navigator.about();
                break;
            }
            case SafeBoxToolbar.BACK_ACTION_ID: {
                getActivity().onBackPressed();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        savePasswords();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        savePasswords();
    }
}