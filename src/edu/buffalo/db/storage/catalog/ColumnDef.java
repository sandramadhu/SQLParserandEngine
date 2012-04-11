package edu.buffalo.db.storage.catalog;

/**
 * @author Michalis Petropoulos
 * 
 * Represents a realitonal column definition.
 * 
 */
public class ColumnDef {

	private String name;

	/**
	 * The position of the column within the {@link TableDef}.
	 */
	private int position;

	/**
	 * Default constructor
	 * 
	 * @param name
	 *            the name of this <code>ColumnDef</code>
	 * @param pos
	 *            the position of this <code>ColumnDef</code> in the
	 *            <code>TableDef</code>
	 */
	public ColumnDef(String name, int pos) {
		this.name = name;
		this.position = pos;
	}

	/**
	 * Gets the name of this <code>ColumnDef</code>
	 * 
	 * @return the <code>ColumnDef</code> name
	 */
	public String getColumnName() {
		return name;
	}

	/**
	 * Gets the position of this <code>ColumnDef</code> in the
	 * <code>TableDef</code>
	 * 
	 * @return the position as an integer
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
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
		final ColumnDef other = (ColumnDef) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
