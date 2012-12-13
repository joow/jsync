package org.jsync;

import org.jsync.sync.CopySource;
import org.jsync.sync.DeleteNonExistentSource;
import org.jsync.sync.SyncFileVisitor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: benoit
 * Date: 20/10/12
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
// TODO : filtres, hook (utile ?), listener et exceptions !
public class JSync {
    private final Path from;

    private final Path to;

    private List<String> excludes = new ArrayList<>();

    public JSync(String from, String to) {
        this.from = Paths.get(from);
        this.to = Paths.get(to);
    }

    public JSync addExcluded(String excluded) {
        excludes.add(excluded);
        return this;
    }

    public void synchronize() throws IOException {
        if (Files.notExists(from)) {
            throw new FileNotFoundException(from + " doesn't exists.");
        }

        Files.walkFileTree(from, new SyncFileVisitor(from , to , new CopySource()));
        Files.walkFileTree(to, new SyncFileVisitor(from, to, new DeleteNonExistentSource()));
    }
}
