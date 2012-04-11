package edu.buffalo.db.storage.csv;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import edu.buffalo.db.parser.sqlexpr.TableAtom;
import edu.buffalo.db.physical.OperatorImpl;
import edu.buffalo.db.storage.Tuple;
import edu.buffalo.db.storage.Value;
import edu.buffalo.db.storage.catalog.IntermediateDef;

/**
 * @author Denis Mindolin
 * 
 * Represents a CSV-file scan operator
 */
public class CSVTableScan extends OperatorImpl {
	/**
	 * The CSV file reader
	 * 
	 */
	protected CSVReader reader;

	/**
	 * The filename of the CSV file containing the tuples
	 */
	private String fileName;

	/**
	 * The corresponding table atom
	 */
	private TableAtom tableAtom;
	
	/**
	 * The parent operator
	 * 
	 */
	protected OperatorImpl parent;
	
	
	/**
	 * Default constructor
	 * 
	 * @param tAtom
	 *            the <code>TableAtom</code> referencing the table to be
	 *            scanned
	 * @param schema
	 *            the output schema that the <code>CSVTableScan</code>
	 *            operator is expected to have
	 * @param file
	 *            the local file system path to the file containing the tuples
	 *            of the table to be scanned
	 */
	public CSVTableScan(TableAtom tAtom, IntermediateDef schema, String file) {
		tableAtom = tAtom;
		outSchema = schema;
		fileName = file;
	}

	/**
	 * Returns the parent operator for the current operator
	 * 
	 * @return the parent operator
	 */
	public OperatorImpl getParent() {
		return parent;
	}

	/**
	 * Sets the parent operator for the current operator
	 * 
	 * @param parentOp
	 *            the parent operator
	 */
	public void setParent(OperatorImpl parentOp) {
		parent = parentOp;
	}

	/**
	 * Returns the child operator collection
	 * 
	 * @return the child operator collection
	 */
	public List<OperatorImpl> getChildOperators() {
		return null;
	}

	/**
	 * Returns the next tuple
	 * 
	 * @return the next tuple, or null if EOF has been reached
	 */
	public Tuple getNext() {

		try {
			String[] row = reader.readNext();

			if (row == null)
				return null;

			Tuple tuple = new Tuple();
			for (String value : row)
				tuple.addValue(value.trim().equals("") ? new Value(null)
						: new Value(value));

			return tuple;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

		return null;
	}

	/**
	 * Opens this operator, that is, the file reader.
	 */
	public void open() {
		try {
			reader = new CSVReader(new FileReader(fileName));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Closes this operator, that is, the file reader.
	 */
	public void close() {
		try {
			reader.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public String paramsToString() {
		return tableAtom.toString();
	}

	
}