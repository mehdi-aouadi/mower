package com.blablacar.parser;

/**
 * 
 * Represents a Parser that parser I to O
 * 
 * @author Mehdi AOUADI
 *
 * @param <I> The input type
 * @param <O> The output type
 */
public interface Parser<I, O> {
	/**
	 * Parses the source I to O
	 * @param source the input
	 * @return parsed input
	 */
	O parse(I source);
}
