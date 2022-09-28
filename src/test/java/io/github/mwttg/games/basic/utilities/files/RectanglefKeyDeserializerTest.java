package io.github.mwttg.games.basic.utilities.files;

import static org.assertj.core.api.Assertions.assertThat;

import org.joml.primitives.Rectanglef;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RectanglefKeyDeserializerTest {

  private RectanglefKeyDeserializer subject;

  @BeforeMethod
  public void setup() {
    subject = new RectanglefKeyDeserializer();
  }

  @Test
  public void testDeserializeKey_withCommaSeparation() {
    final var input = "( 2,000E+0  3,000E+0) < ( 4,000E+0  5,000E+1)";
    final var actual = (Rectanglef) subject.deserializeKey(input, null);
    assertThat(actual).isEqualTo(expected());
  }

  private Rectanglef expected() {
    return new Rectanglef(2, 3, 4, 50);
  }
}