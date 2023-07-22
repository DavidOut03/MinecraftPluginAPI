package com.davidout.api.packet.type.display;

import com.davidout.api.exception.PacketNotCompatibleException;
import com.davidout.api.nms.NMSHelper;
import com.davidout.api.nms.PacketHelper;
import com.davidout.api.nms.Version;
import com.davidout.api.nms.reflection.ReflectionHelper;
import com.davidout.api.packet.Packet;
import com.davidout.api.packet.parameter.factory.ParameterFactory;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class PacketPlayOutTitle extends Packet {
    @Override
    public String getMinecraftPacket() {
        return "PacketPlayOutTitle";
    }


    private String title;
    private String subTitle;
    private int fadeInTicks, displayTicks, fadeOutTicks;

    public PacketPlayOutTitle(String title) {
        this.setDefaultValues();
        this.title = title;
    }

    public PacketPlayOutTitle(String title, String subTitle) {
        this.setDefaultValues();
        this.title = title;
        this.subTitle = subTitle;
    }

    public PacketPlayOutTitle(String title, int displayTicks) {
        this.setDefaultValues();
        this.title = title;
        this.displayTicks = displayTicks;
    }

    public PacketPlayOutTitle(String title, String subTitle, int displayTicks) {
        this.setDefaultValues();
        this.title = title;
        this.subTitle = subTitle;
        this.displayTicks = displayTicks;
    }

    public PacketPlayOutTitle(String title, String subTitle, int fadeInTicks, int displayTicks, int fadeOutTicks) {
        this.setDefaultValues();
        this.title = title;
        this.subTitle = subTitle;
        this.fadeInTicks = fadeInTicks;
        this.fadeOutTicks = fadeOutTicks;
        this.displayTicks = displayTicks;
    }

    private void setDefaultValues() {
        this.title = null;
        this.subTitle = null;
        this.fadeInTicks = 20;
        this.displayTicks = 60;
        this.fadeOutTicks = 20;
    }


    @Override
    public Object constructPacket() throws Exception {
      return constructLocalPacket(this.title, "TITLE");
    }

    public Object constructLocalPacket(String text, String enumType) throws Exception {
        if(!(Version.serverVersionEqualsOrIsAbove("1.8"))) {
            throw new PacketNotCompatibleException(this, Version.getServerVersion());
        }

        Class<?> enumTitleClass = this.getPacketClass().getDeclaredClasses()[0];
        Object enumTitle = enumTitleClass.getField(enumType).get(null);
        Object iChatBaseComponent = ParameterFactory.createIChatBaseComponent(text);
        Constructor<?> packetConstructor = getPacketConstructor(enumTitleClass, NMSHelper.getMinecraftClass("IChatBaseComponent"), int.class, int.class, int.class);
        return packetConstructor.newInstance(enumTitle, iChatBaseComponent, fadeInTicks, displayTicks, fadeOutTicks);
    }

    public Object constructSubTitlePacket() throws Exception {
     return this.constructLocalPacket(this.subTitle, "SUBTITLE");
    }

    @Override
    public void sendToPlayer(Player player) throws Exception {
        if(this.title != null) PacketHelper.sendPacket(player, this.constructPacket());
        if(this.subTitle != null) PacketHelper.sendPacket(player, this.constructSubTitlePacket());
    }
}
