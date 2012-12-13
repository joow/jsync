package org.jsync;

import java.io.IOException;

/**
 * Commandline client for jsync.
 */
public class JSyncCli {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("usage: java -jar jsync.jar <from> <to> <excluded path 1> ... <excluded path n>");
            return;
        }

        JSync jSync = new JSync(args[0], args[1]);

        for (int i = 2; i < args.length; i++) {
            jSync.addExcluded(args[i]);
        }

        jSync.synchronize();
    }
}
