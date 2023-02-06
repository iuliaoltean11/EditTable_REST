package com.example.proiect_frontend;

public class ClientErrorException extends Exception {
    public ClientErrorException() {
    }
    public ClientErrorException(String message) {
        super(message);
    }
}
