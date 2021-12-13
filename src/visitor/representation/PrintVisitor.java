package visitor.representation;

import exceptions.NotImplementedException;
import exceptions.ParserException;
import token.Brace;
import token.NumberToken;
import token.Operation;
import token.Token;
import visitor.AbstractTokenVisitor;

import java.util.List;

public class PrintVisitor extends AbstractTokenVisitor {

    private final List<Token> tokens;

    public PrintVisitor(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void print() throws ParserException, NotImplementedException {
        for (Token token : tokens) {
            visit(token);
        }
        System.out.println(".");
    }

    @Override
    public void visit(NumberToken token) {
        System.out.print("NUMBER(" +
                token.getValue() +
                ") "
        );
    }

    @Override
    public void visit(Brace token) {
        System.out.print(token.getBrace() + " ");
    }

    @Override
    public void visit(Operation token) {
        System.out.print(token.getSymbol() + " ");
    }
}
