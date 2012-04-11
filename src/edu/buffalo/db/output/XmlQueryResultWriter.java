package edu.buffalo.db.output;

import java.io.PrintStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.buffalo.db.parser.sqlexpr.ColumnAtom;
import edu.buffalo.db.storage.Tuple;
import edu.buffalo.db.storage.Value;
import edu.buffalo.db.storage.catalog.IntermediateDef;

public class XmlQueryResultWriter extends QueryResultWriter {

	/**
	 * XML document builder
	 */
	DocumentBuilder builder = null;
	
	/**
	 * The XML document which stores the current query result
	 */
	Document document = null;
	
	/**
	 * The element of the document which stores all the tuples 
	 */
	Element tupleElementParent = null;
	
	/**
	 * The query result format
	 */
	
	IntermediateDef outputSchema;
	
	/**
	 * Constructs an instance of the class
	 * @param out
	 * 			the output where everything is going to be written
	 */
	public XmlQueryResultWriter( PrintStream out ){
		super(out);
	}	
	
	@Override
	public void addTuple(Tuple tuple) throws OutputException {
		// create an element for the tuple
		Element tupleElem = document.createElement( "tuple" );
		tupleElementParent.appendChild( tupleElem );

		// add all the values to the tuple 
		int valueId = 0;
		for( Value value : tuple.getValues() ){
			Element valueElem = document.createElement("value");
			tupleElem.appendChild( valueElem );

			// description of the current value
			ColumnAtom atom = outputSchema.getColumnAtoms().size() > valueId 
					? outputSchema.getColumnAtoms().get( valueId )
					: null;
					
			// fill the value element
			valueElem.setAttribute( "id", new Integer( valueId + 1 ).toString() );
			valueElem.setAttribute( "name", ( atom == null 
				? "MISSING" 
				: atom.getColumnName() ) );
			
			String valueStr = value.getValue();
			valueElem.setTextContent( valueStr == null ? "null" : valueStr );
			
			valueId++;
		}
		
		// print the rest elements
		for( ; valueId < outputSchema.getColumnAtoms().size(); valueId++ ){
			Element valueElem = document.createElement("value");
			tupleElem.appendChild( valueElem );

			// description of the current value
			ColumnAtom atom = outputSchema.getColumnAtoms().size() > valueId 
					? outputSchema.getColumnAtoms().get( valueId )
					: null;
					
			// fill the value element
			valueElem.setAttribute( "id", new Integer( valueId + 1 ).toString() );
			valueElem.setAttribute( "name", ( atom == null 
				? "UNKNOWN" 
				: atom.getColumnName() ) );
			valueElem.setTextContent( "MISSING" );			
		}
	}

	@Override
	public void endQueryResult() throws OutputException {
		// print the current document
		out.println( XmlPlanWriter.getStringFromDocument( document ) );

		// reset the query result information
		document = null;
		outputSchema = null;
	}

	@Override
	public void startQueryResult( IntermediateDef outputSchema ) throws OutputException {
		
		this.outputSchema = outputSchema;
		
		try{
			if ( builder == null ){
				builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			}
			
			document = builder.newDocument();
			
			// create the root element of the document
			document.appendChild( document.createElement("queryResult") );
			tupleElementParent = document.getDocumentElement();
		}
		catch( ParserConfigurationException ex ){
			throw new OutputException( ex );
		}
	}

}
