package me.reckter.parser.tokens;

/**
 * Created by hannes on 20/11/14.
 */
public class SelectionToken extends Token {
    public static final String IDENTIFIER = ":";
    public static final String ALL_IDENTIFIER = "everything";

    static boolean matches(String input) {
        return input.contains(IDENTIFIER) || input.equals(ALL_IDENTIFIER);
    }

    public SelectionToken(String origin) {
        super(origin);
    }
}
