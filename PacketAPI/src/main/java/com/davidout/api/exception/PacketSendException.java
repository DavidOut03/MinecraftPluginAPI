package com.davidout.api.exception;

public class PacketSendException extends Exception {

    public PacketSendException() {
        super("Could not send packet because of an error.");
    }
}
