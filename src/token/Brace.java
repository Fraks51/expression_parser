package token;

import exceptions.NotImplementedException;
import exceptions.ParserException;
import visitor.AbstractTokenVisitor;

public abstract class Brace implements Token {
    @Override
    public void accept(AbstractTokenVisitor visitor) throws NotImplementedException, ParserException {
        visitor.visit(this);
    }

    public abstract String getBrace();
}
