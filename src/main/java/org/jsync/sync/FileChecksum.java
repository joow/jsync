package org.jsync.sync;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.CRC32;

/**
 * Calculates the checksum of a file.
 */
class FileChecksum {
    private static final ByteBuffer BUFFER = ByteBuffer.allocate(8 * 1024);

    private static final CRC32 CRC_32 = new CRC32();

    private final Path path;

    public FileChecksum(Path path) {
        this.path = path;
    }

    /**
     * Calculates and return the checksum of a file. If path corresponds to a directory, a checksum of 0 is returned.
     *
     * @return the checksum (CRC32) of the file, 0 if path represents a directory.
     * @throws IOException If any I/O error occured while reading the file.
     */
    public long getChecksum() throws IOException {
        if (Files.isDirectory(path)) {
            return 0;
        }

        try (SeekableByteChannel channel = Files.newByteChannel(path, StandardOpenOption.READ)) {
            BUFFER.clear();
            CRC_32.reset();

            int read = 0;
            while ((read = channel.read(BUFFER)) > 0) {
                BUFFER.limit(read);
                CRC_32.update(BUFFER.array(), 0, BUFFER.limit());
                BUFFER.clear();
            }

            return CRC_32.getValue();
        }
    }
}
