package org.jsync.sync;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Copy nonexistent/changed directories and files from source.
 */
public class CopySource extends SyncAction {
    @Override
    public void preSyncDirectory(Path source, Path destination) throws IOException {
        Files.createDirectories(destination);
    }

    @Override
    public void syncFile(Path source, Path destination) throws IOException {
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    }
}
