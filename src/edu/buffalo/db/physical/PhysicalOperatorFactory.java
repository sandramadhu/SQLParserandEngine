package edu.buffalo.db.physical;

import edu.buffalo.db.logical.Operator;

/**
 * Represents the interface of a physical operator factory 
 * @author mindolin
 *
 */
public interface PhysicalOperatorFactory {
	/**
	 * Creates a physical operator which implements the logical operator 
	 * @param logicalOperator 
	 * 		the logical operator
	 * @return a new physical operator which implements @paramlogicalOperator
	 * @throws PhysicalPlanException
	 */
	public OperatorImpl createOperator( Operator logicalOperator ) throws PhysicalPlanException;
}
