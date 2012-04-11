package edu.buffalo.db.parser.sqlexpr;

/**
 * @author Michalis Petropoulos
 * 
 * Represents a column atom in an SQL query expression.
 * 
 */

public class ColumnAtom extends Atom {

	/**
	 * The table alias that qualifies the column
	 */
	private String alias = null;

	/**
	 * The column name
	 */
	private String columnName;

	/**
	 * Constructor.
	 * 
	 * @param aliasStr
	 *            the table alias that qualifies the column
	 * @param columnStr
	 *            the column name
	 */
	public ColumnAtom(String aliasStr, String columnStr) {
		this.alias = aliasStr;
		this.columnName = columnStr;
	}

	/**
	 * Gets the column name of this <code>ColumnAtom</code> wihtout the alias
	 * 
	 * @return the column name
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * Gets the alias associated with this <code>ColumnAtom</code>
	 * 
	 * @return the alias of this <code>ColumnAtom</code>
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @see edu.buffalo.db.parser.sqlexpr.Atom#getType()
	 */
	@Override
	public int getType() {
		return Atom.COLUMN_ATOM;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((alias == null) ? 0 : alias.hashCode());
		result = PRIME * result
				+ ((columnName == null) ? 0 : columnName.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ColumnAtom other = (ColumnAtom) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (alias == null)
			return columnName;
		else
			return alias + "." + columnName;
	}

}