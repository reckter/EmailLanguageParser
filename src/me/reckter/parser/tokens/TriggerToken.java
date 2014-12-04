package me.reckter.parser.tokens;

/**
 * Created by hannes on 20/11/14.
 */
public class TriggerToken extends Token {

    public static final String IDENTIFIER = "on";

    static boolean matches(String input) {
        return input.equals(IDENTIFIER);
    }

    public TriggerToken(String origin) {
        super(origin);
    }
}
