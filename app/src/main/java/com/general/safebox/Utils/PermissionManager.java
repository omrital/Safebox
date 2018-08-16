package com.general.safebox.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

public class PermissionManager {

    public static final String PERMISSION_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;

    public static boolean granted(Context context) {
        return ContextCompat.checkSelfPermission(context, PERMISSION_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public static void request(Fragment fragment, int requestCode) {
        fragment.requestPermissions(new String[] {PERMISSION_LOCATION}, requestCode);
    }
}
