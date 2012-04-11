package edu.buffalo.db.parser.sqlexpr;

/**
 * @author Michalis Petropoulos
 * 
 * Implementation of <strong>Exception</strong> that handles all schema
 * validation exceptions thrown by an <code>Atom</code>.
 * 
 */
public class SQLExprException extends Exception {

	private static final long serialVersionUID = -1042850631273296781L;

	/**
	 * Default constructor
	 * 
	 */
	public SQLExprException() {
		super();
	}

	/**
	 * Constructor for a given message
	 * 
	 * @param message
	 *            the message to include in the exception
	 */
	public SQLExprException(String message) {
		super(message);
	}

}
