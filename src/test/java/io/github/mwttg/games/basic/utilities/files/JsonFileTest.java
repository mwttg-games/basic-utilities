package io.github.mwttg.games.basic.utilities.files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class JsonFileTest {

  @Test
  public void testReadFrom_valid() {
    final var filename = JsonFileTest.class.getResource("/files/valid-jsonfile.json").getFile();
    final var actual = JsonFile.readFrom(filename, JsonTestObject.class);
    assertThat(actual).isEqualTo(expected());
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testReadFrom_fileDoesNotExist() {
    JsonFile.readFrom("/file/does/not/exist.json", JsonTestObject.class);
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testReadFrom_filenameIsNull() {
    JsonFile.readFrom(null, JsonTestObject.class);
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testReadFrom_resultTypeIsNull() {
    JsonFile.readFrom("/file/does/not/exist.json", null);
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

  private JsonTestObject expected() {
    return new JsonTestObject("a-string", 1234, 1.0f, true);
  }
}