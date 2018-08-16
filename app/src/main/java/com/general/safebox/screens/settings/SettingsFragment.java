package com.general.safebox.screens.settings;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;
import com.general.safebox.R;
import com.general.safebox.Utils.AlertPresenter;
import com.general.safebox.Utils.Keyboard;
import com.general.safebox.Utils.PermissionManager;
import com.general.safebox.core.BaseFragment;
import com.general.safebox.enums.ToolbarNavigationAction;
import com.general.safebox.widgets.SafeBoxToolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static android.content.Context.LOCATION_SERVICE;

public class SettingsFragment extends BaseFragment implements LocationListener {

    private String GOOGLE_MAPS_URL = "https://www.google.com/maps/place/";
    private final long FOUR_MINUTES = 240000;
    private final int PERMISSION_REQUEST_CODE = 0;
    private LocationManager locationManager;
    private boolean hasPermission;
    private double currentLatitude;
    private double currentLongitude;

    @BindView(R.id.nameEditText) EditText nameEditText;
    @BindView(R.id.lastNameEditText) EditText lastNameEditText;
    @BindView(R.id.webView) WebView webView;

    @OnClick(R.id.updateLocationButton)
    public void updateLocation() {
        if(hasPermission) {
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, this, null);
        } else {
            showPermissionAlert();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        checkForLocationPermission();
        return view;
    }

    private void initView() {
        nameEditText.setText(preferences.getUserName());
        lastNameEditText.setText(preferences.getUserLastName());
        initWebView();
    }

    private void initWebView() {
        webView.loadUrl(GOOGLE_MAPS_URL);

        webView.setOnTouchListener((View view, MotionEvent motionEvent) -> {
            Keyboard.hide(getActivity(), getView());
            return false;
        });
    }

    private void checkForLocationPermission() {
        if (PermissionManager.granted(getActivity())) {
            initLocationManager();
        } else {
            PermissionManager.request(this, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initLocationManager();
            } else {
                showPermissionAlert();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showPermissionAlert() {
        AlertPresenter.showAlert(getActivity(), R.string.settings_alert_no_permission_title, R.string.settings_alert_no_permission_message, R.string.settings_alert_no_permission_button);
    }

    private void initLocationManager() {
        hasPermission = true;
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastKnownLocation != null) {
            showLocation(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, FOUR_MINUTES, 0, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.currentLatitude = location.getLatitude();
        this.currentLongitude = location.getLongitude();

        showLocation(currentLatitude, currentLongitude);
        saveLocation();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) { }

    @Override
    public void onProviderEnabled(String s) { }

    @Override
    public void onProviderDisabled(String s) { }

    private void showLocation(double latitude, double longitide) {
        String URL =  String.format("%s%d%d", GOOGLE_MAPS_URL, latitude, longitide);
        webView.loadUrl(URL);
    }

    private void saveSettings() {
        preferences.saveUserName(nameEditText.getText().toString());
        preferences.saveUserLastName(lastNameEditText.getText().toString());
        saveLocation();
    }

    private void saveLocation() {
        preferences.saveUserLatitude(String.valueOf(currentLatitude));
        preferences.saveUserLongitude(String.valueOf(currentLongitude));
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
        Keyboard.hide(getActivity(), getView());

        switch (actionId) {
            case R.id.save: {
                saveSettings();
                Toast.makeText(getActivity(), R.string.settings_saved_toast, Toast.LENGTH_LONG).show();
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
        saveSettings();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveSettings();
    }
}
