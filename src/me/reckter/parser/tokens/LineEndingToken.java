package me.reckter.parser.tokens;

/**
 * Created by hannes on 20/11/14.
 */
public class LineEndingToken extends Token {

    public static final String IDENTIFIER = "\n";

    static boolean matches(String input) {
        return input.equals(IDENTIFIER);
    }

    public LineEndingToken(String origin) {
        super(origin);
    }
}
