package me.jugg.token;

import java.util.HashMap;
import java.util.Map;

public enum TokenType {

    NUM("NUM"),
    LPAREN("("),
    RPAREN(")"),

    MINUS("-"),
    PLUS("+"),

    ASTERISK("*"),
    HAT("^"),

    SLASH("/"),

    EOF("EOF")
;
    TokenType(String name) {
        this.name = name;
    }

    private String name;

    static Map<String, TokenType> map = new HashMap<>();

    static {
        map.put("(", LPAREN);
        map.put(")", RPAREN);
        map.put("-", MINUS);
        map.put("+", PLUS);
        map.put("*", ASTERISK);
        map.put("/", SLASH);
    }

    @Override
    public String toString() {
        return "TokenType(" + this.name() + ")";
    }
}
