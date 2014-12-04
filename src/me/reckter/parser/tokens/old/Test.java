package me.reckter.parser.tokens.old;

/**
 * Created by hannes on 19/11/14.
 */
public class Test {

    static public void main(String[] args) {

        StringToken stringToken = new StringToken("from:@mhlz.de");
        Selection selection = new Selection();
        selection.content.add(stringToken);

        stringToken = new StringToken("tag school");
        Action action = new Action();
        action.content.add(selection);

        Command command = new Command();
        command.content.add(action);
        command.content.add(selection);
        System.out.println(command.getOutput());
    }
}
