package edu.buffalo.db.physical;

/**
 * @author Michalis Petropoulos
 * 
 * Implementation of <strong>Exception</strong> that handles all exceptions
 * thrown by the <code>ContainmentTester</code>.
 * 
 */
public class PhysicalPlanException extends RuntimeException {

	private static final long serialVersionUID = -6978824926739678135L;

	/**
	 * Default constructor
	 */
	public PhysicalPlanException() {
		super();
	}

	/**
	 * Constructor for a given message
	 * 
	 * @param message
	 *            the message to include in the exception
	 */
	public PhysicalPlanException(String message) {
		super(message);
	}

}
