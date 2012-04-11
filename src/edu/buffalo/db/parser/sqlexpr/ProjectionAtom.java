package edu.buffalo.db.parser.sqlexpr;

/**
 * @author Michalis Petropoulos
 * 
 * Represents a projection atom in the SELECT clause of an SQL query expression.
 * 
 */

public class ProjectionAtom extends Atom {

	private ColumnAtom columnAtom;

	/**
	 * Default constructor
	 * 
	 * @param cAtom
	 *            the <code>ColumnAtom</code> associated with this
	 *            <code>ProjectionAtom</code>
	 */
	public ProjectionAtom(ColumnAtom cAtom) {
		columnAtom = cAtom;
	}

	/**
	 * Gets the <code>ColumnAtom</code> associated with this
	 * <code>ProjectionAtom</code>
	 * 
	 * @return a <code>ColumnAtom</code> instance
	 */
	public ColumnAtom getColumnAtom() {
		return columnAtom;
	}

	/**
	 * @see edu.buffalo.db.parser.sqlexpr.Atom#getType()
	 */
	@Override
	public int getType() {
		return Atom.PROJECTION_ATOM;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result
				+ ((columnAtom == null) ? 0 : columnAtom.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		final ProjectionAtom other = (ProjectionAtom) obj;
		if (columnAtom == null) {
			if (other.columnAtom != null)
				return false;
		} else if (!columnAtom.equals(other.columnAtom))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return columnAtom.toString();
	}

}