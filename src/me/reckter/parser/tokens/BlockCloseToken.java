package me.reckter.parser.tokens;

/**
 * Created by hannes on 20/11/14.
 */
public class BlockCloseToken extends Token {
    public static final String IDENTIFIER = "}";

    static boolean matches(String input) {
        return input.equals(IDENTIFIER);
    }
    public BlockCloseToken(String origin) {
        super(origin);
    }
}
