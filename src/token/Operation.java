package token;

public interface Operation extends Token {
    int execute(int left, int right);
    int getPrior();
    String getSymbol();
}
