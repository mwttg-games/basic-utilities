package io.github.mwttg.games.basic.utilities.files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonFile {

  private static final Logger LOG = LoggerFactory.getLogger(JsonFile.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private JsonFile() {
  }

  /**
   * This function reads a JSON file and returns an object which represents the content.
   * The checked Exceptions (like IOExceptions) are converted to unchecked Exceptions (RuntimeExceptions).
   *
   * @param filename the path and name of the JSON file
   * @param clazz    the result class
   * @return deserialized JSON content
   */
  public static <T> T readFrom(final String filename, final Class<T> clazz) {
    assert filename != null : "filename was null";
    assert clazz != null : "result class was null";
    LOG.info("Reading json file from: '{}'", filename);
    return read(filename, clazz);
  }

  /**
   * This function reads a JSON file and returns an object which represents the content.
   * The checked Exceptions (like IOExceptions) are converted to unchecked Exceptions (RuntimeExceptions).
   *
   * @param filename the path and name of the JSON file
   * @param type    the result type
   * @return deserialized JSON content
   */
  public static <T> T readFrom(final String filename, final TypeReference<T> type) {
    assert filename != null : "filename was null";
    assert type != null : "result type was null";
    LOG.info("Reading json file from: '{}'", filename);
    try {
      return MAPPER.readValue(new File(filename), type);
    } catch (final IOException ioException) {
      LOG.error("Error reading json file '{}' and mapping it to '{}'. The Exception was: ",
          filename,
          type,
          ioException);
      throw new RuntimeException(ioException);
    }
  }

  /**
   * This functions reads a JSON file from the (Java) resource folder and returns an object which represents the content.
   * The checked Exceptions (like IOExceptions) are converted to unchecked Exceptions (RuntimeExceptions).
   *
   * @param filename the path and name of the JSON file inside the resource folder
   * @param clazz    the result type
   * @return deserialized JSON content
   */
  public static <T> T readFromResource(final String filename, final Class<T> clazz) {
    assert filename != null : "filename was null";
    assert clazz != null : "result type was null";
    final var resource = TextFile.class.getResource(filename);
    if (resource == null) {
      throw new RuntimeException("Resource json file '" + filename + "' was not found.");
    }
    LOG.info("Reading json file from resource folder: '{}'", filename);

    final var file = resource.getFile();
    return read(file, clazz);
  }

  private static <T> T read(final String filename, final Class<T> clazz) {
    try {
      return MAPPER.readValue(new File(filename), clazz);
    } catch (final IOException ioException) {
      LOG.error("Error reading json file '{}' and mapping it to '{}'. The Exception was: ",
          filename,
          clazz.getSimpleName(),
          ioException);
      throw new RuntimeException(ioException);
    }
  }

  /**
   * This function takes an item and creates a file where the content of this item is stored as JSON.
   *
   * @param item the item which should be serialized to JSON
   * @param file the file which will be created (with the content of the item)
   * @param <T>  the type of the item
   */
  public static <T> void writeTo(final T item, final File file) {
    assert item != null : "item which should be serialized to JSON was null;";
    try {
      MAPPER.writerWithDefaultPrettyPrinter().writeValue(file, item);
    } catch (final IOException ioException) {
      LOG.error("Error writing json file '{}'. The exception was: ", file.getAbsolutePath());
      throw new RuntimeException(ioException);
    }
  }
}
