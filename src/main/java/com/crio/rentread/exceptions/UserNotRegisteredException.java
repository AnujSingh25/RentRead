package com.crio.rentread.exceptions;

import java.io.IOException;

public class UserNotRegisteredException extends IOException {

    public UserNotRegisteredException(String message) {
        super(message);
    }
    
}
