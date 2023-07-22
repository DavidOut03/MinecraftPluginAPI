package com.davidout.api.exception;

import com.davidout.api.packet.Packet;

public class PacketCreationException extends Exception {

    public PacketCreationException(String packetname) {
        super("Could not create the packet: " + packetname + " because of an error: ");
        this.printStackTrace();
    }

}
