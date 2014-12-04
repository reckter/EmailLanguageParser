package me.reckter.parser.tree;

import me.reckter.parser.tokens.BlockCloseToken;
import me.reckter.parser.tokens.BlockOpenToken;
import me.reckter.parser.tokens.LineEndingToken;
import me.reckter.parser.tokens.Token;
import me.reckter.parser.tokens.old.Command;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannes on 20/11/14.
 */
public class BlockNode extends Node {

    List<CommandNode> commands = new ArrayList<>();

    @Override
    List<Token> parse(List<Token> tokens) throws ParseException {
        if(!(tokens.get(0) instanceof BlockOpenToken)) {
            throw new ParseException("Expected '{' but got '" + tokens.get(0).origin + "'");
        }
        tokens.remove(0);

        while(tokens.size() > 0 && !(tokens.get(0) instanceof BlockCloseToken)) {
            if(tokens.get(0) instanceof LineEndingToken) {
                tokens.remove(0);
            } else {
                CommandNode commandNode = new CommandNode();
                tokens = commandNode.parse(tokens);
                commands.add(commandNode);
            }
        }
        if(tokens.size() == 0) {
            throw new ParseException("Expected '}' but reached EOF");
        }
        tokens.remove(0);
        return tokens;
    }

    @Override
    public String getOutput() {
        String out = "";
        for(CommandNode command: commands) {
            out += command.getOutput() + "\n";
        }
        return out;
    }
}
