import exceptions.CaclException;
import exceptions.IncorrectInputException;
import exceptions.NotImplementedException;
import exceptions.ParserException;
import token.Token;
import tokenizer.Tokenizer;
import visitor.compute.CalcVisitor;
import visitor.representation.ParserVisitor;
import visitor.representation.PrintVisitor;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        while (sc.hasNextLine()) {
            String inputString = sc.nextLine();
            input.append(" ");
            input.append(inputString);
        }
        try {
            Tokenizer tokenizer = new Tokenizer(input.toString());
            List<Token> tokens = tokenizer.getTokens();

            ParserVisitor parserVisitor = new ParserVisitor(tokens);
            List<Token> parsed = parserVisitor.parse();

            System.out.println("Tokens in reverse polish notation");
            PrintVisitor printVisitor = new PrintVisitor(parsed);
            printVisitor.print();

            System.out.println("Result:");
            CalcVisitor calcVisitor = new CalcVisitor(parsed);
            System.out.println(calcVisitor.calc());
        } catch (IncorrectInputException |
                ParserException |
                NotImplementedException |
                CaclException e) {
            e.printStackTrace();
        }
    }
}
