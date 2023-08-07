package me.jugg.parser;

import me.jugg.ast.*;
import me.jugg.lexer.Lexer;
import me.jugg.token.Token;
import me.jugg.token.TokenType;

import java.math.BigInteger;
import java.util.HashMap;

public class Parser {

    final Lexer lexer;

    Token cur, peek;

    HashMap<TokenType, InfixParseFn> infixParseFnHashMap = new HashMap<>();
    HashMap<TokenType, PrefixParseFn> prefixParseFnHashMap = new HashMap<>();

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        prefixParseFnHashMap.put(TokenType.NUM, this::parseInteger);
        prefixParseFnHashMap.put(TokenType.MINUS, this::parsePrefixExpression);
        prefixParseFnHashMap.put(TokenType.LPAREN, this::parseGroupExpression);

        infixParseFnHashMap.put(TokenType.PLUS, this::parseInfixExpression);
        infixParseFnHashMap.put(TokenType.MINUS, this::parseInfixExpression);
        infixParseFnHashMap.put(TokenType.ASTERISK, this::parseInfixExpression);
        infixParseFnHashMap.put(TokenType.SLASH, this::parseInfixExpression);
        infixParseFnHashMap.put(TokenType.HAT, this::parseInfixExpression);
        nextToken();
        nextToken();
    }

    void nextToken() {
        cur = peek;
        peek = lexer.nextToken();
    }

    private boolean curTokenIs(TokenType type) {
        return cur.type == type;
    }

    private boolean peekTokenIs(TokenType type) {
        return peek.type == type;
    }

    public Expression parseMain() {
        return parseExpression(Precedence.LOWEST);
    }

    private int peekPrecedence() {
        return Precedence.getPrecedence(peek.type);
    }
    public Expression parseExpression(int precedence) {
        PrefixParseFn prefixParseFn = prefixParseFnHashMap.get(cur.type);
        if (prefixParseFn == null) {
            throw new RuntimeException("No prefix parse function for " + cur.type);
        }
        Expression left = prefixParseFn.parse();

        while (!peekTokenIs(TokenType.EOF) && precedence < peekPrecedence()) {
            InfixParseFn infixParseFn = infixParseFnHashMap.get(peek.type);
            if (infixParseFn == null) {
                return left;
            }
            nextToken();
            left = infixParseFn.parse(left);
        }

        return left;
    }
    Expression parseInfixExpression(Expression left) {
        InfixExpression infixExpression = new InfixExpression();
        infixExpression.left = left;
        infixExpression.operator = cur.value;
        int precedence = Precedence.getPrecedence(cur.type);
        nextToken();
        infixExpression.right = parseExpression(precedence);
        return infixExpression;
    }
    Expression parsePrefixExpression() {
        PrefixExpression prefixExpression = new PrefixExpression();
        prefixExpression.operator = cur.value;
        nextToken();
        prefixExpression.right = parseExpression(Precedence.PREFIX);

        return prefixExpression;
    }
    public Expression parseInteger() {
        BigInteger val = new BigInteger(cur.value);

        IntegerExpression integerExpression = new IntegerExpression(val);

        return integerExpression;
    }





    Expression parseGroupExpression() {
        nextToken();
        Expression exp = parseExpression(Precedence.LOWEST);
        nextToken();
        return exp;
    }

}
