package io.dummymaker.writer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

/**
 * Default Comment
 *
 * @author GoodforGod
 * @since 31.05.2017
 */
public class BufferedFileWriter implements IWriter {

    private final Logger logger = Logger.getLogger(BufferedFileWriter.class.getName());

    private BufferedWriter writer;

    /**
     * @param fileName file name
     * @param path     path where to create file (NULL or EMPTY for home dir)
     * @param extension file extension
     */
    public BufferedFileWriter(final String fileName, final String path, final String extension) throws IOException {
        try {
            final String workPath = buildPath(fileName, path, extension);
            this.writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(workPath), "UTF-8")
            );
        } catch (IOException e) {
            logger.warning(e.getMessage() + " | CAN NOT CREATE BUFFERED WRITER.");
            throw e;
        }
    }

    private String buildPath(final String fileName, final String path, final String extension) {
        final String workPath = (path == null || path.trim().isEmpty())
                ? ""
                : path;

        return workPath + fileName + extension;
    }

    @Override
    public boolean write(final String value) {
        try {
            writer.write(value);
            writer.newLine();
            return true;
        } catch (IOException e) {
            logger.warning(e.getMessage());
            flush();
            return false;
        }
    }

    @Override
    public boolean flush() {
        try {
            if (writer != null)
                writer.close();
            return true;
        } catch (IOException e) {
            logger.warning(e.getMessage() + " | CAN NOT CLOSE WRITER");
            return false;
        }
    }
}
