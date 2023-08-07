package me.jugg.evaluator;

import me.jugg.ast.InfixExpression;
import me.jugg.ast.IntegerExpression;
import me.jugg.ast.Node;
import me.jugg.ast.PrefixExpression;
import me.jugg.object.MInt;
import me.jugg.object.MObj;

import java.util.Objects;

public class Evaluator {


    public static MObj eval(Node node) {

        if (node instanceof IntegerExpression e) {
            return new MInt(e.getValue());
        } else if (node instanceof PrefixExpression pre) {
            if (Objects.equals(pre.operator, "-")) {
                return minus(node);
            } else if (Objects.equals(pre.operator, "+")) {
                return eval(pre.right);
            }
        } else if (node instanceof InfixExpression infix) {
            var left = eval(infix.left);
            var right = eval(infix.right);

            return op(left, right, infix.operator);
        }

        return null;
    }

    public static MObj op(MObj left, MObj right, String operator) {
        if (left instanceof MInt leftInt && right instanceof MInt rightInt) {
            return switch (operator) {
                case "+" -> new MInt(leftInt.value.add(rightInt.value));
                case "-" -> new MInt(leftInt.value.subtract(rightInt.value));
                case "*" -> new MInt(leftInt.value.multiply(rightInt.value));
                case "/" -> new MInt(leftInt.value.divide(rightInt.value));
                case "^" -> new MInt(leftInt.value.pow(rightInt.value.intValue()));
                default -> null;
            };
        }
        return null;
    }

    public static MObj minus(Node node) {
        if (node instanceof PrefixExpression ) {
            MObj m = eval(((PrefixExpression) node).right);
            if (m instanceof MInt mint) {
                return new MInt(mint.value.negate());
            }
        }
        return null;
    }
}
