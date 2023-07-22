package com.davidout.api.packet.type.entity;

import com.davidout.api.nms.reflection.ReflectionHelper;
import com.davidout.api.packet.Packet;

import java.lang.reflect.InvocationTargetException;

public class PacketPlayOutEntityDestroy extends Packet {
    @Override
    public String getMinecraftPacket() {
        return "PacketPlayOutEntityDestroy";
    }

    @Override
    public Object constructPacket() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        return ReflectionHelper.getConstructor(getPacketClass(), int[].class).newInstance((Object) entitiesToDestroy);
    }

    private final int[] entitiesToDestroy;

    public PacketPlayOutEntityDestroy(int... entityIDS) {
        this.entitiesToDestroy = entityIDS;
    }

    public int[] getEntitiesToDestroy() {
        return entitiesToDestroy;
    }

}
