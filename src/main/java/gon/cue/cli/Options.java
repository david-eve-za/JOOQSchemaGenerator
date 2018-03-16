package gon.cue.cli;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

public class Options {
    private static final OptionParser       parser;

    private static final OptionSpec<String> dbType;
    private static final OptionSpec<String> dbLocation;
    private static final OptionSpec<String> dbUser;
    private static final OptionSpec<String> dbPassword;
    private static final OptionSpec<String> codeDestination;
    private static final OptionSpec<String> packDestination;
    private static final OptionSpec<String> dbName;

    static {
        parser = new OptionParser() {
            {
                acceptsAll(Arrays.asList("?", "h", "help"), "Shows this help").isForHelp();
            }
        };
        dbType =
                 parser.acceptsAll(Arrays.asList("drv", "DRV"), "Driver to use on the process").withRequiredArg().ofType(String.class)
                       .required();
        dbName = parser.acceptsAll(Arrays.asList("db", "DB"), "DataBase Name").withRequiredArg().ofType(String.class).required();
        dbLocation =
                     parser.acceptsAll(Arrays.asList("l", "L"), "May be server/local otherwise generate error").withRequiredArg()
                           .ofType(String.class).required();
        dbUser = parser.acceptsAll(Arrays.asList("usr", "USR"), "Database User").withRequiredArg().ofType(String.class).required();
        dbPassword =
                     parser.acceptsAll(Arrays.asList("pass", "PASS"), "Database Password").withRequiredArg().ofType(String.class)
                           .required();
        codeDestination =
                          parser.acceptsAll(Arrays.asList("dest", "DEST"), "Code generation destination").withRequiredArg()
                                .ofType(String.class).required();
        packDestination =
                          parser.acceptsAll(Arrays.asList("pack", "PACK"), "Code generation package destination").withRequiredArg()
                                .ofType(String.class).required();

    }

    public static void printHelp(OutputStream out) {
        try {
            parser.printHelpOn(out);
        } catch (IOException e) {
            throw new RuntimeException("Can't write help to out", e);
        }
    }

    public static Options parse(String... args) {
        OptionSet opts = parser.parse(args);

        return new Options(opts.valueOf(dbType), opts.valueOf(dbLocation), opts.valueOf(codeDestination), opts.valueOf(dbUser),
                           opts.valueOf(dbPassword), opts.valueOf(packDestination), opts.valueOf(dbName));
    }

    private String Driver;

    public String getDriver() {
        return Driver;
    }

    private String Location;

    public String getLocation() {
        return Location;
    }

    private String Destination;

    public String getDestination() {
        return Destination;
    }

    private String User;

    public String getUser() {
        return User;
    }

    private String Password;

    public String getPassword() {
        return Password;
    }

    private String Package;

    public String getPackage() {
        return Package;
    }

    private String DBName;

    public String getDBName() {
        return DBName;
    }

    public Options(String Drv, String Location, String Dest, String Usr, String Pass, String Pkg, String dbN) {

        setDriver(Drv);
        this.Location = Location;
        this.Destination = Dest;
        this.User = Usr;
        this.Password = Pass;
        this.Package = Pkg;
        this.DBName = dbN;
    }

    private void setDriver(String drv) {
        switch (drv.toLowerCase()) {
            case "h2":
                this.Driver = "org.h2.Driver";
                break;
        }
    }
}
