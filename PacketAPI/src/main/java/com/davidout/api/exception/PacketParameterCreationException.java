package com.davidout.api.exception;

import com.davidout.api.packet.Packet;

public class PacketParameterCreationException extends Exception {

    public PacketParameterCreationException(String parameter) {
        super("Could not create the parameter: " + parameter + " because of an error: ");
        this.printStackTrace();
    }
}
