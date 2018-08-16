package com.general.safebox.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.general.safebox.enums.ToolbarNavigationAction;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected Unbinder unbinder;
    protected Navigator navigator;
    protected Preferences preferences;

    protected abstract int getMenuRes();
    protected abstract int getToolbarTitleRes();
    protected abstract ToolbarNavigationAction getNavigationAction();
    protected abstract void onToolbarActionClick(int actionId);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        navigator = Navigator.getInstance();
        preferences = Preferences.getInstance(getActivity());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();

        if(unbinder != null) {
            unbinder.unbind();
        }
    }
}
