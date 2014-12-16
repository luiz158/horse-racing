package com.intenthq.horseracing.io;

import com.google.common.base.Preconditions;
import com.intenthq.horseracing.core.Lane;
import com.intenthq.horseracing.core.Race;

public class RaceOutputFormatter {
	
	static final String NEW_LINE = "\n";
	static final String SEPARATOR = ", ";
	static final String HEADER = "Position, Lane, Horse name";
	static final String NOT_TERMINATED = "Racing is not terminated!";
	
	private RaceOutputFormatter() {
		// static
	}
	
	public static String format(Race race) {
		Preconditions.checkNotNull(race);
		
		String output;
		
		if(race.isTerminated()) {
			StringBuffer outputBuffer = new StringBuffer();
			int position = 1;
			
			outputBuffer.append(HEADER);
			
			for(Lane lane : race.getResults()) {
				outputBuffer.append(NEW_LINE + 
						position + SEPARATOR + 
						lane.getNumber() + SEPARATOR + 
						lane.getHorse().getName());
				position++;
			}
			
			output = outputBuffer.toString();
			
		} else {
			output = NOT_TERMINATED;
		}
		
		return output;
	}

}
