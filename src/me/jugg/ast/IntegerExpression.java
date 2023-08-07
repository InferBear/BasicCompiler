package me.jugg.ast;

import java.math.BigInteger;
import java.sql.SQLIntegrityConstraintViolationException;

public class IntegerExpression extends Expression {

    private BigInteger value;

    public IntegerExpression(String value) {
        this.value = new BigInteger(value);
    }

    public IntegerExpression(BigInteger val) {
        this.value = val;
    }

    public BigInteger getValue() {
        return value;
    }
}
