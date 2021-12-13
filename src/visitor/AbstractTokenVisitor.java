package visitor;

import exceptions.NotImplementedException;
import exceptions.ParserException;
import token.Brace;
import token.NumberToken;
import token.Operation;
import token.Token;

abstract public class AbstractTokenVisitor implements TokenVisitor {
      protected void visit(Token token) throws ParserException, NotImplementedException {
        if (token instanceof Brace) {
            visit((Brace) token);
        } else if (token instanceof Operation) {
            visit((Operation) token);
        } else if (token instanceof NumberToken) {
            visit((NumberToken) token);
        } else {
            throw new ParserException("Invalid token for visit");
        }
    }
}
