package com.blablacar.utils;

import com.blablacar.base.Lawn;
import com.blablacar.base.Position;
import com.blablacar.mower.Mower;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Represents a Grid drawer. It draws a {@link Lawn} and its status
 * (The mowers it contains and the mowed/not yet mowed cells)
 *
 * @author Mehdi AOUADI
 */
public final class GridPrinter {

  private static final String HORIZONTAL_SEPARATOR = "-";
  private static final String VERTICAL_SEPARATOR = "|";
  private static Logger logger = LoggerFactory.getLogger(GridPrinter.class);

  /**
   * Returns a String that represents the Lawn state
   *
   * @param lawn   the {@link Lawn} to draw
   * @param mowers the list of {@link Mower} to draw within the lawn
   * @return a {@link String} representation of the lawn
   */
  public static String draw(Lawn lawn, List<Mower> mowers) {

    int width = lawn.getWidth();
    int height = lawn.getHeight();
    boolean containsMowers = !mowers.isEmpty();

    //The number of digits of the column number in order to add spaces
    int rowIndexMaxDigitNumber = String.valueOf(height - 1).length();

    //The number of digits of the row numbers in order to add print it vertically
    int columnIndexMaxDigitNumber = String.valueOf(width - 1).length();

    StringBuilder gridToPrint = new StringBuilder();
    gridToPrint.append("\n");

    //In order to print the Lawn with the 0:0 at the left down position, we need to start printing from 0:Y the left up.
    for (int rowIndex = height - 1; rowIndex >= 0; rowIndex--) {
      //Print vertical index
      gridToPrint.append(verticalIndexCell(rowIndex, rowIndexMaxDigitNumber));
      for (int columnIndex = 0; columnIndex < width; columnIndex++) {
        //Must check that a Mower is present within the cell. Otherwise the Iterables.find below will throw an Exception
        if (lawn.cellAt(columnIndex, rowIndex).isLocked()) {
          gridToPrint.append(
              containsMowers ?
                  cell(getMowerOrientation(new Position(columnIndex, rowIndex), mowers).charAt(0))
                  : cell(' ')
          );
        } else if (lawn.cellAt(columnIndex, rowIndex).isMowed()) {
          gridToPrint.append(cell('X'));
        } else {
          gridToPrint.append(cell(' '));
        }
      }
      gridToPrint.append(addColumnSeparator(width, rowIndexMaxDigitNumber));
    }

    //Print horizontal indexes
    gridToPrint.append(addColumnIndexes(width, columnIndexMaxDigitNumber, rowIndexMaxDigitNumber));
    return gridToPrint.toString();
  }

  /**
   * Returns a formatted Cell from a char
   *
   * @param c the character ro be drawn within the cell
   * @return a {@link String} representation of cell containing the character
   */
  private static String cell(char c) {
    return new StringBuilder()
        .append(" ")
        .append(c)
        .append(" ")
        .append(VERTICAL_SEPARATOR)
        .toString();
  }

  /**
   * Returns a formatted Cell from an vertical index
   *
   * @param rowIndex the index of the row
   * @return a {@link String} representation of a row index
   */
  private static String verticalIndexCell(int rowIndex, int yDigits) {
    int nbDigits = String.valueOf(rowIndex).length();
    return new StringBuilder()
        .append(" ")
        .append(rowIndex)
        .append(Strings.repeat(" ", yDigits - nbDigits + 1))
        .append(VERTICAL_SEPARATOR)
        .toString();
  }

  /**
   * Adds the horizontal indexes of the lawn
   *
   * @param columnNumber the number of column
   * @return a {@link String} representation of the column indexes
   */
  private static String addColumnIndexes(
      int columnNumber,
      int xMaxDigitsNumber,
      int yMaxDigitsNumber
  ) {

    StringBuilder horizontalIndexes = new StringBuilder();

    for (int lineNumber = 0; lineNumber < xMaxDigitsNumber; lineNumber++) {

      //Add spaces according to Y digits numbers to align the horizontal indexes
      horizontalIndexes.append(Strings.repeat(" ", yMaxDigitsNumber + 3));

      for (int columnIndex = 0; columnIndex < columnNumber; columnIndex++) {

        if (String.valueOf(columnIndex).length() > lineNumber) {
          horizontalIndexes.append(cell(String.valueOf(columnIndex).toCharArray()[lineNumber]));
        } else {
          horizontalIndexes.append(cell(' '));
        }
      }
      //New line to print the next digits
      horizontalIndexes.append("\n");
    }

    return horizontalIndexes.toString();
  }

  /**
   * Adds an horizontal line separator
   *
   * @param width the {@link Lawn} width
   * @return a {@link String} representation of a column separator
   */
  private static String addColumnSeparator(int width, int rowIndexDigitNumber) {

    StringBuilder horizontalSeparator = new StringBuilder();
    horizontalSeparator.append("\n")
        .append(Strings.repeat(
            HORIZONTAL_SEPARATOR,
            (width + 1) * 4 + rowIndexDigitNumber)
        )
        .append("\n");
    return horizontalSeparator.toString();
  }

  /**
   * Retrieve the mower orientation as a String
   *
   * @param position the position of the {@link Mower}
   * @param mowers   the list of the present mowers
   * @return a {@link String} representation of the mower orientation
   */
  private static String getMowerOrientation(Position position, List<Mower> mowers) {
    Optional<Mower> mower =
        mowers.stream().filter(e -> e.getCell().getPosition().equals(position)).findFirst();
    if (mower.isPresent()) {
      return mower.get().getOrientation().toString();
    } else {
      logger.warn("No mower found at position {} in mower list {}", position, mowers);
      return "";
    }
  }

}

