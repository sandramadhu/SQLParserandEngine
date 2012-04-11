package edu.buffalo.db.output;

import java.io.PrintStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.buffalo.db.logical.Operator;
import edu.buffalo.db.parser.sqlexpr.ColumnAtom;
import edu.buffalo.db.physical.OperatorImpl;
import edu.buffalo.db.storage.catalog.IntermediateDef;

public class XmlPlanWriter extends PlanWriter {

	public XmlPlanWriter( PrintStream out ){
		super(out);
	}
	
	@Override
	public void printPlan(Operator rootOperator) throws OutputException  {

		try{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.newDocument();
			
			// create the root element of the document
			doc.appendChild( doc.createElement("logicalPlan") );
			
			// print add an element representing the root node
			addOperatorChild( doc.getDocumentElement(), doc, rootOperator );
			
			// convert the xml document to text
			String text = getStringFromDocument( doc );
			
			// print the text to the output
			out.println( text );
		}
		catch( ParserConfigurationException ex ){
			throw new OutputException( ex );
		}
	}

	@Override
	public void printPlan(OperatorImpl rootOperator) throws OutputException {
		// TODO Auto-generated method stub
		try{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.newDocument();
			
			// create the root element of the document
			doc.appendChild( doc.createElement("physicalPlan") );
			
			// print add an element representing the root node
			addOperatorChild( doc.getDocumentElement(), doc, rootOperator );
			
			// convert the xml document to text
			String text = getStringFromDocument( doc );
			
			// print the text to the output
			out.println( text );
		}
		catch( ParserConfigurationException ex ){
			throw new OutputException( ex );
		}
	}
	
	
	/**
	 * Converts an XML-document to string
	 * @param doc 
	 * 	the document to convert
	 * @return
	 * 	the string representing @param doc
	 */
	public static String getStringFromDocument(Document doc)
	{
	    try
	    {
	       DOMSource domSource = new DOMSource(doc);
	       StringWriter writer = new StringWriter();
	       StreamResult result = new StreamResult(writer);
	       TransformerFactory tf = TransformerFactory.newInstance();
	       Transformer transformer = tf.newTransformer();
	       transformer.transform(domSource, result);
	       return writer.toString();
	    }
	    catch(TransformerException ex)
	    {
	       ex.printStackTrace();
	       return null;
	    }
	} 	
	
	/**
	 * Adds a new operator to the plan's XML document
	 * @param currentElem
	 * 		the XML element of the current logical plan operator
	 * @param doc
	 * 		the plan's XML document
	 * @param operator
	 * 		a child logical operator
	 */
	protected void addOperatorChild(Element currentElem, Document doc, Operator operator ){
		// create an xml-element to store the operator information
		Element operatorElem = doc.createElement("operator");
		currentElem.appendChild( operatorElem );
		
		// set the operator type
		operatorElem.setAttribute( "type", operator.getClass().getSimpleName() );
		
		// create the outputSchema element
		Element schemaElem = doc.createElement( "outputSchema" );
		operatorElem.appendChild( schemaElem );
		
		// add the schema
		IntermediateDef outputSchema = operator.getOutputSchema();
		
		for( ColumnAtom atom: outputSchema.getColumnAtoms() ){
			Element atomElem = doc.createElement("column");
			schemaElem.appendChild( atomElem );
			
			atomElem.setAttribute( "alias", atom.getAlias() );
			atomElem.setAttribute( "name", atom.getColumnName());
		}
		
		// add the parameters
		Element paramsElem = doc.createElement( "params" );
		operatorElem.appendChild( paramsElem );
		
		String params = operator.paramsToString();
		paramsElem.setTextContent( params == null ? "" : params );
		
		// add the rest children
		for( Operator child: operator.getChildOperators()){
			addOperatorChild( operatorElem, doc, child );
		}
	}

	/**
	 * Adds a new operator to the plan's XML document
	 * @param currentElem
	 * 		the XML element of the current logical plan operator
	 * @param doc
	 * 		the plan's XML document
	 * @param operator
	 * 		a child logical operator
	 */
	protected void addOperatorChild(Element currentElem, Document doc, OperatorImpl operator ){
		// create an xml-element to store the operator information
		Element operatorElem = doc.createElement("operator");
		currentElem.appendChild( operatorElem );
		
		// set the operator type
		operatorElem.setAttribute( "type", operator.getClass().getSimpleName() );
		
		// create the outputSchema element
		Element schemaElem = doc.createElement( "outputSchema" );
		operatorElem.appendChild( schemaElem );
		
		// add the schema
		IntermediateDef outputSchema = operator.getOutputSchema();
		
		for( ColumnAtom atom: outputSchema.getColumnAtoms() ){
			Element atomElem = doc.createElement("column");
			schemaElem.appendChild( atomElem );
			
			atomElem.setAttribute( "alias", atom.getAlias() );
			atomElem.setAttribute( "name", atom.getColumnName());
		}

		// add the parameters
		Element paramsElem = doc.createElement( "params" );
		operatorElem.appendChild( paramsElem );
		
		String params = operator.paramsToString();
		paramsElem.setTextContent( params == null ? "" : params );		
		
		// add the rest children
		for( OperatorImpl child: operator.getChildOperators()){
			addOperatorChild( operatorElem, doc, child );
		}
	}
	

}
