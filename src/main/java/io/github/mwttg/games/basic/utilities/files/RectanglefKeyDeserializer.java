package io.github.mwttg.games.basic.utilities.files;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.joml.primitives.Rectanglef;

public class RectanglefKeyDeserializer extends KeyDeserializer {

  // Well ....
  // JOML primitives Rectanglef is serialized (as a json map key) by using its #toString method
  // A representation as String would look like: ( 2,000E+0  3,000E+0) < ( 4,000E+0  5,000E+0)
  // Unfortunately Jackson can't deserialize this map key out-of-the-box
  // That's why this KeyDeserializer is used
  //
  // I'm not sure why number delimiter is changed from '.' to ',' during serialization :/
  @Override
  public Object deserializeKey(final String key, final DeserializationContext ctxt) {
    final var items = key.split(" ");
    final var minX = Float.parseFloat(items[1].trim().replace(",", "."));
    final var minY = Float.parseFloat(items[3].substring(0, items[3].length() - 1).trim().replace(",", "."));
    final var maxX = Float.parseFloat(items[6].trim().replace(",", "."));
    final var maxY = Float.parseFloat(items[8].substring(0, items[8].length() - 1).trim().replace(",", "."));
    return new Rectanglef(minX, minY, maxX, maxY);
  }
}