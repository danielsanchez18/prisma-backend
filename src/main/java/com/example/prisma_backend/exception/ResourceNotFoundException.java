package com.example.prisma_backend.exception;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException() {
        super("ERROR: The resource does not exist. Try again");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
