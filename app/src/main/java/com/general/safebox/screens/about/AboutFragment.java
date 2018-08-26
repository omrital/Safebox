package com.general.safebox.screens.about;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import com.general.safebox.R;
import com.general.safebox.core.BaseFragment;
import com.general.safebox.enums.ToolbarNavigationAction;
import com.general.safebox.model.ProfileRowInfo;
import com.general.safebox.widgets.ProfileRow;
import com.general.safebox.widgets.SafeBoxToolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutFragment  extends BaseFragment {

    private static final String VIDEO_PROFILE = "password_video";

    @BindView(R.id.about_my_info_row) ProfileRow myInfoRow;
    @BindView(R.id.about_job_row1) ProfileRow job1Row;
    @BindView(R.id.about_job_row2) ProfileRow job2Row;
    @BindView(R.id.about_job_row3) ProfileRow job3Row;
    @BindView(R.id.about_education_row) ProfileRow educationRow;
    @BindView(R.id.about_video) VideoView videoView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        initializePlayer();
        myInfoRow.set(new ProfileRowInfo(ProfileRow.NO_IMAGE, R.string.about_my_name, R.string.about_my_job, R.string.about_my_location));
        job1Row.set(new ProfileRowInfo(R.drawable.job1_image, R.string.about_job1_name, R.string.about_job1_company, R.string.about_job1_extra));
        job2Row.set(new ProfileRowInfo(R.drawable.job2_image, R.string.about_job2_name, R.string.about_job2_company, R.string.about_job2_extra));
        job3Row.set(new ProfileRowInfo(R.drawable.job3_image, R.string.about_job3_name, R.string.about_job3_company, R.string.about_job3_extra));
        educationRow.set(new ProfileRowInfo(R.drawable.education_image, R.string.about_education1, R.string.about_education2, R.string.about_education3));
    }

    private void initializePlayer() {
        Uri videoUri = getMedia(VIDEO_PROFILE);
        videoView.setVideoURI(videoUri);
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
    }

    private Uri getMedia(String mediaName) {
        return Uri.parse("android.resource://" + getActivity().getPackageName() + "/raw/" + mediaName);
    }

    private void releasePlayer() {
        videoView.stopPlayback();
    }

    @Override
    public void onStart() {
        super.onStart();
        videoView.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    protected int getMenuRes() {
        return R.menu.about_menu;
    }

    @Override
    protected int getToolbarTitleRes() {
        return R.string.about_toolbar;
    }

    @Override
    protected ToolbarNavigationAction getNavigationAction() {
        return ToolbarNavigationAction.BACK;
    }

    @Override
    protected void onToolbarActionClick(int actionId) {
        switch (actionId) {
            case SafeBoxToolbar.BACK_ACTION_ID: {
                getActivity().onBackPressed();
            }
        }
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

