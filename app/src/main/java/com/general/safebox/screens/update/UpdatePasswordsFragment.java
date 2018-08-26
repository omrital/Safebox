package com.general.safebox.screens.update;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.general.safebox.R;
import com.general.safebox.Utils.Keyboard;
import com.general.safebox.Utils.SoundPlayer;
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
    @BindView(R.id.ScrollView) ScrollView scrollView;
    @BindView(R.id.emptyState) TextView emptyState;

    private ArrayList<UpdatePasswordRow> rows;

    @OnClick(R.id.AddPasswordButton)
    public void onAddPasswordClick() {
        SoundPlayer.playSound(getActivity(), R.raw.add_password_sound);
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
        rows = new ArrayList<>();

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
        row.set(info, () -> onRowRemoved(row));
        rows.add(row);
        passwordsList.addView(row);
        row.setAlpha(0.0f);
        row.animate().alpha(1.0f);
        scrollDown();
        updateEmptyState();
    }

    private void onRowRemoved(UpdatePasswordRow row) {
        SoundPlayer.playSound(getActivity(), R.raw.remove_password_sound);
        rows.remove(row);
        updateEmptyState();
    }

    private void scrollDown() {
        new Handler().postDelayed(() -> {
            scrollView.fullScroll(View.FOCUS_DOWN);
        }, 100);
    }

    private void updateEmptyState() {
        emptyState.setVisibility(rows.isEmpty()? View.VISIBLE : View.GONE);
    }

    private void savePasswords() {
        ArrayList<PasswordInfo> passwords = new ArrayList<>();

        for(UpdatePasswordRow row : rows) {
            PasswordInfo info = row.getPasswordInfo();
            if(info != null) {
                passwords.add(row.getPasswordInfo());
            }
        }
        preferences.savePasswords(passwords);
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
    public void onStop() {
        super.onStop();
        savePasswords();
    }

    @Override
    protected boolean isAddToStack() {
        return true;
    }

    @Override
    protected boolean isAnimated() {
        return true;
    }
}