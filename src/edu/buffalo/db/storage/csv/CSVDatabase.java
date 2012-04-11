package edu.buffalo.db.storage.csv;

import java.io.IOException;

import edu.buffalo.db.parser.sqlexpr.TableAtom;
import edu.buffalo.db.physical.OperatorImpl;
import edu.buffalo.db.storage.Database;
import edu.buffalo.db.storage.catalog.Catalog;
import edu.buffalo.db.storage.catalog.CatalogException;
import edu.buffalo.db.storage.catalog.IntermediateDef;
import edu.buffalo.db.storage.catalog.csv.CSVCatalog;

/**
 * Implementation of CSV database
 * 
 * @author Denis Mindolin
 */
public class CSVDatabase implements Database {
	/**
	 * Database catalog
	 * 
	 */
	private CSVCatalog catalog;

	/**
	 * Constructs a <code>CSVDatabase</code>
	 * 
	 * @param dbPath
	 *            the local file system path that constains the data files
	 *            (.data), the schema files (.meta) and the catalog file
	 *            (catalog.meta)
	 * @throws IOException
	 *             if any database file cannot be read/opened/etc
	 * @throws CatalogException
	 *             if schema files are semantically wrong
	 */
	public CSVDatabase(String dbPath) throws IOException, CatalogException {
		catalog = CSVCatalog.readCatalog(dbPath);
	}

	/**
	 * Get the database catalog
	 * 
	 * @return the database catalog
	 */
	public Catalog getCatalog() {
		return catalog;
	}

	/**
	 * @see edu.buffalo.db.storage.Database#tableScan(edu.buffalo.db.parser.sqlexpr.TableAtom,
	 *      edu.buffalo.db.storage.catalog.IntermediateDef)
	 */
	public OperatorImpl tableScan(TableAtom tableAtom, IntermediateDef schema) {
		return new CSVTableScan(tableAtom, schema, catalog
				.getFileName(tableAtom.getTableName()));
	}
}