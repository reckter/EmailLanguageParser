package me.reckter.parser.tokens.old;

/**
 * Created by hannes on 20/11/14.
 */
public class StringToken extends Token {


    public StringToken(String out) {
        super(out);
    }

    @Override
    public String getOutput() {
        return out;
    }
}
