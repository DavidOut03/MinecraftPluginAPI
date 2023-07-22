package com.davidout.api.packet.parameter.chat;

import com.davidout.api.exception.PacketCreationException;
import com.davidout.api.exception.PacketParameterCreationException;
import com.davidout.api.nms.NMSHelper;
import org.bukkit.ChatColor;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class IChatBaseComponent {
    private final String message;
    public IChatBaseComponent(String message) {
        this.message = message;
    }

    public Object getCompletedIChatBaseComponent() throws PacketParameterCreationException {
       try {
           return Objects.requireNonNull(NMSHelper.getMinecraftClass("IChatBaseComponent")).getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', getMessage()) + "\"}");
       } catch (Exception ex) {
           throw new PacketParameterCreationException("IChatBaseComponent");
       }
    }

    private String getMessage() {
        return this.message;
    }

    public String getText() {
        return this.message;
    }



    public static Class<?> getMinecraftClass() {
        return NMSHelper.getMinecraftClass("IChatBaseComponent");
    }
}
