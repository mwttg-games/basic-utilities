package io.github.mwttg.games.basic.utilities.files;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;

public class JsonFileTest {

  private final TypeReference<JsonTestObject> type = new TypeReference<>() {
  };

  @Test
  public void testReadFrom_valid_withResultClass() {
    final var filename = JsonFileTest.class.getResource("/files/valid-jsonfile.json").getFile();
    final var actual = JsonFile.readFrom(filename, JsonTestObject.class);
    assertThat(actual).isEqualTo(expected());
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testReadFrom_fileDoesNotExist_withResultClass() {
    JsonFile.readFrom("/file/does/not/exist.json", JsonTestObject.class);
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testReadFrom_filenameIsNull_withResultClass() {
    JsonFile.readFrom(null, JsonTestObject.class);
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testReadFrom_resultTypeIsNull_withResultClass() {
    JsonFile.readFrom("/file/does/not/exist.json", (Class<Object>) null);
  }

  @Test
  public void testReadFrom_valid_withResultType() {
    final var filename = JsonFileTest.class.getResource("/files/valid-jsonfile.json").getFile();
    final var actual = JsonFile.readFrom(filename, type);
    assertThat(actual).isEqualTo(expected());
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testReadFrom_fileDoesNotExist_withResultType() {
    JsonFile.readFrom("/file/does/not/exist.json", type);
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testReadFrom_filenameIsNull_withResultType() {
    JsonFile.readFrom(null, type);
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testReadFrom_resultTypeIsNull_withResultType() {
    JsonFile.readFrom("/file/does/not/exist.json", (TypeReference<Object>) null);
  }

  @Test
  public void testReadFromResource_valid() {
    final var actual = JsonFile.readFromResource("/files/valid-jsonfile.json", JsonTestObject.class);
    assertThat(actual).isEqualTo(expected());
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testReadFromResource_fileDoesNotExist() {
    JsonFile.readFromResource("/file/does/not/exist.json", JsonTestObject.class);
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testReadFromResource_filenameIsNull() {
    JsonFile.readFromResource(null, JsonTestObject.class);
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testReadFromResource_resultTypeIsNull() {
    JsonFile.readFromResource("/file/does/not/exist.json", null);
  }

  @Test
  public void testWriteTo_valid() throws IOException {
    final var tempFile = File.createTempFile("mwttg-temp-file-basic-utils", ".json");
    tempFile.deleteOnExit();

    final var item = expected();
    JsonFile.writeTo(item, tempFile);

    final var expectedFile = JsonFileTest.class.getResource("/files/valid-jsonfile.json").getFile();
    final var expected = new File(expectedFile);
    assertThat(Files.mismatch(Paths.get(expected.toURI()), Paths.get(tempFile.toURI()))).isEqualTo(-1);
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testWriteTo_itemIsNull() throws IOException {
    final var tempFile = File.createTempFile("mwttg-temp-file-basic-utils", ".json");
    tempFile.deleteOnExit();
    JsonFile.writeTo(null, tempFile);
  }

  private JsonTestObject expected() {
    return new JsonTestObject("a-string", 1234, 1.0f, true);
  }
}