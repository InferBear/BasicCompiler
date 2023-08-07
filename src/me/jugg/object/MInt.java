package me.jugg.object;

import java.math.BigInteger;

public class MInt extends MObj {

    public BigInteger value;

    public MInt(String value) {
        this.value = new BigInteger(value);
    }

    public MInt(BigInteger value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MInt(" + value + ")";
    }
}