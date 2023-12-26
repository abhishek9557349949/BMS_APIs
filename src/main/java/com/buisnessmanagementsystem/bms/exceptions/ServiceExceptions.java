package com.buisnessmanagementsystem.bms.exceptions;

import java.io.Serial;

public class ServiceExceptions extends Exception{
    @Serial
    private static final long serialVersionUID = -3372664571043193359L;

    public ServiceExceptions() {
        super();
    }

    public ServiceExceptions(String message) {
        super(message);
    }

    public ServiceExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceExceptions(Throwable cause) {
        super(cause);
    }
}
