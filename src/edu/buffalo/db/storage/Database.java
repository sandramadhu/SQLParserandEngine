package edu.buffalo.db.storage;

import edu.buffalo.db.parser.sqlexpr.TableAtom;
import edu.buffalo.db.physical.OperatorImpl;
import edu.buffalo.db.storage.catalog.Catalog;
import edu.buffalo.db.storage.catalog.IntermediateDef;

/**
 * Database interface
 * 
 * @author Denis Mindolin
 */
public interface Database {

	/**
	 * Get the database schema
	 * 
	 * @return the database schema
	 */
	public Catalog getCatalog();

	/**
	 * Creates a physical instance of a table-scan operator. This operator will
	 * sequentially retrieve one tuple at a time, as opposed to index-scan or
	 * sort-scan.
	 * 
	 * @param tableAtom
	 *            the <code>TableAtom</code> referencing the table to scan
	 * @param schema
	 *            the output schema that the returned <code>OperatorImpl</code>
	 *            is expected to have
	 * @return an instanceof the physical table scan operator
	 */
	public OperatorImpl tableScan(TableAtom tableAtom, IntermediateDef schema);
}