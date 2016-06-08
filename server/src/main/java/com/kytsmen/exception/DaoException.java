package com.kytsmen.exception;

import com.kytsmen.dao.ServiceErrorCode;

public class DaoException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private ServiceErrorCode errorCode;

    public DaoException() {
        super();
    }

    public DaoException(ServiceErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(ServiceErrorCode errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public ServiceErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ServiceErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
