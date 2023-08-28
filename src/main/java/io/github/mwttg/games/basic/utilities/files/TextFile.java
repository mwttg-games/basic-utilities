package io.github.mwttg.games.basic.utilities.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public final class TextFile {

    private static final Logger LOG = LoggerFactory.getLogger(TextFile.class);

    private TextFile() {
    }

    /**
     * This function reads a text file and returns its content as a list of lines.
     * The checked Exceptions (like IOExceptions) are converted to unchecked Exceptions (RuntimeExceptions).
     *
     * @param filename the path and name of the text file
     * @return list of lines
     */
    public static List<String> readFrom(final String filename) {
        assert filename != null : "filename was null";
        LOG.info("Reading text file from: '{}'", filename);
        return read(filename);
    }

    /**
     * This functions reads a text file from the (Java) resource folder and returns its content as a list of lines.
     * The checked Exceptions (like IOExceptions) are converted to unchecked Exceptions (RuntimeExceptions).
     *
     * @param filename the path and name of the text file inside the resource folder
     * @return list of lines
     */
    public static List<String> readFromResource(final String filename) {
        assert filename != null : "filename was null";
        final var stream = TextFile.class.getResourceAsStream(filename);
        if (stream == null) {
            throw new RuntimeException("Resource text file '" + filename + "' was not found.");
        }
        LOG.info("Reading text file from resource folder: '{}'", filename);

        try {
            final var bufferedReader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException("A encoding problem occurred: ", e);
        }
    }

    private static List<String> read(final String filename) {
        final var path = Paths.get(filename);
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (final IOException ioException) {
            LOG.error("Error reading text file '{}'. The Exception was: ", filename, ioException);
            throw new RuntimeException(ioException);
        }
    }
}
