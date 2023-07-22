package com.davidout.api.packet.type.display.PacketPlayOutChat;

public enum ChatMessageType {

    CHAT("CHAT", (byte)0),
    SERVER("SYSTEM", (byte)1),
    ACTION_BAR("GAME_INFO", (byte)2);

    private final byte id;
    private final String minecraftEnum;

    private ChatMessageType(String minecraftEnum, byte id) {
        this.id = id;
        this.minecraftEnum = minecraftEnum;
    }

    public byte getMinecraftID() {
        return this.id;
    }


}
