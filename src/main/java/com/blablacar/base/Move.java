package com.blablacar.base;

import com.blablacar.mower.Mower;

/**
 * Represents an enumeration with the possible moves of a mower :
 * D (Turn Right), G (Turn Left) and A (Move Forward)
 *
 * @author Mehdi AOUADI
 */
public enum Move {
  D {
    @Override
    public void move(Mower current) {
      current.turnRight();
    }
  },

  G {
    @Override
    public void move(Mower current) {
      current.turnLeft();
    }
  },

  A {
    @Override
    public void move(Mower current) {
      current.moveForward();
    }
  };

  /**
   * Execute the mower move
   *
   * @param current
   * @return
   */
  public abstract void move(Mower current);
}
