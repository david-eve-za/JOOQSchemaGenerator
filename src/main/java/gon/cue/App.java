package gon.cue;

import joptsimple.OptionException;
import gon.cue.cli.Options;
import gon.cue.jooq.GenerateFromDB;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Options opts = null;
        try {
            opts = Options.parse(args);
        } catch (OptionException e) {
            Options.printHelp(System.out);
            return;
        }
        
        GenerateFromDB.startGenerate(opts);

    }
}
