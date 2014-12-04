package me.reckter.parser.tree;

import me.reckter.parser.tokens.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannes on 20/11/14.
 */
public class TreeParser {
    List<Node> startingNodes = new ArrayList<>();



    public void parse(Tokenizer tokenizer) throws ParseException {
        parse(tokenizer.tokens);
    }

    public void parse(List<Token> tokens) throws ParseException {
        while(tokens.size() > 0) {
            if(tokens.get(0) instanceof LineEndingToken) {
                tokens.remove(0);
            } else if((tokens.get(0) instanceof ActionToken) || (tokens.get(0) instanceof SelectionToken)) {
                Node command = new CommandNode();
                tokens = command.parse(tokens);
                startingNodes.add(command);
            } else if(tokens.get(0) instanceof TriggerToken) {
                Node trigger = new TriggerNode();
                tokens = trigger.parse(tokens);
                startingNodes.add(trigger);
            } else {
                throw new ParseException("unexpected '" + tokens.get(0).origin + "'(" + tokens.get(0).getClass() + ")");
            }
        }
        System.out.println("done parsing");
    }
}
