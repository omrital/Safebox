package com.general.safebox.core;

import android.support.v4.app.FragmentTransaction;
import com.general.safebox.R;
import com.general.safebox.screens.login.LoginFragment;

public class Navigator {

    private static Navigator instance;
    private MainActivity activity;

    private Navigator(){}

    public static Navigator getInstance() {
        if(instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public void loginScreen() {
        navigateToFragment(new LoginFragment());
    }

    public void passwordsList() {
        navigateToFragment(new LoginFragment());
    }

    public void updatePasswords() {
        navigateToFragment(new LoginFragment());
    }

    public void settings() {
        navigateToFragment(new LoginFragment());
    }

    public void about() {
        navigateToFragment(new LoginFragment());
    }

    private void navigateToFragment(BaseFragment fragment) {
        FragmentTransaction transaction = this.activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_left_from_right_of_screen, R.anim.slide_left_from_screen, R.anim.slide_right_from_left_of_screen, R.anim.slide_right_from_screen);
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        activity.setCurrentFragment(fragment);
    }
}
