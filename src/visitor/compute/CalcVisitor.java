package visitor.compute;

import exceptions.CaclException;
import exceptions.NotImplementedException;
import exceptions.ParserException;
import token.Brace;
import token.NumberToken;
import token.Operation;
import token.Token;
import visitor.AbstractTokenVisitor;

import java.util.List;
import java.util.Stack;

public class CalcVisitor extends AbstractTokenVisitor {

    private final List<Token> tokens;
    private final Stack<Integer> computationStack;

    public CalcVisitor(List<Token> tokens) {
        this.tokens = tokens;
        computationStack = new Stack<>();
    }

    public int calc() throws CaclException, ParserException, NotImplementedException {
        for (Token token : tokens) {
            visit(token);
        }
        if (computationStack.size() != 1) {
            throw new CaclException("Invalid reverse polish notation");
        }
        return computationStack.pop();
    }

    @Override
    public void visit(NumberToken token) {
        computationStack.add(token.getValue());
    }

    @Override
    public void visit(Brace token) throws NotImplementedException {
        throw new NotImplementedException("Brace token not implemented");
    }

    @Override
    public void visit(Operation token) {
        int right = computationStack.pop();
        int left = computationStack.pop();
        computationStack.add(token.execute(left, right));
    }
}
