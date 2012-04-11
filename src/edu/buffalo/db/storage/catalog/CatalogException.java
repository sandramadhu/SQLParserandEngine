package edu.buffalo.db.storage.catalog;

/**
 * @author Michalis Petropoulos
 * 
 * Implementation of <strong>Exception</strong> that handles all exceptions
 * thrown by the <code>SchemaReader</code>.
 * 
 */
public class CatalogException extends Exception {

	private static final long serialVersionUID = 2781401590228899850L;

	/**
	 * Default constructor
	 */
	public CatalogException() {
		super();
	}

	/**
	 * Constructor for a given message
	 * 
	 * @param message
	 *            the message to include in the exception
	 */
	public CatalogException(String message) {
		super(message);
	}

}
