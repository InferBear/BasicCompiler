import me.jugg.ast.Expression;
import me.jugg.evaluator.Evaluator;
import me.jugg.lexer.Lexer;
import me.jugg.parser.Parser;
import org.junit.Test;

public class LexerTest {


    @Test
    public void test() {
        String code = "1-(-2*2^3)";
        Lexer lexer = new Lexer(code);
        Parser p = new Parser(lexer);
        Expression expression = p.parseMain();
        System.out.println(Evaluator.eval(expression));
    }
}
