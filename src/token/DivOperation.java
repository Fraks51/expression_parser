package token;

import visitor.AbstractTokenVisitor;

public class DivOperation implements Operation {

    @Override
    public int execute(int left, int right) {
        return left / right;
    }

    @Override
    public int getPrior() {
        return 1;
    }

    @Override
    public String getSymbol() {
        return "DIV";
    }

    @Override
    public void accept(AbstractTokenVisitor visitor) {

    }
}
