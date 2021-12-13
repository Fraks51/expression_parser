package visitor.representation;

import exceptions.NotImplementedException;
import exceptions.ParserException;
import token.*;
import visitor.AbstractTokenVisitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor extends AbstractTokenVisitor {
    private int index;
    private final List<Token> tokens;
    private final List<Token> parsedTokens;
    private Token currentToken;
    private int balance;
    private Stack<Operation> operationStack;

    public ParserVisitor(List<Token> tokens) throws ParserException {
        if (tokens.isEmpty())
            throw new ParserException("Empty list of tokens");
        this.tokens = tokens;
        index = 0;
        balance = 0;
        currentToken = tokens.get(0);
        this.parsedTokens = new LinkedList<>();
        operationStack = new Stack<>();
    }

    private void nextToken() {
        index++;
        if (index < tokens.size())
            currentToken = tokens.get(index);
        else
            currentToken = null;
    }

    public List<Token> parse() throws ParserException, NotImplementedException {
        while (currentToken != null) {
            visit(currentToken);
            nextToken();
        }
        if (balance != 0) {
            throw new ParserException("Incorrect braces sequence");
        }
        return parsedTokens;
    }

    @Override
    public void visit(NumberToken token) throws ParserException, NotImplementedException {
        parsedTokens.add(token);
        lookNextTokenHaveBetterPrior();
    }

    @Override
    public void visit(Brace token) throws ParserException, NotImplementedException {
        if (token instanceof LeftBrace) {
            balance++;
            Stack<Operation> oldStack = operationStack;
            nextToken();
            operationStack = new Stack<>();
            if (currentToken instanceof RightBrace || token instanceof Operation) {
                throw new ParserException("Illegal tokens after left brace");
            }
            visit(currentToken);
            operationStack = oldStack;
            lookNextTokenHaveBetterPrior();
        } else {
            if (balance < 1) {
                throw new ParserException("Incorrect braces sequence");
            }
            balance--;
        }
    }

    @Override
    public void visit(Operation token) throws ParserException, NotImplementedException {
        operationStack.add(token);
        nextToken();
        if (currentToken == null) {
            throw new ParserException("End of input after operation");
        }
        if (currentToken instanceof RightBrace ||
            currentToken instanceof Operation) {
            throw new ParserException("Illegal token after operation");
        }
        visit(currentToken);
        parsedTokens.add(token);
        operationStack.pop();
    }


    private void lookNextTokenHaveBetterPrior() throws ParserException, NotImplementedException {
        if (index + 1 < tokens.size()) {
            Token nextToken = tokens.get(index + 1);
            if (nextToken instanceof LeftBrace || nextToken instanceof NumberToken) {
                throw new ParserException("Invalid token after number or close brace");
            }
            if (!(nextToken instanceof Operation
                    && !operationStack.isEmpty()
                    && ((Operation) nextToken).getPrior() > operationStack.lastElement().getPrior())) {
                nextToken();
                visit(currentToken);
            }
        }
    }
}
