package me.reckter.parser.tokens;

/**
 * Created by hannes on 20/11/14.
 */
public class BlockOpenToken extends Token {

    public static final String IDENTIFIER = "{";

    static boolean matches(String input) {
        return input.equals(IDENTIFIER);
    }

    public BlockOpenToken(String origin) {
        super(origin);
    }
}
