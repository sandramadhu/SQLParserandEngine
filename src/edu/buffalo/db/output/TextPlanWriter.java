package edu.buffalo.db.output;

import java.io.PrintStream;

import edu.buffalo.db.logical.Operator;
import edu.buffalo.db.physical.OperatorImpl;

/**
 * Represents a logical/physical plan writer as text
 * @author mindolin
 *
 */
public class TextPlanWriter extends PlanWriter {

	public TextPlanWriter( PrintStream out ){
		super(out);
	}
	
	@Override
	public void printPlan(Operator rootOperator) throws OutputException {
		out.println( printNode( rootOperator, 0 ) );
	}

	@Override
	public void printPlan(OperatorImpl rootOperator) throws OutputException {
		out.println( printNode( rootOperator, 0 ) );
	}
	
	/**
	 * Converts a physical operator to a string
	 * 
	 * @param operator
	 *            the operator to convert
	 * @param indent
	 *            the number of spaces in the prefix of the resulting string
	 * @return the string representation of the <i>operator</i>
	 */
	private String printNode(OperatorImpl operator, int indent) {
		String str = "";
		String indentStr = "";
		for (int i = 0; i < indent; i++)
			indentStr += "\t";
		str += indentStr + "[" + operator.getOutputSchema().toString() + "]\n";
		str += indentStr + operator.getClass().getSimpleName() + " - " + operator.paramsToString() + "\n";
		for (OperatorImpl op : operator.getChildOperators())
			str += printNode(op, indent + 1);

		return str;
	}


	/**
	 * Converts an logical operator a string
	 * 
	 * @param operator
	 *            the operator to convert
	 * @param indent
	 *            the number of spaces in the prefix of the resulting string
	 * @return the string representation of the <i>operator</i>
	 */
	private String printNode(Operator operator, int indent) {
		String str = "";
		String indentStr = "";
		for (int i = 0; i < indent; i++)
			indentStr += "\t";
		str += indentStr + "[" + operator.getOutputSchema().toString() + "]\n";
		str += indentStr + operator.getClass().getSimpleName() + " - " + operator.paramsToString() + "\n";
		for (Operator op : operator.getChildOperators())
			str += printNode(op, indent + 1);

		return str;
	}
}
