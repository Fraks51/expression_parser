package tokenizer;

import exceptions.IncorrectInputException;
import token.*;

import java.util.LinkedList;
import java.util.List;

public class Tokenizer {

    private int index;
    private final String inputString;
    private char curChar;

    public Tokenizer(String string) throws IncorrectInputException {
        if (string.isEmpty()) {
            throw new IncorrectInputException("Empty input");
        }
        inputString = string;
        index = 0;
        curChar = string.charAt(0);
    }

    public void nextChar() {
        index++;
        if (index < inputString.length())
            curChar = inputString.charAt(index);
        else
            curChar = '\0';
    }

    private Token getToken() throws IncorrectInputException {
        Token tmpToken;
        if (index >= inputString.length()) {
            return null;
        }
        switch (curChar) {
            case '/':
                tmpToken = new DivOperation();
                break;
            case '*':
                tmpToken = new MulOperation();
                break;
            case '+':
                tmpToken = new AddOperation();
                break;
            case '-':
                tmpToken = new SubOperation();
                break;
            case '(':
                tmpToken = new LeftBrace();
                break;
            case ')':
                tmpToken = new RightBrace();
                break;
            case ' ':
            case '\t':
                nextChar();
                return getToken();
            default:
                if (Character.isDigit(curChar)) {
                    int res = Character.getNumericValue(curChar);
                    nextChar();
                    while (Character.isDigit(curChar)) {
                        res = res * 10 + Character.getNumericValue(curChar);
                        nextChar();
                    }
                    return new NumberToken(res);
                }
                throw new IncorrectInputException(
                        "Invalid char:" +
                                curChar + " in input"
                );
        }
        nextChar();
        return tmpToken;
    }

    public List<Token> getTokens() throws IncorrectInputException {
        List<Token> tokens = new LinkedList<>();
        while (true) {
            Token token = getToken();
            if (token == null) {
                break;
            }
            tokens.add(token);
        }
        return tokens;
    }

}
