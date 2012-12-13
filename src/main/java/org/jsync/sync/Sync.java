package org.jsync.sync;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class used to check if directories and files have to be synced.
 */
final class Sync {
    private final Path from;

    private final Path to;

    private final SyncAction syncAction;

    public Sync(Path from, Path to, SyncAction syncAction) {
        this.from = from;
        this.to = to;
        this.syncAction = syncAction;
    }

    public final void preSyncDirectory(Path source) throws IOException {
        final Path destination = getDestination(source);

        if (shouldSync(source, destination)) {
            syncAction.preSyncDirectory(source, destination);
        }
    }

    public final void syncFile(Path source) throws IOException {
        final Path destination = getDestination(source);

        if (shouldSync(source, destination)) {
            syncAction.syncFile(source, destination);
        }
    }

    public final void postSyncDirectory(Path source) throws IOException {
        final Path destination = getDestination(source);

        if (shouldSync(source, destination)) {
            syncAction.postSyncDirectory(source, destination);
        }
    }

    private Path getDestination(Path source) {
        final Path relative = from.relativize(source);
        return to.resolve(relative);
    }

    private boolean shouldSync(Path source, Path destination) {
        return Files.notExists(destination) || new FileChecksum(source) != new FileChecksum(destination);
    }
}
