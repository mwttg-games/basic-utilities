package io.github.mwttg.games.basic.utilities.files;

import org.joml.primitives.Rectanglef;

public final class RectanglefUtilities {

  private RectanglefUtilities() {
  }

  static Rectanglef parse(final String text) {
    final var items = text.split(" ");
    final var minX = Float.parseFloat(items[1].trim().replace(",", "."));
    final var minY = Float.parseFloat(items[3].substring(0, items[3].length() - 1).trim().replace(",", "."));
    final var maxX = Float.parseFloat(items[6].trim().replace(",", "."));
    final var maxY = Float.parseFloat(items[8].substring(0, items[8].length() - 1).trim().replace(",", "."));
    return new Rectanglef(minX, minY, maxX, maxY);
  }
}
