package com.blablacar.mower;

import com.blablacar.base.Cell;
import com.blablacar.base.Lawn;
import com.blablacar.base.Move;
import com.blablacar.base.Orientation;
import com.blablacar.mower.MowerTest.Data.DataBuilder;
import com.blablacar.utils.GridPrinter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class MowerTest {

  private static Lawn lawn;
  private static Logger logger = LoggerFactory.getLogger(MowerTest.class);
  private Orientation orientation;
  private Mower mower;

  @Before
  public void setUp() {
    lawn = new Lawn(5, 5);
    orientation = Orientation.N;
  }

  @Test
  @Parameters( {"N", "E", "S", "W"})
  public void mowerTurningRightTest(Orientation orientation) {
    mower = new Mower(UUID.randomUUID(), lawn.cellAt(3, 3), orientation);
    checkTurningRight(mower);
  }

  @Test
  @Parameters( {"N", "E", "S", "W"})
  public void mowerTurningLeftTest(Orientation orientation) {
    mower = new Mower(UUID.randomUUID(), lawn.cellAt(3, 3), orientation);
    checkTurningLeft(mower);
  }

  @Test(expected = IllegalArgumentException.class)
  public void mowerInitialCellLockedTest() {
    lawn.cellAt(3, 3).lock();
    mower = new Mower(UUID.randomUUID(), lawn.cellAt(3, 3), orientation);
    checkTurningRight(mower);
  }

  @Test
  public void mowerNextCellLocked() {
    lawn.cellAt(3, 3).nextCell(orientation).lock();
    mower = new Mower(UUID.randomUUID(), lawn.cellAt(3, 3), orientation);
    mower.moveForward();
    assertEquals(mower.getCell().getPosition(), lawn.cellAt(3, 3).getPosition());
    assertEquals(mower.getOrientation(), orientation);
  }

  @Test
  @Parameters( {"2,4,N", "4,2,E", "2,0,S", "0,2,W"})
  public void mowerNextCellOutOfTheLawn(int x, int y, Orientation orientation) {
    mower = new Mower(UUID.randomUUID(), lawn.cellAt(x, y), orientation);
    mower.moveForward();
    assertEquals(mower.getCell().getPosition(), lawn.cellAt(x, y).getPosition());
    assertEquals(mower.getOrientation(), orientation);
  }

  @Test
  @Parameters
  public void startTest(Data data) {
    data.mowers.start();
    assertEquals(data.expectedOrientation, data.mowers.getOrientation());
    assertEquals(data.expectedCell, data.mowers.getCell());
    assertEquals(data.expectedCell, data.mowers.getCell());
  }

  public Object[][] parametersForStartTest() {
    return new Object[][] {
        {DataBuilder.lawn(2, 2).mower(0, 0, Orientation.E).expected(0, 0, Orientation.E)},
        {DataBuilder.lawn(1, 1).mower(0, 0, Orientation.E, Move.R).expected(0, 0, Orientation.S)},
        {DataBuilder.lawn(1, 1).mower(0, 0, Orientation.E, Move.L).expected(0, 0, Orientation.N)},
        {DataBuilder.lawn(2, 2).mower(0, 0, Orientation.W, Move.L, Move.L, Move.L, Move.L).expected(0, 0,
            Orientation.W)},
        {DataBuilder.lawn(5, 5).mower(2, 2, Orientation.W, Move.R, Move.F, Move.F, Move.L, Move.F, Move.F, Move.L, Move.F, Move.L, Move.F)
            .expected(1, 3, Orientation.E)}};
  }

  private void checkTurningRight(Mower mower) {
    Orientation expectedOrientation;

    switch (mower.getOrientation()) {
      case N:
        expectedOrientation = Orientation.E;
        break;
      case E:
        expectedOrientation = Orientation.S;
        break;
      case S:
        expectedOrientation = Orientation.W;
        break;
      case W:
        expectedOrientation = Orientation.N;
        break;
      default:
        throw new IllegalArgumentException("Invalid direction given");
    }

    mower.turnRight();
    assertEquals(expectedOrientation, mower.getOrientation());
  }

  private void checkTurningLeft(Mower mower) {
    Orientation expectedOrientatinon;

    switch (mower.getOrientation()) {
      case N:
        expectedOrientatinon = Orientation.W;
        break;
      case E:
        expectedOrientatinon = Orientation.N;
        break;
      case S:
        expectedOrientatinon = Orientation.E;
        break;
      case W:
        expectedOrientatinon = Orientation.S;
        break;
      default:
        throw new IllegalArgumentException("Invalid direction given");
    }

    mower.turnLeft();
    assertEquals(expectedOrientatinon, mower.getOrientation());
  }

  // Builder design patter : used to create data for the test (Mowers, positions, lawn ... )
  // WARNING : The initial state of the lawn just after setting the mower is
  // not logged : Cannot access non static variable from the inner static
  // class DataBuilder
  static class Data implements Observer {

    private Lawn lawn;
    private Mower mowers;
    private Cell expectedCell;
    private Orientation expectedOrientation;

    @Override
    public void update(Observable o, Object arg) {
      logger.debug("A move has been detected. Here is the Lawn state :\n {}", GridPrinter.draw(lawn, mowers));
    }

    Cell on(final int x, final int y) {
      return lawn.cellAt(x, y);
    }

    static class DataBuilder {

      final Data data = new Data();

      static DataBuilder lawn(final int width, final int height) {
        DataBuilder builder = new DataBuilder();
        builder.data.lawn = new Lawn(width, height);
        return builder;
      }

      DataBuilder mower(final int x, final int y, final Orientation o, final Move... moves) {
        data.mowers = new Mower(UUID.randomUUID(), data.on(x, y), o, moves);
        data.mowers.addObserver(data);
        return this;
      }

      Data expected(final int x, final int y, final Orientation o) {
        data.expectedCell = data.on(x, y);
        data.expectedOrientation = o;
        return data;
      }
    }

  }
}
