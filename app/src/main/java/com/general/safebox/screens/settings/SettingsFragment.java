package com.general.safebox.screens.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.general.safebox.R;
import com.general.safebox.core.BaseFragment;
import com.general.safebox.enums.ToolbarNavigationAction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsFragment extends BaseFragment {

    @BindView(R.id.passwordInfoText)
    TextView passwordInfoText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);





        initView();
        return view;
    }

    private void initView() {




    }




    @Override
    protected int getMenuRes() {
        return R.menu.settings_menu;
    }

    @Override
    protected int getToolbarTitleRes() {
        return R.string.settings_toolbar;
    }

    @Override
    protected ToolbarNavigationAction getNavigationAction() {
        return ToolbarNavigationAction.BACK;
    }

    @Override
    protected void onToolbarActionClick(int actionId) {




    }
}
