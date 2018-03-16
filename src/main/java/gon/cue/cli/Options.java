package gon.cue.cli;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import joptsimple.OptionParser;
import joptsimple.OptionSpec;

public class Options {
    private static final OptionParser     parser;

    private static final OptionSpec<Void> dbType;
    private static final OptionSpec<String> dbLocation;
    private static final OptionSpec<String> dbUser;
    private static final OptionSpec<String> dbPassword;
    private static final OptionSpec<String> codeDestination;
    private static final OptionSpec<String> packDestination;

    static {
        parser = new OptionParser() {
            {
                acceptsAll(Arrays.asList("?", "h", "help"), "Shows this help").isForHelp();
            }
        };
        dbType = parser.acceptsAll(Arrays.asList("h2", "H2"), "Driver to use on the process");
        dbLocation = parser.acceptsAll(Arrays.asList("l", "L"), "May be server/local otherwise generate error").withRequiredArg().ofType(String.class).required();
        dbUser = parser.acceptsAll(Arrays.asList("usr", "USR"), "Database User").withRequiredArg().ofType(String.class).required();
        dbPassword = parser.acceptsAll(Arrays.asList("pass", "PASS"), "Database Password").withRequiredArg().ofType(String.class).required();
        codeDestination = parser.acceptsAll(Arrays.asList("dest", "DEST"), "Code generation destination").withRequiredArg().ofType(String.class).required();
        packDestination = parser.acceptsAll(Arrays.asList("pack", "PACK"), "Code generation destination").withRequiredArg().ofType(String.class).required();

    }

    public static void printHelp(OutputStream out) {
        try {
            parser.printHelpOn(out);
        } catch (IOException e) {
            throw new RuntimeException("Can't write help to out", e);
        }
    }
}
