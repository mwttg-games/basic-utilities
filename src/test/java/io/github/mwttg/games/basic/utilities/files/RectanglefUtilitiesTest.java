package io.github.mwttg.games.basic.utilities.files;

import static org.assertj.core.api.Assertions.assertThat;

import org.joml.primitives.Rectanglef;
import org.testng.annotations.Test;

public class RectanglefUtilitiesTest {

  @Test
  public void testDeserializeKey_withCommaSeparation() {
    final var input = "( 2,000E+0  3,000E+0) < ( 4,000E+0  5,000E+1)";
    final var actual = RectanglefUtilities.parse(input);
    assertThat(actual).isEqualTo(expected());
  }

  @Test
  public void testDeserializeKey_withPointSeparation() {
    final var input = "( 2.000E+0  3.000E+0) < ( 4.000E+0  5.000E+1)";
    final var actual = RectanglefUtilities.parse(input);
    assertThat(actual).isEqualTo(expected());
  }

  @Test(expectedExceptions = NumberFormatException.class)
  public void testDeserializeKey_withParseError() {
    final var input = "( 2.000E+0  x) < ( 4.000E+0  5.000E+1)";
    RectanglefUtilities.parse(input);
  }

  private Rectanglef expected() {
    return new Rectanglef(2, 3, 4, 50);
  }
}