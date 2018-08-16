package com.general.safebox.screens.passwords;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.general.safebox.R;
import com.general.safebox.core.BaseFragment;
import com.general.safebox.enums.ToolbarNavigationAction;
import com.general.safebox.model.PasswordInfo;
import com.general.safebox.widgets.PasswordRow;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PasswordsListFragment extends BaseFragment {

    @BindView(R.id.passwordsList) LinearLayout passwordsList;
    @BindView(R.id.emptyState) TextView emptyState;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_passwords_list, container, false);
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
        PasswordRow row = (PasswordRow) inflater.inflate(R.layout.password_row, null);
        row.set(info);
        passwordsList.addView(row);
    }

    @Override
    protected int getMenuRes() {
        return R.menu.passwords_menu;
    }

    @Override
    protected int getToolbarTitleRes() {
        return R.string.passwords_toolbar;
    }

    @Override
    protected ToolbarNavigationAction getNavigationAction() {
        return ToolbarNavigationAction.NONE;
    }

    @Override
    protected void onToolbarActionClick(int actionId) {
        switch (actionId) {
            case R.id.edit: {
                navigator.updatePasswords();
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
        }
    }
}
