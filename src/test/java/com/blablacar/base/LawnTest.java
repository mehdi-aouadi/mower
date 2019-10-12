package com.blablacar.base;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(JUnitParamsRunner.class)
public class LawnTest {

  @Test
  @Parameters( {"5,5", "1,1", "3,6"})
  public void isValidLawnTest(int width, int height) {
    checkLawn(width, height);
  }

  @Test(expected = IllegalArgumentException.class)
  @Parameters( {"0,0", "0,3", "2,0", "-1,2", "3,-5", "-3,0", "0,-6"})
  public void isNotValidValidLawnTest(int width, int height) {
    checkLawn(width, height);
  }

  private void checkLawn(int width, int height) {
    Lawn lawn = new Lawn(width, height);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Cell cell = lawn.cellAt(x, y);
        Position position = new Position(x, y);
        assertFalse(cell.isMowed());
        assertFalse(cell.isLocked());
        assertEquals(cell.getPosition(), position);
        checkNeighbors(lawn, cell.getPosition(), Orientation.E, new Position(x + 1, y));
        checkNeighbors(lawn, cell.getPosition(), Orientation.S, new Position(x, y - 1));
        checkNeighbors(lawn, cell.getPosition(), Orientation.W, new Position(x - 1, y));
        checkNeighbors(lawn, cell.getPosition(), Orientation.N, new Position(x, y + 1));

      }
    }
  }

  private void checkNeighbors(Lawn lawn, Position position, Orientation orientation,
                              Position expectedPosition) {
    Cell expectedCell = null;
    Cell cell = lawn.cellAt(position.getColumn(), position.getRow());
    Cell nextCell = cell.nextCell(orientation);

    if (expectedPosition.getColumn() < lawn.getWidth() && expectedPosition.getColumn() >= 0
        && expectedPosition.getRow() < lawn.getHeight() && expectedPosition.getRow() >= 0) {
      expectedCell = lawn.cellAt(expectedPosition.getColumn(), expectedPosition.getRow());
    }

    assertEquals(expectedCell, nextCell);

  }

}
