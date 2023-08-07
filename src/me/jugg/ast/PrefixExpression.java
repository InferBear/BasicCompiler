package me.jugg.ast;

public class PrefixExpression extends Expression {
    public String operator;
    public Expression right;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        sb.append(operator);
        sb.append(right);
        sb.append(")");

        sb.append("\n");

        return sb.toString();
    }
}
