package edu.buffalo.db.logical;

/**
 * @author Denis Mindolin
 * 
 * Represents a logial plan exception
 */
public class LogicalPlanException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public LogicalPlanException() {
		super();
	}

	/**
	 * Constructor for a given message
	 * 
	 * @param message
	 *            the message to include in the exception
	 */
	public LogicalPlanException(String message) {
		super(message);
	}
}
