package token;

import exceptions.NotImplementedException;
import exceptions.ParserException;
import visitor.AbstractTokenVisitor;

public class NumberToken implements Token {

    private final int value;

    public NumberToken(int value) {
        this.value = value;
    }

    @Override
    public void accept(AbstractTokenVisitor visitor) throws ParserException, NotImplementedException {
        visitor.visit(this);
    }

    public int getValue() {
        return value;
    }
}
