package me.jugg.parser;


import me.jugg.ast.Expression;

public interface PrefixParseFn {
    Expression parse();
}
