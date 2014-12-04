package me.reckter.parser.tokens;

/**
 * Created by hannes on 20/11/14.
 */
public class AliasToken extends Token {


    public static final String IDENTIFIER = "alias";

    static boolean matches(String input) {
        return input.equals(IDENTIFIER);
    }

    public AliasToken(String origin) {
        super(origin);
    }
}
