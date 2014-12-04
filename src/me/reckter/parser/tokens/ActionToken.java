package me.reckter.parser.tokens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hannes on 20/11/14.
 */
public class ActionToken extends Token {


    static List<String> actions = new ArrayList<>();

    static public void addAction(String action) {
        actions.add(action);
    }
    static boolean matches(String input) {
        return actions.contains(input);
    }

    public ActionToken(String origin) {
        super(origin);
    }
}
