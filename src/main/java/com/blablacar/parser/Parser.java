package com.blablacar.parser;

/**
 * 
 * Represents a Parser that parser I to O
 * 
 * @author Mehdi AOUADI
 *
 * @param <I>
 * @param <O>
 */
public interface Parser<I, O> {
	/**
	 * Parses the source I to O
	 * @param source
	 * @return
	 */
	O parse(I source);
}
