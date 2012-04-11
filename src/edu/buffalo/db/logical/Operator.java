package edu.buffalo.db.logical;

import java.util.List;

import edu.buffalo.db.storage.catalog.IntermediateDef;

/**
 * @author Denis Mindolin
 * 
 * Represents a logical operator interface
 */

public abstract class Operator {

	/**
	 * The output schema of the operator
	 * 
	 */
	protected IntermediateDef outputSchema;
	
	/**
	 * Constructs an instance of the operator
	 */
	protected Operator() {
		this.outputSchema = new IntermediateDef();
	}

	/**
	 * Returns the output schema collection
	 * 
	 * @return the output schema collection
	 */
	public IntermediateDef getOutputSchema() {
		return outputSchema;
	}
	
	/**
	 * Converts the operator's parameters to a string
	 * 
	 * @return the string representation of the operator parameters
	 */
	public abstract String paramsToString();
	
	/**
	 * Gets the child operator collection
	 * 
	 * @return the child operator collection
	 */
	public abstract List<Operator> getChildOperators();

	/**
	 * Gets the parent operator for the current operator
	 * 
	 * @return the parent operator
	 */
	public abstract Operator getParent();
}