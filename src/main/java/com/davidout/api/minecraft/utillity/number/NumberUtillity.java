package com.davidout.api.minecraft.utillity.number;

public class NumberUtillity {

    public static boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
