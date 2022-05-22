package io.github.mwttg.games.basic.utilities.files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    final var resource = TextFile.class.getResource(filename);
    if (resource == null) {
      throw new RuntimeException("Resource text file '" + filename + "' was not found.");
    }
    LOG.info("Reading text file from resource folder: '{}'", filename);

    final var file = resource.getFile();
    return read(file);
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
