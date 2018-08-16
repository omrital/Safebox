package com.general.safebox.Utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public class AlertPresenter {
    public static void showAlert(Context context, int titleRes, int messageRes, int positiveButtonRes) {
        new AlertDialog.Builder(context)
                .setTitle(titleRes)
                .setMessage(messageRes)
                .setPositiveButton(positiveButtonRes, null)
                .show();
    }
}
