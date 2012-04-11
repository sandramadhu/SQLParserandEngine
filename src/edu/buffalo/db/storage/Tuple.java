package edu.buffalo.db.storage;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * @author Michalis Petropoulos
 * 
 * Represents a tuple.
 */
public class Tuple {
	/**
	 * The actual tuple repsented by a Vector.
	 */
	protected Vector<Value> tuple;

	/**
	 * Constructor.
	 * 
	 * @param size
	 *            the arity of the tuple
	 */
	public Tuple(int size) {
		tuple = new Vector<Value>(size, 1);
	}

	/**
	 * Default constructor
	 */
	public Tuple() {
		tuple = new Vector<Value>();
	}

	/**
	 * Adds a <code>String</code> value to the end of the tuple.
	 * 
	 * @param val
	 *            The value to add
	 * @return True, if succesful; false, otherwise
	 */
	public boolean addValue(Value val) {
		return tuple.add(val);
	}

	/**
	 * Gets the <code>String</code> value at the given position.
	 * 
	 * @param pos
	 *            the position of the value to get
	 * @return a <code>Value</code> instance
	 * @throws StorageException
	 *             if the given position is out of range
	 */
	public Value getValue(int pos) throws StorageException {
		if (pos < 0 || pos >= tuple.size())
			throw new StorageException("Tuple value not found: "
					+ "specified position is out of range.");

		return tuple.get(pos);
	}

	/**
	 * Gets all the <code>String</code> values of the tuple.
	 * 
	 * @return a collection of <code>Value</code> instances
	 */
	public List<Value> getValues() {
		return Collections.unmodifiableList(tuple);
	}

	/**
	 * Gets the arity of the tuple.
	 * 
	 * @return the arity of the tuple as an integer
	 */
	public int size() {
		return tuple.size();
	}

	/**
	 * Clears all values in the tuple.
	 */
	public void clear() {
		tuple.clear();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String out = "";

		for (int i = 0; i < tuple.size(); i++)
			out += tuple.get(i).toString() + ",";
		out = out.substring(0, out.length() - 1);

		return out;
	}

}
