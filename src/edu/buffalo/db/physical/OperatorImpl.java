package edu.buffalo.db.physical;

import java.util.Collections;
import java.util.List;

import edu.buffalo.db.storage.Tuple;
import edu.buffalo.db.storage.catalog.IntermediateDef;

/**
 * @author Michalis Petropoulos
 * 
 * Represents a physical implementation of an algeraic operator.
 */
public abstract class OperatorImpl {

	/**
	 * The schema of the tuples this operator outputs.
	 */
	protected IntermediateDef outSchema;

	/**
	 * Gets the schema of the tuples this operator outputs.
	 * 
	 * @return the output schema
	 * @throws PhysicalPlanException
	 *             if the newly updated output schema is <code>null</code>
	 *             (not set)
	 */
	public IntermediateDef getOutputSchema() throws PhysicalPlanException {
		return outSchema;
	}
	
	/**
	 * Returns the child operator collection
	 * 
	 * @return the child operator collection
	 */
	public List<OperatorImpl> getChildOperators() {
		return Collections.emptyList();
	}
	/**
	 * Returns the parent operator for the current operator
	 * 
	 * @return the parent operator
	 */
	public abstract OperatorImpl getParent();

	/**
	 * Converts the operator's parameters to a string
	 * 
	 * @return the string representation of the operator parameters
	 */
	public abstract String paramsToString();	
	
	/**
	 * Opens this operator. Initialization code goes here.
	 */
	public abstract void open();

	/**
	 * Gets the next <code>Tuple</code> produced by this operator.
	 * 
	 * @return a <code>Tuple</code> instance; or <code>null</code> if there
	 *         is no next <code>Tuple</code>
	 */
	public abstract Tuple getNext();

	/**
	 * Closes this operator. Cleanup code goes here.
	 */
	public abstract void close();
}
