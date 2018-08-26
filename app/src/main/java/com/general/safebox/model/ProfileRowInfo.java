package com.general.safebox.model;

public class ProfileRowInfo {
    private int imageRes;
    private int titleRes;
    private int subtitleRes;
    private int extraRes;

    public ProfileRowInfo(int imageRes, int titleRes, int subtitleRes, int extraRes) {
        this.imageRes = imageRes;
        this.titleRes = titleRes;
        this.subtitleRes = subtitleRes;
        this.extraRes = extraRes;
    }

    public int getImageRes() {
        return imageRes;
    }

    public int getTitleRes() {
        return titleRes;
    }

    public int getSubtitleRes() {
        return subtitleRes;
    }

    public int getExtraRes() {
        return extraRes;
    }
}
