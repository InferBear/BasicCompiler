package me.jugg.parser;

import me.jugg.token.TokenType;

import java.util.HashMap;

public class Precedence {

    public static final int LOWEST = 0;
    public static final int EQUALS = 1;
    public static final int LESS_GREATER = 2;
    public static final int SUM = 3;

    public static final int PRODUCT = 4;

    public static final int POWER = 5;

    public static final int PREFIX = 6;
    public static final int CALL = 7;

    static HashMap<TokenType, Integer> precedences = new HashMap<>();

    static {
        precedences.put(TokenType.PLUS, SUM);
        precedences.put(TokenType.MINUS, SUM);
        precedences.put(TokenType.SLASH, PRODUCT);
        precedences.put(TokenType.ASTERISK, PRODUCT);
        precedences.put(TokenType.HAT, POWER);
        precedences.put(TokenType.LPAREN, CALL);
    }

    static int getPrecedence(TokenType t) {
        return precedences.getOrDefault(t, LOWEST);
    }

}
