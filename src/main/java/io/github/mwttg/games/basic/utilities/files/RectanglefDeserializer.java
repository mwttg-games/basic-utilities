package io.github.mwttg.games.basic.utilities.files;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import org.joml.primitives.Rectanglef;

public class RectanglefDeserializer extends StdDeserializer<Rectanglef> {

  public RectanglefDeserializer() {
    this(null);
  }

  protected RectanglefDeserializer(final Class<?> vc) {
    super(vc);
  }

  // Well... if we are using a custom deserializer for a Rectanglef in a Map as Key, we also can use
  // this representation for a value (instead the standard default ("minX" : .. , "minY" .. , ....)
  @Override
  public Rectanglef deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
    final var node = (JsonNode) parser.getCodec().readTree(parser);

    // looks like it makes a difference if the rectangle is part of a Map (as value)
    // or if it is a normal object
    if (node.textValue() != null) {
      return RectanglefUtilities.parse(node.textValue());
    }

    final var text = node.get("zone").textValue();
    return RectanglefUtilities.parse(text);
  }
}
