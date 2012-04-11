package edu.buffalo.db.output;

import java.io.PrintStream;

import edu.buffalo.db.storage.Tuple;
import edu.buffalo.db.storage.catalog.IntermediateDef;

public abstract class QueryResultWriter {
	
	/**
	 * The output where everything is written
	 */
	protected PrintStream out;
	
	/**
	 * Constructs an instance of the class
	 * @param out
	 * 			the output where everything is going to be written
	 */
	public QueryResultWriter( PrintStream out ){
		this.out = out;
	}
	
	/**
	 * Starts a query result
	 * @param outputSchema 
	 * 		the output schema of the query
	 * @throws OutputException
	 */
	public abstract void startQueryResult( IntermediateDef outputSchema ) throws OutputException;

	/**
	 * Adds a new tuple to the query result
	 * @param tuple
	 * 		the tuple to add 
	 * @throws OutputException
	 */
	public abstract void addTuple( Tuple tuple ) throws OutputException;
	
	/**
	 * Ends the current query result
	 * @throws OutputException
	 */
	public abstract void endQueryResult() throws OutputException;
}
