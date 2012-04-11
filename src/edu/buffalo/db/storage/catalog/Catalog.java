package edu.buffalo.db.storage.catalog;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Michalis Petropoulos
 * 
 * Represents a realitonal database catalog.
 * 
 */
public class Catalog {

	private String catalogName;

	private Map<String, TableDef> tableDefs;

	/**
	 * Constructs a <code>Catalog</code> with the given name
	 * 
	 * @param name
	 *            the name of the <code>Catalog</code>
	 */
	public Catalog(String name) {
		catalogName = name;
		tableDefs = new LinkedHashMap<String, TableDef>();
	}

	/**
	 * Gets the name of the <code>Catalog</code>
	 * 
	 * @return the <code>Catalog</code> name
	 */
	public String getCatalogName() {
		return catalogName;
	}

	/**
	 * Gets all <code>TableDef</code> instances in this <code>Catalog</code>
	 * 
	 * @return a collection of <code>TableDef</code> instances
	 */
	public Collection<TableDef> getTableDefs() {
		return Collections.unmodifiableCollection(tableDefs.values());
	}

	/**
	 * Tests if a <code>TableDef</code> with a given name exists in this
	 * <code>Catalog</code>
	 * 
	 * @param name
	 *            a table name
	 * @return <code>true</code> if one found; <code>false</code> otherwise
	 */
	public boolean hasTableDef(String name) {
		return tableDefs.containsKey(name);
	}

	/**
	 * Gets a <code>TableDef</code> with a given name
	 * 
	 * @param name
	 *            a table name
	 * @return a <code>TableDef</code> instance
	 * @throws CatalogException
	 *             if no <code>TableDef</code> instance was found
	 */
	public TableDef getTableDef(String name) throws CatalogException {
		if (tableDefs.get(name) != null)
			return (TableDef) tableDefs.get(name);

		throw new CatalogException("Table definition " + name
				+ " not found in catalog " + catalogName);
	}

	/**
	 * Adds a <code>TableDef</code> instance with a given name to this
	 * <code>Catalog</code>
	 * 
	 * @param name
	 *            the name of the <code>TableDef</code> instance to create
	 * @return the newly created <code>TableDef</code> instance
	 * @throws CatalogException
	 *             if a <code>TableDef</code> with the same name already
	 *             exists
	 */
	public TableDef addTableDef(String name) throws CatalogException {
		TableDef tableDef = new TableDef(name);
		if (!tableDefs.containsKey(name)) {
			tableDefs.put(name, tableDef);
			return tableDef;
		}

		throw new CatalogException("Table definition " + name
				+ " already added to catalog " + catalogName);
	}

}
