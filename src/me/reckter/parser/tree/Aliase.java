package me.reckter.parser.tree;

import java.util.HashMap;

/**
 * Created by hannes on 20/11/14.
 */
public class Aliase {
    HashMap<String, HashMap<String, String>> aliase;


    public Aliase() {
        aliase = new HashMap<>();
        aliase.put("action", new HashMap<>());
        aliase.put("selection", new HashMap<>());
    }

    public Aliase(Aliase aliase) {
        try {
            this.aliase = (HashMap<String, HashMap<String, String>>) aliase.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void addAlias(String category, String alias, String replacement) throws ParseException {
        if(!aliase.containsKey(category)) {
            throw new ParseException("The category '" + category + "' is not a valid alias category!");
        }
        aliase.get(category).put(alias,replacement);
    }

    public boolean exists(String category, String check) throws ParseException {
        if(!aliase.containsKey(category)) {
            throw new ParseException("The category '" + category + "' is not a valid alias category!");
        }
        return aliase.get(category).containsKey(check);

    }

    public String replace(String category, String check) throws ParseException {
        if(!exists(category, check)) {
            throw new ParseException("There exists now alias '" + check + "' in the category '" + category + "'");
        }

        return aliase.get(category).get(check);
    }


}
