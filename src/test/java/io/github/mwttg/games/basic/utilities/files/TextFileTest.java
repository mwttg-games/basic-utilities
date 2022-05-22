package io.github.mwttg.games.basic.utilities.files;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class TextFileTest {

  @Test
  public void testReadFrom_valid() {
    final var filename = TextFileTest.class.getResource("/files/valid-textfile.txt").getFile();
    final var actual = TextFile.readFrom(filename);
    assertThat(actual).containsExactly("1. line", "2. line", "3. line");
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testReadFrom_filenameIsNull() {
    TextFile.readFrom(null);
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testReadFrom_fileDoesNotExist() {
    TextFile.readFrom("file/does/not/exist.txt");
  }

  @Test
  public void testReadFromResource_valid() {
    final var actual = TextFile.readFromResource("/files/valid-textfile.txt");
    assertThat(actual).containsExactly("1. line", "2. line", "3. line");
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testReadFromResource_filenameIsNull() {
    TextFile.readFromResource(null);
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testReadFromResource_fileDoesNotExist() {
    TextFile.readFromResource("/file/does/not/exist.txt");
  }

}