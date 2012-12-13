package org.jsync.sync;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Delete nonexistent directories and files from source.
 */
public class DeleteNonExistentSource extends SyncAction {
    @Override
    public void syncFile(Path source, Path destination) throws IOException {
        Files.delete(source);
    }

    @Override
    public void postSyncDirectory(Path source, Path destination) throws IOException {
        Files.delete(source);
    }
}
