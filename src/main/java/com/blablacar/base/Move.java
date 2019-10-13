package com.blablacar.base;

import com.blablacar.mower.Mower;

/**
 * Represents an enumeration with the possible moves of a mower :
 * D (Turn Right), G (Turn Left) and A (Move Forward)
 *
 * @author Mehdi AOUADI
 */
public enum Move {
  R {
    @Override
    public void move(Mower current) {
      current.turnRight();
    }
  },

  L {
    @Override
    public void move(Mower current) {
      current.turnLeft();
    }
  },

  F {
    @Override
    public void move(Mower current) {
      current.moveForward();
    }
  };

  /**
   * Execute the mower move
   *
   * @param current The current {@link Mower}
   *
   */
  public abstract void move(Mower current);
}
