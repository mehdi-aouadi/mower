package com.blablacar.utils;

import com.blablacar.base.Cell;
import com.blablacar.base.Lawn;
import com.blablacar.base.Orientation;
import com.blablacar.base.Position;
import com.blablacar.mower.Mower;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(JUnitParamsRunner.class)
public class GridPrinterTest {

  private static Lawn lawn;
  private static List<Mower> mowers;

  private static Logger logger = LoggerFactory.getLogger(GridPrinterTest.class);
	
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    mowers = new ArrayList<Mower>();
    Mower mower = new Mower(UUID.randomUUID(), new Cell(new Position(3, 5)), Orientation.N);
    mowers.add(mower);
    mower = new Mower(UUID.randomUUID(), new Cell(new Position(1, 2)), Orientation.S);
    mowers.add(mower);
  }

  @Test
  @Parameters(value = {"5, 5", "11, 5", "5, 11", "11, 11", "101, 101"})
  public void gridPrinterTest(final int width, final int height) {
    lawn = new Lawn(width, height);
    logger.info(GridPrinter.draw(lawn, mowers.toArray(new Mower[] {})));
  }

}
