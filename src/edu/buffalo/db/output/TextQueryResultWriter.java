package edu.buffalo.db.output;

import java.io.PrintStream;

import edu.buffalo.db.storage.Tuple;
import edu.buffalo.db.storage.catalog.IntermediateDef;

public class TextQueryResultWriter extends QueryResultWriter {

	public TextQueryResultWriter( PrintStream out ){
		super(out);
	}		
	
	@Override
	public void addTuple(Tuple tuple) throws OutputException {
		out.println(tuple.toString());
	}

	@Override
	public void endQueryResult() throws OutputException {
	}

	@Override
	public void startQueryResult(IntermediateDef outputSchema)
			throws OutputException {
	}

}
