package com.davidout.api.packet.type.display.PacketPlayOutChat;

import com.davidout.api.nms.NMSHelper;
import com.davidout.api.nms.Version;
import com.davidout.api.packet.Packet;
import com.davidout.api.packet.parameter.chat.IChatBaseComponent;
import org.bukkit.ChatColor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class PacketPlayOutChat extends Packet {
    @Override
    public String getMinecraftPacket() {
        return "PacketPlayOutChat";
    }

    private final ChatMessageType type;
    private final String message;

    public PacketPlayOutChat(String message) {
        this.message = message;
        this.type = ChatMessageType.CHAT;
    }

    public PacketPlayOutChat(String message, ChatMessageType type) {
        this.message = message;
        this.type = type;
    }

    @Override
    public Object constructPacket() throws Exception {
        IChatBaseComponent baseComponent = new IChatBaseComponent(ChatColor.translateAlternateColorCodes('&', this.message));
        if(Version.serverVersionEqualsOrIsAbove("1.16")) {
            Constructor<?> packetConstructor = this.getPacketConstructor(IChatBaseComponent.getMinecraftClass(), NMSHelper.getMinecraftClass("ChatMessageType"), UUID.class);
            Object chatType = NMSHelper.getMinecraftClass("ChatMessageType").getDeclaredMethod("a", byte.class).invoke(null, (byte) this.type.getMinecraftID());
            return packetConstructor.newInstance(baseComponent.getCompletedIChatBaseComponent(), chatType, UUID.randomUUID());
        }

        Constructor<?> packetConstructor = getPacketConstructor(IChatBaseComponent.getMinecraftClass(), byte.class);
        return packetConstructor.newInstance(baseComponent.getCompletedIChatBaseComponent(), this.type.getMinecraftID());
    }
}
