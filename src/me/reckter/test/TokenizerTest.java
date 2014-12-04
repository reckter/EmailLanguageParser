package me.reckter.test;

import junit.framework.Assert;
import me.reckter.parser.tokens.*;
import me.reckter.parser.tree.ParseException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {
    static Tokenizer tokenizer;

    @BeforeClass
    static public void setup() {
        ActionToken.addAction("tag");
        ActionToken.addAction("untag");
        ActionToken.addAction("forward");
        ActionToken.addAction("reply");
    }

    @Before
    public void initTokenizer() {
        tokenizer = new Tokenizer();
    }

    @Test
    public void testSimpleAction() throws ParseException {

        testParser("tag cool from:.*@mhlz.de", Arrays.asList(new ActionToken("tag"), new IdentifierToken("cool"), new SelectionToken("from:.*@mhlz.de"), new LineEndingToken("\n")));
    }

    public void testParser(String input, List<Token> expected) throws ParseException {

        tokenizer.parse(input);
        List<Token> parseToken = tokenizer.tokens;
        assertList(parseToken, expected);

    }


    static public <T> void assertList(List<T> actual, List<T> expected) {
        if (actual.size() != expected.size()) {
            Assert.assertTrue("The Lists aren't the same size!", false);
        }
        for (int i = 0; i < actual.size(); i++) {
            Assert.assertEquals("The elements at index " + i + " aren't the same!", actual.get(i), expected.get(i));
        }
    }
}