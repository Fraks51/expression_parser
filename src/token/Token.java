package token;

import exceptions.NotImplementedException;
import exceptions.ParserException;
import visitor.AbstractTokenVisitor;

public interface Token {
    void accept(AbstractTokenVisitor visitor) throws ParserException, NotImplementedException;
}
