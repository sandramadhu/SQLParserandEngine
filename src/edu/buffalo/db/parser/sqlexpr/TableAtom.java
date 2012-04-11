package edu.buffalo.db.parser.sqlexpr;

/**
 * @author Michalis Petropoulos
 * 
 * Represents a table atom in the FROM clause of an SQL query expression.
 * 
 */

public class TableAtom extends Atom {

	private String tableName;

	private String alias = null;

	/**
	 * Default constructor
	 * 
	 * @param tableStr
	 *            the name of the table referenced by this
	 *            <code>TableAtom</code>
	 * @param aliasStr
	 *            the name of the alias used by this <code>TableAtom</code>
	 */
	public TableAtom(String tableStr, String aliasStr) {
		tableName = tableStr;
		alias = aliasStr;
	}

	/**
	 * Gets the name of the table referenced by this <code>TableAtom</code>
	 * 
	 * @return the table name
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Gets the name of the alias used by this <code>TableAtom</code>
	 * 
	 * @return the name of the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @see edu.buffalo.db.parser.sqlexpr.Atom#getType()
	 */
	@Override
	public int getType() {
		return Atom.TABLE_ATOM;
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
				+ ((tableName == null) ? 0 : tableName.hashCode());
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
		final TableAtom other = (TableAtom) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (alias == null)
			return tableName;

		return tableName + " " + alias;
	}

}