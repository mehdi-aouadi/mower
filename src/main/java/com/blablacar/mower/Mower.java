package com.blablacar.mower;

import com.blablacar.base.Cell;
import com.blablacar.base.Move;
import com.blablacar.base.Orientation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents a Mower
 *
 * @author Mehdi AOUADI
 */
@ToString
@EqualsAndHashCode
@Getter
public class Mower extends Observable {

  private static Logger logger = LoggerFactory.getLogger(Mower.class);

  /**
   * The mower identifier (Unique)
   */
  private final UUID id;
  /**
   * The mower cell
   */
  private Cell cell;
  /**
   * The mower orientation
   */
  private Orientation orientation;
  /**
   * A queue of instruction to be executed by the mower
   */
  private Queue<Move> moves;

  /**
   * Mower constructor
   *
   * @param id          a unique mower {@link UUID}
   * @param cell        the mower initial {@link Cell}
   * @param orientation the mower {@link Orientation}
   */
  public Mower(UUID id, Cell cell, Orientation orientation, Move... moves) {
    super();
    checkArgument(!cell.isLocked(),
        "Error when creating a new Mower at position X:" +
            cell.getPosition().getColumn() +
            " Y:" +
            cell.getPosition().getRow() +
            ". This cell already contains a mower"
    );
    this.id = id;
    this.orientation = orientation;
    this.cell = cell;
    this.moves = new LinkedList<>(Arrays.asList(moves));

    //The initial position of the mower is locked
    this.cell.lock();
  }

  /**
   * Make the mower turns right
   *
   * @return the current {@link Mower}
   */
  public Mower turnRight() {

    switch (this.orientation) {
      case N:
        this.orientation = Orientation.E;
        break;
      case E:
        this.orientation = Orientation.S;
        break;
      case S:
        this.orientation = Orientation.W;
        break;
      case W:
        this.orientation = Orientation.N;
        break;
      default:
        throw new IllegalArgumentException("Invalid direction");
    }

    changeAndNotify();
    return this;
  }

  /**
   * Make the mower turns left
   *
   * @return the current {@link Mower}
   */
  public Mower turnLeft() {

    switch (this.orientation) {
      case N:
        this.orientation = Orientation.W;
        break;
      case W:
        this.orientation = Orientation.S;
        break;
      case S:
        this.orientation = Orientation.E;
        break;
      case E:
        this.orientation = Orientation.N;
        break;
      default:
        throw new IllegalArgumentException("Invalid direction");
    }

    changeAndNotify();
    return this;
  }

  /**
   * Make the mower move forward
   */
  public void moveForward() {
    Cell nextCell = this.cell.nextCell(this.orientation);
    if (null != nextCell && !nextCell.isLocked()) {
      this.cell.unLock();
      this.cell = nextCell;
      this.cell.lock();
      mow();
      changeAndNotify();
    }
  }

  /**
   * Mows the current Cell
   */
  public void mow() {
    this.cell.mow();
  }

  /**
   * Launh the mower : Execute all the instructions from the queue
   */
  public void start() {
    logger.debug("The mower {} is coming, brace yourself ", this.id);
    mow();
    int index = 1;
    if (moves != null && !moves.isEmpty()) {
      while (!moves.isEmpty()) {
        Move move = moves.poll();
        logger.debug("Performing the instruction N° {} {} .", index++, move);
        move.move(this);
      }
    } else {
      logger.warn("The mower {} doesn't have any instruction", this.toString());
    }
    logger.debug("Stopping the mower {}", this.toString());
  }

  /**
   * Notify the observer (The monitor) for any change
   */
  private void changeAndNotify() {
    setChanged();
    notifyObservers();
  }
}
