package me.reckter.parser.tree;

import me.reckter.parser.tokens.Token;

import java.util.List;

/**
 * Created by hannes on 20/11/14.
 */
public abstract class Node {

    String out;

    List<Node> children;

    String origin;


    abstract List<Token> parse(List<Token> tokens) throws ParseException;
    abstract public String getOutput();

    @Override
    public String toString() {
        return getOutput();
    }
}
