package me.reckter.parser.tokens;

/**
 * Created by hannes on 20/11/14.
 */
public abstract class Token {
    public String origin;

    public Token(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return origin;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Token) {
            return this.getClass().equals(obj.getClass()) && origin.equals(((Token) obj).origin);
        }
        return super.equals(obj);
    }
}
