package edu.buffalo.db.storage.catalog;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import edu.buffalo.db.parser.sqlexpr.ColumnAtom;

/**
 * @author Michalis Petropoulos
 * 
 * Represents the schema of intermediate results.
 * 
 */
public class IntermediateDef {

	private Vector<ColumnAtom> columnAtoms;

	/**
	 * Default constructor
	 * 
	 */
	public IntermediateDef() {
		columnAtoms = new Vector<ColumnAtom>();
	}

	/**
	 * Adds the input <code>ColumnAtom</code> to the schema
	 * 
	 * @param atom
	 */
	public void addColumnAtom(ColumnAtom atom) {
		columnAtoms.add(atom);
	}

	/**
	 * Gets all the <code>ColumnAtom</code> instances
	 * 
	 * @return a list of <code>ColumnAtom</code> instances
	 */
	public List<ColumnAtom> getColumnAtoms() {
		return Collections.unmodifiableList(columnAtoms);
	}

	/**
	 * Tests if the input <code>ColumnAtom</code> is part of this
	 * <code>IntermediateDef</code>
	 * 
	 * @param atom
	 *            the <code>ColumnAtom</code> to locate
	 * @return <code>true</code> if found; <code>false</code> otherwise
	 */
	public boolean hasColumnAtom(ColumnAtom atom) {
		return columnAtoms.contains(atom);
	}

	/**
	 * Gets the position of the input <code>ColumnAtom</code> within this
	 * <code>IntermediateDef</code>
	 * 
	 * @param atom
	 *            a <code>ColumnAtom</code> instance
	 * @return the position if the input <code>ColumnAtom</code> is part of
	 *         this <code>IntermediateDef</code>; -1 otherwise
	 */
	public int getPosition(ColumnAtom atom) {
		return columnAtoms.indexOf(atom);
	}

	/**
	 * The number of <code>ColumnAtom</code> instances in this
	 * <code>IntermediateDef</code>
	 * 
	 * @return the <code>ColumnAtom</code> count
	 */
	public int size() {
		return columnAtoms.size();
	}

	/**
	 * Clears the schema.
	 * 
	 */
	public void clear() {
		columnAtoms.clear();
	}

	@Override
	public String toString()
	{
		String str = "";
		
		for( ColumnAtom atom: columnAtoms ){
			str += atom.toString() + ", ";
		}
		
		return str.substring(0, str.length() - 2);
	}
}
