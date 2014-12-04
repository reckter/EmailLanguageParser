package me.reckter.parser.tree;

import me.reckter.parser.tokens.IdentifierToken;
import me.reckter.parser.tokens.Token;
import me.reckter.parser.tokens.TriggerToken;

import java.util.List;

/**
 * Created by hannes on 20/11/14.
 */
public class TriggerNode extends Node {

    IdentifierToken event;

    CommandNode command;

    @Override
    List<Token> parse(List<Token> tokens) throws ParseException {
        if(!(tokens.get(0) instanceof TriggerToken)) {
            throw new ParseException("Expedted 'on' but got: " + tokens.get(1).origin);
        }
        tokens.remove(0);

        if(!(tokens.get(0) instanceof IdentifierToken)) {
            throw new ParseException("Expedted an event but got: " + tokens.get(1).origin);
        }

        event = (IdentifierToken) tokens.get(0);
        tokens.remove(0);

        command = new CommandNode();
        tokens = command.parse(tokens);

        return tokens;
    }

    @Override
    public String getOutput() {
        String out = "EventSystem.on(\"" + event.origin + "\", function(){\n" + command.getOutput() + "\n});";
        return out;
    }
}
