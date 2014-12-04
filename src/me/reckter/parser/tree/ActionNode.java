package me.reckter.parser.tree;

        import me.reckter.parser.tokens.ActionToken;
        import me.reckter.parser.tokens.IdentifierToken;
        import me.reckter.parser.tokens.Token;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by hannes on 20/11/14.
 */
public class ActionNode extends Node{

    ActionToken action = null;
    List<IdentifierToken> arguments = new ArrayList<>();


    @Override
    List<Token> parse(List<Token> tokens) throws ParseException {
        if(tokens.get(0) instanceof ActionToken) {
            action = (ActionToken) tokens.get(0);
            tokens.remove(0);
            while(tokens.get(0) instanceof IdentifierToken) {
                arguments.add((IdentifierToken) tokens.get(0));
                tokens.remove(0);
                //TODO check number of arguments
            }
            return tokens;
        } else {
            throw new ParseException("expected an Action, but got '" + tokens.get(0).origin + "'");
        }
    }

    @Override
    public String getOutput() {
        String out = "\"" + action.origin + "\"";
        for(IdentifierToken argument: arguments) {
            out += ",\"" + argument.origin + "\"";
        }
        return out;
    }
}