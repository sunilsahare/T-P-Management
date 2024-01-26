package com.tnpserver.constants;

import java.util.Arrays;

public enum ErrorCode {
    USER_NOT_FOUND("invalid UserId!!! Please provide valid userId."),
    USERNAME_NOT_FOUND("Username not found."),
    USER_EXISTS("User already exists."),
    USERNAME_EXISTS("Please use another username."),
    USER_DEACTIVATE("Please Activate the Account by Admin and then try to Logged in."),
    EMAIL_EXISTS("Email already exists."),
    EMAIL_NOT_FOUND("Email not found."),
    USER_DEACTIVATED("User is Deactivated"),
    ACADEMIC_INFO_UPDATE_NOT_ALLOWED("Academic Info update is allowed only by Logged in User"),
    JOB_DETAILS_NOT_ALLOWED_TO_ADD("Job details can be added/updated only by the Employer and the Admin Role"),
    ACADEMIC_INFO_NOT_FOUND("Invalid Id. Academic Info not found!!!");

    private final String error;

    ErrorCode(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
