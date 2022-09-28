package io.github.mwttg.games.basic.utilities.files;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.joml.primitives.Rectanglef;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RectanglefDeserializerTest {

  @Mock
  private JsonParser parser;

  @Mock
  private ObjectCodec codec;

  @Mock
  private JsonNode node;

  @Mock
  private JsonNode node2;

  @InjectMocks
  private RectanglefDeserializer subject;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testDeserializeKey_whenMapValue() throws IOException {
    final var text = "( 2,000E+0  3,000E+0) < ( 4,000E+0  5,000E+1)";
    Mockito.when(parser.getCodec()).thenReturn(codec);
    Mockito.when(codec.readTree(parser)).thenReturn(node);
    Mockito.when(node.get("zone")).thenReturn(node2);
    Mockito.when(node2.textValue()).thenReturn(text);

    final var actual = (Rectanglef) subject.deserialize(parser, null);
    assertThat(actual).isEqualTo(expected());
  }

  @Test
  public void testDeserializeKey_whenNormalObject() throws IOException {
    final var text = "( 2,000E+0  3,000E+0) < ( 4,000E+0  5,000E+1)";
    Mockito.when(parser.getCodec()).thenReturn(codec);
    Mockito.when(codec.readTree(parser)).thenReturn(node);
    Mockito.when(node.textValue()).thenReturn(text);

    final var actual = (Rectanglef) subject.deserialize(parser, null);
    assertThat(actual).isEqualTo(expected());
  }

  private Rectanglef expected() {
    return new Rectanglef(2, 3, 4, 50);
  }
}