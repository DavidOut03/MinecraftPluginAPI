package com.davidout.api.packet.parameter.factory;

import com.davidout.api.exception.PacketParameterCreationException;
import com.davidout.api.packet.parameter.chat.IChatBaseComponent;

public class ParameterFactory {


    public static Object createIChatBaseComponent(String text) {
        try {
            return new IChatBaseComponent(text).getCompletedIChatBaseComponent();
        } catch (Exception e) {
            return null;
        }
    }


}
