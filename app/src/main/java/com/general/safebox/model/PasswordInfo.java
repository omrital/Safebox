package com.general.safebox.model;

public class PasswordInfo {
    private String description;
    private String password;

    public PasswordInfo(String description, String password) {
        this.description = description;
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
