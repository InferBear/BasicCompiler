package me.jugg.lexer;

import me.jugg.token.Token;
import me.jugg.token.TokenType;

public class Lexer {

    final String input;

    char ch;
    int curPos, peekPos;
    public Lexer(String input) {
        this.input = input;
        curPos = 0;
        peekPos = 0;
        readChar();
    }

    public char peekChar() {
        return input.charAt(peekPos);
    }

    public Token nextToken() {
        Token token;
        skipWhitespace();
        switch (ch) {
            case '+' -> {
                token = new Token(TokenType.PLUS, "+");
            }
            case '-' -> {
                token = new Token(TokenType.MINUS, "-");
            }
            case '*' -> {
                token = new Token(TokenType.ASTERISK, "*");
            }
            case '/' -> {
                token = new Token(TokenType.SLASH, "/");
            }
            case '(' -> {
                token = new Token(TokenType.LPAREN, "(");
            }
            case ')' -> {
                token = new Token(TokenType.RPAREN, ")");
            }
            case '^' -> {
                token = new Token(TokenType.HAT, "^");
            }
            case 0 -> {
                token = new Token(TokenType.EOF, "");
            }
            default -> {
                if (isDigit(ch)) {
                    String num = readNum();
                    token = new Token(TokenType.NUM, num);
                    return token;
                } else {
                    throw new RuntimeException("Lexer error");
                }
            }
        }
        readChar();
        return token;
    }

    String readNum() {
        StringBuilder num = new StringBuilder();
        while (isDigit(ch)) {
            num.append(ch);
            readChar();
        }
        return num.toString();
    }

    boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    void skipWhitespace() {
        while (hasNext()) {
            if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r')  {
                readChar();
            } else break;
        }
    }

    public boolean hasNext() {
            return peekPos <= input.length();
    }

    void readChar() {
        if (peekPos >= input.length()) {
            ch = 0;
        } else {
            ch = input.charAt(peekPos);
        }
        curPos = peekPos;
        peekPos += 1;
    }

}
