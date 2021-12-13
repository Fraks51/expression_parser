package token;

import visitor.AbstractTokenVisitor;

public class SubOperation implements Operation {

    @Override
    public int execute(int left, int right) {
        return left - right;
    }

    @Override
    public int getPrior() {
        return 2;
    }

    @Override
    public String getSymbol() {
        return "SUB";
    }

    @Override
    public void accept(AbstractTokenVisitor visitor) {

    }
}
