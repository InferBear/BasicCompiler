package me.jugg.token;

public class Token {

    public TokenType type;
    public String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Token() {

    }


    @Override
    public String toString() {
        return "Token(" + type + ", " + value + ")";
    }
}
