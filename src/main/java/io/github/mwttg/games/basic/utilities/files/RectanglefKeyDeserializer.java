package io.github.mwttg.games.basic.utilities.files;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.joml.primitives.Rectanglef;

public class RectanglefKeyDeserializer extends KeyDeserializer {

  // Well ....
  // JOML primitives Rectanglef is serialized (for a json map key) by using its #toString method
  // A representation as String looks like: ( 2,000E+0  3,000E+0) < ( 4,000E+0  5,000E+0)
  // Unfortunately Jackson can't deserialize this map key out-of-the-box
  // That's why this KeyDeserializer is used
  @Override
  public Object deserializeKey(final String key, final DeserializationContext ctxt) {
    return RectanglefUtilities.parse(key);
  }
}