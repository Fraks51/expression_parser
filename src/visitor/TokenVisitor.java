package visitor;

import exceptions.NotImplementedException;
import exceptions.ParserException;
import token.Brace;
import token.NumberToken;
import token.Operation;

public interface TokenVisitor {
    void visit(NumberToken token) throws ParserException, NotImplementedException;
    void visit(Brace token) throws NotImplementedException, ParserException;
    void visit(Operation token) throws ParserException, NotImplementedException;
}
