package org.jsync.sync;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Defines action to do when syncing directories and files.
 */
public abstract class SyncAction {
    public void preSyncDirectory(Path source, Path destination) throws IOException {
    }

    public void syncFile(Path source, Path destination) throws IOException {
    }

    public void postSyncDirectory(Path source, Path destination) throws IOException {
    }
}
