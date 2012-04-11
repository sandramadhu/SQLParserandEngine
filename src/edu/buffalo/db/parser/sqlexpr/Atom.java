package edu.buffalo.db.parser.sqlexpr;

/**
 * @author Michalis Petropoulos
 * 
 * Represents an atom in an SQL query expression.
 * 
 */

public abstract class Atom {

	/**
	 * Table atoms
	 */
	public static int TABLE_ATOM = 0;

	/**
	 * Column atoms
	 */
	public static int COLUMN_ATOM = 1;

	/**
	 * Projection atoms
	 */
	public static int PROJECTION_ATOM = 2;

	/**
	 * Constant condition atoms
	 */
	public static int CONDITION_ATOM = 3;

	/**
	 * Join condition atoms
	 */
	public static int JOIN_ATOM = 4;

	/**
	 * Gets the type of an <code>Atom</code>
	 * 
	 * @return an integer representing the type of an <code>Atom</code>
	 */
	public abstract int getType();

}
