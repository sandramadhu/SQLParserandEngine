package edu.buffalo.db.parser.sqlexpr;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import edu.buffalo.db.parser.ParseException;

/**
 * @author Michalis Petropoulos
 * 
 *         Represents an SQL query expression.
 * 
 */
public class SQLExpr {

	/**
	 * The list of <code>ProjectionAtom</code> instances in the SELECT clause.
	 */
	private List<ProjectionAtom> projectionAtoms;

	/**
	 * The single <code>TableAtom</code> in the FROM clause.
	 */
	private TableAtom tableAtom;

	public SQLExpr() {
		projectionAtoms = new Vector<ProjectionAtom>();
	}

	/**
	 * Adds a table atom to this SQLExpr.
	 * 
	 * @param tAtom
	 *            the table atom to add
	 */
	public void addTableAtom(TableAtom tAtom) throws ParseException {
		tableAtom = tAtom;
	}

	/**
	 * Adds a <code>ProjectionAtom</code>
	 * 
	 * @param pAtom
	 *            the <code>ProjectionAtom</code> to add
	 */
	public void addProjectionAtom(ProjectionAtom pAtom) {
		projectionAtoms.add(pAtom);
	}

	public TableAtom getTableAtom() {
		return tableAtom;
	}

	public List<ProjectionAtom> getProjectionAtoms() {
		return Collections.unmodifiableList(projectionAtoms);
	}

	public String toString() {

		String retString = "SELECT ";
		for (ProjectionAtom atom : getProjectionAtoms())
			retString += atom.toString() + ", ";
		// remove the last comma
		retString = retString.substring(0, retString.length() - 2);

		retString += "\nFROM ";
		retString += tableAtom.toString();

		return retString + "\n";
	}

}