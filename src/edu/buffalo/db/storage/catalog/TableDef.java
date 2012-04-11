package edu.buffalo.db.storage.catalog;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * @author Michalis Petropoulos
 * 
 * Represents a realitonal table definition.
 * 
 */
public class TableDef {

	private String tableName;

	private List<ColumnDef> columnDefs;

	private List<String> columnNames;

	/**
	 * Constructs a <code>TableDef</code> with the given name
	 * 
	 * @param name
	 *            the name of the table definition
	 */
	public TableDef(String name) {
		this.tableName = name;
		this.columnDefs = new Vector<ColumnDef>();
		this.columnNames = new Vector<String>();
	}

	/**
	 * Adds a column with the given name to this <code>TableDef</code>
	 * 
	 * @param name
	 *            tha column name
	 * @return the newly created <code>ColumnDef</code>
	 * @throws CatalogException
	 *             if another column with the same name already exists
	 */
	public ColumnDef addColumn(String name) throws CatalogException {
		ColumnDef column = new ColumnDef(name, columnDefs.size());
		if (!columnDefs.contains(column)) {
			columnDefs.add(column);
			columnNames.add(name);
			return column;
		}

		throw new CatalogException("Column definition " + name
				+ " already added to table definition " + tableName);
	}

	/**
	 * Gets the name of this <code>TableDef</code>
	 * 
	 * @return the table name
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Gets all <code>ColumnDef</code> instances in this <code>TableDef</code>
	 * 
	 * @return a list of <code>ColumnDef</code> instances
	 */
	public List<ColumnDef> getColumnDefs() {
		return Collections.unmodifiableList(columnDefs);
	}

	/**
	 * Tests if a <code>ColumnDef</code> with the given name exists in this
	 * <code>TableDef</code>
	 * 
	 * @param name
	 *            the name of the column to look for
	 * @return <code>true</code> if one found; <code>false</code> otherwise
	 */
	public boolean hasColumnDef(String name) {
		return columnNames.contains(name);
	}

}
