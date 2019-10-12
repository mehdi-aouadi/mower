package com.blablacar.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a cell of the lawn's grid
 *
 * @author Mehdi AOUADI
 */
public class Cell {

  /**
   * The position of the cell
   */
  @Getter
  private final Position position;
  /**
   * The cell's neighbors (Cell/Orientation)
   */
  private final Map<Orientation, Cell> neighbors = new HashMap<>
      (Orientation.values().length);
  /**
   * TRUE when the cell is mowed
   */
  private boolean mowed;
  /**
   * TRUE if the cell contains a mower
   */
  @Getter
  private boolean locked;

  /**
   * Cell constructor
   *
   * @param position, an instance of {@link Position}
   */
  public Cell(Position position) {
    super();
    this.position = position;
    this.mowed = false;
    this.locked = false;
  }

  /**
   * Tests if the Cell is mowed
   *
   * @return true if the cell is already mowed
   */
  public boolean isMowed() {
    return mowed;
  }

  /**
   * Mow the cell : Set mowed as TRUE
   */
  public void mow() {
    this.mowed = true;
  }

  /**
   * Lock the cell when a mower occupy it
   */
  public void lock() {
    this.locked = true;
  }

  /**
   * Unlock the cell when a mower leaves it
   */
  public void unLock() {
    this.locked = false;
  }

  /**
   * Put the next neighbor Cell by Orientation
   *
   * @param orientation
   * @param cell
   */
  public void nextCell(final Orientation orientation, final Cell cell) {
    this.neighbors.put(orientation, cell);
  }

  /**
   * Provides the next Cell
   *
   * @param orientation
   * @return the neighbor Cell on Orientation
   */
  public Cell nextCell(Orientation orientation) {
    return neighbors.get(orientation);
  }

}
