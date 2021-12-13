package token;

import visitor.AbstractTokenVisitor;

public class MulOperation implements Operation {

    @Override
    public int execute(int left, int right) {
        return left * right;
    }

    @Override
    public int getPrior() {
        return 1;
    }

    @Override
    public String getSymbol() {
        return "MUL";
    }

    @Override
    public void accept(AbstractTokenVisitor visitor) {

    }
}
