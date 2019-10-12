package com.blablacar.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a position based on X and Y axis
 *
 * @author Mehdi AOUADI
 */
@ToString
@EqualsAndHashCode
@Getter
public class Position {

  /**
   * Horizontal index
   */
  private final int column;
  /**
   * Vertical index
   */
  private final int row;

  /**
   * Position constructor
   *
   * @param column index
   * @param row    index
   */
  public Position(int column, int row) {
    super();
    this.column = column;
    this.row = row;
  }

}
