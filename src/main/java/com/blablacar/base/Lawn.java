package com.blablacar.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents a Lawn
 *
 * @author Mehdi AOUADI
 */

@ToString
@EqualsAndHashCode
public class Lawn {

  private static Logger logger = LoggerFactory.getLogger(Lawn.class);
  /**
   * The Lawn width (East limit)
   */
  @Getter
  private final int width;
  /**
   * The Lawn height (North limit)
   */
  @Getter
  private final int height;
  /**
   * A grid of cells representing the lawn surface
   */
  private final Cell[][] grid;

  /**
   * Lawn constructor
   *
   * @param width
   * @param heitgh
   */
  public Lawn(int width, int heitgh) {
    super();
    checkArgument(width > 0 && heitgh > 0, "Width and Height of the Lawn must be > 0");
    this.width = width;
    this.height = heitgh;
    this.grid = new Cell[this.width][this.height];

    for (int x = 0; x < this.width; x++) {
      for (int y = 0; y < this.height; y++) {
        grid[x][y] = new Cell(new Position(x, y));
      }
    }

    //Adding the cells neighbors
    for (int x = 0; x < this.width; x++) {
      for (int y = 0; y < this.height; y++) {

        if (x > 0) {
          cellAt(x, y).nextCell(Orientation.W, cellAt(x - 1, y));
        }

        if (x < width - 1) {
          cellAt(x, y).nextCell(Orientation.E, cellAt(x + 1, y));
        }

        if (y > 0) {
          cellAt(x, y).nextCell(Orientation.S, cellAt(x, y - 1));
        }

        if (y < height - 1) {
          cellAt(x, y).nextCell(Orientation.N, cellAt(x, y + 1));
        }
      }
    }

    logger.debug("A lawn has been initialized : North limit is {} and East limit is {} ", this.height, this.width);
  }

  /**
   * Provides a Cell according to X:Y indexes
   *
   * @param x index
   * @param y index
   * @return the cell located at X:Y
   */
  public Cell cellAt(int x, int y) {
    checkArgument(contains(x, y), "The position X:" + x + ", Y:" + y
        + " is out of the Lawn");
    return grid[x][y];
  }

  /**
   * Check the position x:y is within the Lawn
   *
   * @param x
   * @param y
   * @return TRUE if the x:y is within the Lawn
   */
  private boolean contains(int x, int y) {
    return (x < width && x >= 0) && (y < height && y >= 0);
  }
}
