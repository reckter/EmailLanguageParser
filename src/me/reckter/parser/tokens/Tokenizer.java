package me.reckter.parser.tokens;

import me.reckter.parser.tokens.old.Selection;
import me.reckter.parser.tree.ParseException;

import javax.sound.sampled.Line;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hannes on 20/11/14.
 */
public class Tokenizer {

    public List<Token> tokens;

    public Tokenizer() {
        this.tokens = new ArrayList<>();
    }

    public Token identifyToken(String input) {
        if(LineEndingToken.matches(input)) {
            return new LineEndingToken(input);
        } else if(TriggerToken.matches(input)) {
            return new TriggerToken(input);
        } else if(AliasToken.matches(input)) {
            return new AliasToken(input);
        } else if(BlockOpenToken.matches(input)) {
            return new BlockOpenToken(input);
        } else if(BlockCloseToken.matches(input)) {
            return new BlockCloseToken(input);
        } else if(ActionToken.matches(input))  {
            return new ActionToken(input);
        } else if(SelectionToken.matches(input)) {
            return new SelectionToken(input);
        } else {
            return new IdentifierToken(input);
        }
    }

    public void parse(String input) throws ParseException {
        input += " \n";
        input = input.replaceAll("([^ ])\\{", "$1 {");
        input = input.replaceAll("([^ ])\\}", "$1 }");
        input = input.replaceAll("([^ ])\\n", "$1 \n");
        input = input.replaceAll("\\n", "\n ");

        input = parseAlias(input);

        List<String> tokenStrings = splitInput(input);
        for(String tokenString: tokenStrings) {
            if (!tokenString.equals("")) {
                tokens.add(identifyToken(tokenString));
            }
        }
        cleanUpTokens();

    }

    private String parseAlias(String input) throws ParseException {
        String[] tmp = input.split("\\n");
        String ret = "";
        Map<String, String> aliase = new HashMap<>();

        for(String string: tmp) {
            if(string.trim().startsWith("alias")) {
                List<String> splits = splitInput(string);
                splits.removeIf( a -> a.equals(""));
                if(splits.size() != 3) {
                    throw new ParseException("Expected alias with 2 arguments, but got " + (splits.size() - 1));
                }
                aliase.put(" " + splits.get(1) + " ", " " + splits.get(2) + " ");
            } else {
                ret += string + "\n";
            }
        }

        boolean foundSomething = true;
        while(foundSomething) {
            foundSomething = false;
            for(String key: aliase.keySet()) {
                if(ret.contains(key)) {
                    foundSomething = true;
                    ret = ret.replaceAll(key, aliase.get(key));
                }
            }
        }

        return ret;
    }


    private List<String> splitInput(String input) {
        List<String> splits = new ArrayList<>();

        int lastDivider = 0;
        boolean foundQuotation = false;
        String split = "";

        for(int i = 0; i < input.length(); i++) {

            if(input.charAt(i) == '"' && lastDivider == i) {
                foundQuotation = true;
            } else if(input.charAt(i) == '\"' && foundQuotation || input.charAt(i) == ' '&& !foundQuotation) {
                split = input.substring(lastDivider, i);
                if(foundQuotation) {
                    split = split.substring(1, split.length());
                }
                splits.add(split);
                lastDivider = i + 1;
                foundQuotation = false;
            }

        }
        return splits;
    }


    private void cleanUpTokens() {
        List<Token> cleanedUp = new ArrayList<>();
        Token lastToken = null;
        Token token = null;
        Token nextToken = null;
        boolean keep = true;
        for(int i = 0; i < tokens.size(); i++) {
            keep = true;
            if(i > 0) {
                lastToken = tokens.get(i - 1);
            }
            if(i < tokens.size() - 1) {
                nextToken = tokens.get(i + 1);
            }
            token = tokens.get(i);

            if((lastToken instanceof LineEndingToken) && (token instanceof LineEndingToken)) {
                keep = false;
            }

            if(keep) {
                cleanedUp.add(token);
            }
        }

        tokens = cleanedUp;
    }
}
