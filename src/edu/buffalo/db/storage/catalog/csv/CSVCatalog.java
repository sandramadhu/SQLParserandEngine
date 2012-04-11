package edu.buffalo.db.storage.catalog.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import edu.buffalo.db.storage.catalog.Catalog;
import edu.buffalo.db.storage.catalog.CatalogException;
import edu.buffalo.db.storage.catalog.TableDef;

/**
 * Implementation of CSV database schema
 * 
 * @author Denis Mindolin
 */

public class CSVCatalog extends Catalog {

	/**
	 * Pairs of (table name, filename)
	 */
	private Map<String, String> fileNames;

	private CSVCatalog(String name) {
		super(name);

		fileNames = new HashMap<String, String>();
	}

	private void addFileName(TableDef tableDef, String fileName) {
		fileNames.put(tableDef.getTableName(), fileName);
	}

	/**
	 * Gets the local file system path to the file containing the tuples of the
	 * given table
	 * 
	 * @param tableName
	 *            tha name of the table
	 * @return a local file system path
	 */
	public String getFileName(String tableName) {
		return fileNames.get(tableName);
	}

	/**
	 * Creates an instanceof a database schema
	 * 
	 * @param dbPath
	 *            the path to the directory where the database is stored
	 * 
	 * @return an instanceof a database schema
	 * 
	 * @throws IOException
	 *             if any database file cannot be read/opened/etc
	 * @throws CatalogException
	 *             if schema files are semantically wrong
	 */
	public static CSVCatalog readCatalog(String dbPath) throws IOException,
			CatalogException {

		CSVCatalog catalog = new CSVCatalog(dbPath);

		String metaFilePath = dbPath + File.separator + "catalog.meta";

		// open the meta file
		CSVReader rd = new CSVReader(new FileReader(metaFilePath));

		// read the relations

		String[] row = null;

		while ((row = rd.readNext()) != null) {
			// get the relation name
			String relationName = row[0];

			TableDef tableDef = catalog.addTableDef(relationName);

			catalog.readTableDef(tableDef, dbPath + File.separator
					+ relationName + ".meta");

			catalog.addFileName(tableDef, dbPath + File.separator
					+ relationName + ".data");
		}

		rd.close();

		return catalog;
	}

	/**
	 * Reads a relation schema from a cvs file
	 * 
	 * @param relatioName
	 *            the name of the relation
	 * @throws FileNotFoundException
	 *             if the metadata file was not found IOException if there's
	 *             some problem while reading the metadata file CatalogException
	 *             if there is some error in the database schema itself
	 */
	private void readTableDef(TableDef table, String metaFileName)
			throws IOException, FileNotFoundException, CatalogException {

		CSVReader reader = new CSVReader(new FileReader(metaFileName));

		String row[];

		while ((row = reader.readNext()) != null)
			// get the column name
			table.addColumn(row[0]);

		reader.close();
	}

}