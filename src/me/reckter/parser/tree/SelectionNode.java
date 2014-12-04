package me.reckter.parser.tree;

import me.reckter.parser.tokens.SelectionToken;
import me.reckter.parser.tokens.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by hannes on 20/11/14.
 */
public class SelectionNode extends Node {


    List<SelectionToken> selections = new ArrayList<>();

    @Override
    List<Token> parse(List<Token> tokens) throws ParseException {
        while(tokens.get(0) instanceof SelectionToken) {
            selections.add((SelectionToken) tokens.get(0));
            tokens.remove(0);
        }
        if(selections.size() == 0) {
            throw new ParseException("Expected an Selection, but got '" + tokens.get(0).origin + "'");
        }
        return tokens;
    }

    @Override
    public String getOutput() {
        String out = "";
        for(SelectionToken selection: selections) {
            out += selection.origin + " ";
        }
        out = out.substring(0, out.length() - 1);
        return out;
    }
}
