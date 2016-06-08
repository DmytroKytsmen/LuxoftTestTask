package com.kytsmen.dao;

public enum ServiceErrorCode {
    GENERAL_ERROR("generalError", true),
    INVALID_ARGUMENTS("invalidArguments", false),
    BAD_REQUEST_PARAMS("badRequestParams", true),
    ITEM_NOT_FOUND("itemNotFound", false);

    String resKey;
    boolean showErrorMessage;

    ServiceErrorCode(String _resKey, boolean _showErrorMessage) {
        resKey = _resKey;
        showErrorMessage = _showErrorMessage;
    }

    public String getResKey() {
        return resKey;
    }

    public boolean showErrorMessage() {
        return showErrorMessage;
    }
}

