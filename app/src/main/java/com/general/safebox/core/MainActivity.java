package com.general.safebox.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import com.general.safebox.R;
import com.general.safebox.widgets.SafeBoxToolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Navigator navigator;
    private Unbinder unbinder;
    private BaseFragment currentFragment;

    @BindView(R.id.toolbar)
    SafeBoxToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initNavigator();
        goToLoginScreen();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onOptionsItemSelected(null));
    }

    private void initNavigator() {
        navigator = Navigator.getInstance();
        navigator.setActivity(this);
    }

    private void goToLoginScreen() {
        navigator.loginScreen();
    }

    public void setCurrentFragment(BaseFragment fragment) {
        this.currentFragment = fragment;
        invalidateOptionsMenu();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(currentFragment == null) {
            return false;
        }
        toolbar.setTitle(currentFragment.getToolbarTitleRes());
        toolbar.setNavigationAction(currentFragment.getNavigationAction());
        getMenuInflater().inflate(currentFragment.getMenuRes(), menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item == null) {
            currentFragment.onToolbarActionClick(SafeBoxToolbar.BACK_ACTION_ID);
            return true;
        }
        currentFragment.onToolbarActionClick(item.getItemId());
        return true;
    }
}