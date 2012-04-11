package edu.buffalo.db.parser.sqlexpr;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import edu.buffalo.db.parser.ParseException;
import edu.buffalo.db.parser.SQLParser;

/**
 * @author Michalis Petropoulos
 * 
 *         Represents a union of SQL query expressions.
 * 
 */
public class UnionExpr {

	/**
	 * The list of <code>SQLExpr</code> instances.
	 */
	private List<SQLExpr> sqlExprs;

	/**
	 * Default constructor
	 * 
	 */
	public UnionExpr() {
		sqlExprs = new Vector<SQLExpr>();
	}

	/**
	 * Adds an SQLExpr to this UnionExpr.
	 * 
	 * @param sqlExpr
	 *            the SQLExpr to add
	 */
	public void addSQLExpr(SQLExpr sqlExpr) {
		sqlExprs.add(sqlExpr);
	}

	/**
	 * Gets all <code>SQLExpr</code> instances in this <code>UnionExpr</code>
	 * 
	 * @return a list of <code>SQLExpr</code> instances
	 */
	public List<SQLExpr> getSQLExprs() {
		return Collections.unmodifiableList(sqlExprs);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String retString = "";
		for (SQLExpr sqlExpr : getSQLExprs()) {
			retString += sqlExpr.toString();
			retString += "UNION\n";
		}
		// remove the last UNION
		retString = retString.substring(0, retString.length() - 6);

		return retString + ";\n";
	}

	/**
	 * Testing method.
	 * 
	 * @param args
	 *            a single argument that is the file path to a text file
	 *            containing SQL query strings.
	 */
	public static void main(String[] args) {
		try {
			FileReader reader = new FileReader(args[0]);
			new SQLParser(reader);

			List<UnionExpr> queries = SQLParser.getUnionExprs();
			for (UnionExpr unionExpr : queries)
				System.out.println(unionExpr.toString());

		} catch (FileNotFoundException ex1) {
			System.out.println(ex1.getMessage());
		} catch (ParseException ex2) {
			System.out.println(ex2.getMessage());
		}
	}

}