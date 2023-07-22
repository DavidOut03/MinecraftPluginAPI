package com.davidout.api.exception;

import com.davidout.api.packet.Packet;

public class PacketNotCompatibleException extends Exception {

    public PacketNotCompatibleException(Packet packet, String version) {
        super("This packet: " + packet.getMinecraftPacket() + " is not compatible with the version: " + version + " of minecraft.");
    }
}
