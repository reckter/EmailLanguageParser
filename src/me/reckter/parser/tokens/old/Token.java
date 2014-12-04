package me.reckter.parser.tokens.old;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hannes on 19/11/14.
 */
public class Token {

    public ArrayList<Token> content = new ArrayList<>();
    public String out;

    public Token(String out) {
        this.out = out;
    }

    public String getOutput() {
        String[] tmp = out.split("\\$");
        String ret = tmp[0];

        Pattern numberPatter = Pattern.compile("^(\\d\\d*)(\\D.*)$");
        for(int i = 1; i < tmp.length - 1; i++) {
            Matcher matcher = numberPatter.matcher(tmp[1]);
            int index = Integer.parseInt(matcher.group(1));
            ret += content.get(index).getOutput();
            ret +=matcher.group(2);
        }

        return ret;
    }
}

