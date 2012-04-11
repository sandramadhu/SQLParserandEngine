package edu.buffalo.db.storage;

/**
 * @author Michalis Petropoulos
 * 
 * This class represents a data value.
 */
public class Value {

	private String valStr;

	/**
	 * Constructor that sets the this <code>Value</code> to the input value
	 * 
	 * @param str
	 *            the input value
	 */
	public Value(String str) {
		valStr = str;
	}

	/**
	 * Gets the value
	 * 
	 * @return a string value
	 */
	public String getValue() {
		return valStr;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return valStr;
	}

}
