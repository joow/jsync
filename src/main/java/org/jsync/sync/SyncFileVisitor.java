package org.jsync.sync;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * File visitor to synchronize source and destination paths.
 */
public class SyncFileVisitor implements FileVisitor<Path> {
    private final Sync sync;

    public SyncFileVisitor(Path from , Path to, SyncAction syncAction) {
        this.sync = new Sync(from, to, syncAction);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path paths, BasicFileAttributes basicFileAttributes) throws IOException {
        sync.preSyncDirectory(paths);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path paths, BasicFileAttributes basicFileAttributes) throws IOException {
        sync.syncFile(paths);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path paths, IOException e) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path paths, IOException e) throws IOException {
        sync.postSyncDirectory(paths);
        return FileVisitResult.CONTINUE;
    }
}
