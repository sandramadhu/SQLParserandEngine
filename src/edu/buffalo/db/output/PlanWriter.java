package edu.buffalo.db.output;

import java.io.PrintStream;

import edu.buffalo.db.logical.Operator;
import edu.buffalo.db.physical.OperatorImpl;

public abstract class PlanWriter {
	/**
	 * The output where plans are written
	 */
	protected PrintStream out;

	public PlanWriter(PrintStream out) {
		this.out = out;
	}

	/**
	 * Prints a logical plan to the output
	 * 
	 * @param plan
	 *            the root of the logical plan
	 */
	public abstract void printPlan(Operator plan) throws OutputException;

	/**
	 * Prints a physical plan to the output
	 * 
	 * @param rootOperator
	 *            the root operator of the physical plan
	 */
	public abstract void printPlan(OperatorImpl rootOperator)
			throws OutputException;
}
