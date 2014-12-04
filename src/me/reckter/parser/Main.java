package me.reckter.parser;

import me.reckter.parser.tokens.ActionToken;
import me.reckter.parser.tokens.Tokenizer;
import me.reckter.parser.tree.ParseException;
import me.reckter.parser.tree.TreeParser;

import java.io.*;
import java.nio.file.Files;

/**
 * Created by hannes on 20/11/14.
 */
public class Main {

    static public void main(String[] args) throws IOException, ParseException {
        Tokenizer tokenizer = new Tokenizer();

        ActionToken.addAction("tag");
        ActionToken.addAction("forward");
        ActionToken.addAction("untag");


        String reduce = Files.readAllLines(new File("test.txt").toPath()).stream().reduce((a,b) -> a + b + "\n").get();
        tokenizer.parse(reduce);
        TreeParser tree = new TreeParser();
        tree.parse(tokenizer);


    }
}
