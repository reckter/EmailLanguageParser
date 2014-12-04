package me.reckter.parser.tree;

import me.reckter.parser.tokens.*;

import java.util.List;

/**
 * Created by hannes on 20/11/14.
 */
public class CommandNode extends Node{


    SelectionNode selection;
    ActionNode action;
    BlockNode block;

    boolean isBlock;

    @Override
    List<Token> parse(List<Token> tokens) throws ParseException {
        if(tokens.get(0) instanceof ActionToken) {
            isBlock = false;
            action = new ActionNode();
            tokens = action.parse(tokens);
            if(tokens.get(0) instanceof LineEndingToken) {
                tokens.add(0, new SelectionToken(""));
            }
            selection = new SelectionNode();
            tokens = selection.parse(tokens);
            if(tokens.get(0) instanceof LineEndingToken) {
                tokens.remove(0);
            }

            return tokens;


        } else {
            isBlock = true;
            if(tokens.get(0) instanceof BlockOpenToken) {
                tokens.add(0, new SelectionToken(""));
            }
            selection = new SelectionNode();
            tokens = selection.parse(tokens);
            block = new BlockNode();
            tokens = block.parse(tokens);


            if(tokens.get(0) instanceof LineEndingToken) {
                tokens.remove(0);
            }
            return tokens;
        }
    }

    public String getOutput() {
        String out = "this";
        String selectionString = selection.getOutput();
        if(!selectionString.equals("")) {
            out += ".select(\"" + selectionString + "\")";
        }
        if(isBlock) {
            out += ".action(function() {\n" + block.getOutput() + "});";
        } else {
            out += ".action(" + action.getOutput() + ");";
        }

        return out;
    }
}
