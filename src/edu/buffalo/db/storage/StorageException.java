package edu.buffalo.db.storage;

/**
 * An thrown if something goes wrong in the query engine
 * 
 * @author Denis Mindolin
 */
public class StorageException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Initializes an instanceof of the exception
	 * 
	 * @param message
	 *            message describing the exception
	 */
	public StorageException(String message) {
		super(message);
	}
}