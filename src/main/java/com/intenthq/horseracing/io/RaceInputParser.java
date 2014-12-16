package com.intenthq.horseracing.io;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.intenthq.horseracing.core.Hole;
import com.intenthq.horseracing.core.Lane;

/**
 * Parse the string to the processor.
 */
public class RaceInputParser {
	
	private final static Splitter LINES_SPLITTER = Splitter.on(Pattern.compile("\r?\n"));
	private final static Splitter COMMA_SPLITTER = Splitter.on(",").trimResults();
	private final static Splitter SPACE_SPLITTER = Splitter.on(" ").trimResults();
	
	private final RaceInputProcessor processor;
	
	public RaceInputParser(RaceInputProcessor processor) {
		this.processor = Preconditions.checkNotNull(processor);
	}

	/**
	 * Parse the string to the processor.
	 * @param input the string to parse.
	 * @throws IOException
	 */
	public void parse(String input) throws RaceInputException {
		Preconditions.checkNotNull(input);
		
		List<String> lines = LINES_SPLITTER.splitToList(input);
		if(lines.size() < 2) {
			throw new RaceInputException("Invalid input: " + input);
		}
		
		boolean namesRead = false;
		for(String line : lines) {
			if(!namesRead) {
				List<String> values = COMMA_SPLITTER.splitToList(line);
				if(values.size() < 2) {
					throw new RaceInputException("Invalid input at line: " + line);
				}
				
				this.processor.processHorseNames(values);
				
				namesRead = true;
				
			} else {
				List<String> values = SPACE_SPLITTER.splitToList(line);
				if(values.size() != 2) {
					throw new RaceInputException("Invalid input at line: " + line);
				}
				
				Integer laneNumber = Integer.valueOf(values.get(0));
				if(laneNumber < Lane.MIN_LANE_NUMBER || laneNumber > Lane.MAX_LANE_NUMBER) {
					throw new RaceInputException("Invalid lane number at line: " + line);
				}
				
				Hole hole = Hole.fromString(values.get(1));
				if(hole == null) {
					throw new RaceInputException("Invalid hole number at line: " + line);
				}
				
				this.processor.processHoleHit(laneNumber, hole);
			}
		}
	}
	
	/**
	 * Interface to implement a {@link RaceInputProcessor}.
	 */
	public interface RaceInputProcessor {
		
		/**
		 * Called with the name horses in order.
		 * @param names the name of parsed horses in order.
		 */
		void processHorseNames(List<String> names);
		
		/**
		 * Called when a hole hit is parsed.
		 * @param laneNumber the lane number.
		 * @param hole the hole hit.
		 */
		void processHoleHit(Integer laneNumber, Hole hole);
		
	}

}
