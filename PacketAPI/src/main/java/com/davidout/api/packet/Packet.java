package com.davidout.api.packet;

import com.davidout.api.exception.PacketCreationException;
import com.davidout.api.nms.NMSHelper;
import com.davidout.api.nms.PacketHelper;
import com.davidout.api.nms.reflection.ReflectionHelper;
import org.bukkit.entity.Player;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Packet {

    public abstract String getMinecraftPacket();
    public abstract Object constructPacket() throws Exception;


    public void sendToPlayer(Player player) throws Exception {
        Object packet = constructPacket();
        if(packet == null) {
            throw new PacketCreationException(getMinecraftPacket());
        }

        PacketHelper.sendPacket(player, packet);
    }

    public void sendToPlayers(Player... players) throws Exception {
        for (Player player : players) {
            sendToPlayer(player);
        }
    }

    public void sendToPlayers(List<Player> playerList) throws Exception {
        for (Player player : playerList) {
            sendToPlayer(player);
        }
    }


    /**
     *
     *  packet methods
     *
     */

    protected Class<?> getPacketClass() {
        return NMSHelper.getMinecraftClass(getMinecraftPacket());
    }

    protected Constructor<?> getPacketConstructor(Class<?>... parameterClasses) {
        return ReflectionHelper.getConstructor(getPacketClass(), parameterClasses);
    }

    protected Method getPacketMethod(String method, Class<?>... parameterClasses) {
        return ReflectionHelper.getMethod(getPacketClass(), method, parameterClasses);
    }

    protected Field getPacketVariable(String variableName) {
        return ReflectionHelper.getField(getPacketClass(), variableName);
    }


    /**
     *
     *  Debugging
     *
     */

    protected String getFunctionString(String name, Parameter[] parameters) {
        StringBuilder printParameters = new StringBuilder(name + "(");
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            printParameters.append(parameter.getType().getSimpleName()).append(" ").append(parameter.getName());

            if (i < parameters.length - 1) {
                printParameters.append(", ");
            }
        }
        printParameters.append(")");

        return printParameters.toString();
    }

    protected void printConstructors() {
        Arrays.stream(getPacketClass().getConstructors()).collect(Collectors.toList()).forEach(constructor -> System.out.println(getFunctionString("Constructor: " + constructor.getName(), constructor.getParameters())));
    }

    protected void printMethods() {
        Arrays.stream(getPacketClass().getMethods()).collect(Collectors.toList()).forEach(method -> System.out.println(getFunctionString("Function: " + method.getName(), method.getParameters())));
    }

}
