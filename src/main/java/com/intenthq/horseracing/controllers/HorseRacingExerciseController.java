package com.intenthq.horseracing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.intenthq.horseracing.controllers.Race;
import com.intenthq.horseracing.model.Racer;
import com.intenthq.horseracing.factory.RacerFactory;

import org.springframework.context.annotation.Scope;
import com.intenthq.horseracing.NonExistingTrackException;
import java.util.logging.Logger;

@Controller
@Scope("request")
public class HorseRacingExerciseController {

	@Autowired
	private Race race;

	public static final String INPUT_ATT = "input";
	public static final String OUTPUT_ATT = "output";

	public static Logger LOGGER = Logger.getLogger("Race logging");
	
	@RequestMapping("/horse-racing/exercise")
	public String exercise(
			@RequestParam(value = "input", required = false) String input,
			ModelMap model) {
		if (!StringUtils.isEmpty(input)) {
			
			String output = generateOutputFromInput(input);
			
			model.addAttribute(INPUT_ATT, input);
			model.addAttribute(
					OUTPUT_ATT,
					output);
		}
		return "exercise";
	}

	private void prepareRace(String horseNames) {
		try {
			String[] names = horseNames.split(",");
			race.initRace(names.length, 220); //TODO the length of the track should come from a config or some other external place, it shouldn't be hardcodd
			for (int i = 0; i < names.length; ++i) {
				names[i] = names[i].replaceAll("\\s", "");
				Racer racer = RacerFactory.createNewHorseRacer(names[i]);
				race.addNewRacer(racer,i);
			}
		} catch (NonExistingTrackException nete) {
			LOGGER.info("Something went wrong in preparing the race!");
		}
	}
	
	private String generateOutputFromInput(String input) {
		String raceState = "";
		String[] inputArray = input.split("\n");
		try {	
			String[] tempArray;
			if (input.contains(",")) { //This means that we specified a new race, with new horse names
				prepareRace(inputArray[0]);
				tempArray = new String[inputArray.length-1];
				for (int i=0;i<inputArray.length-1;++i) {	//TODO handle this in a better way, after changes in the specification
					tempArray[i] = inputArray[i+1];
				}
			} else {
				tempArray = inputArray;
			}			

			race.updateRace(tempArray);
			raceState = race.getRaceState();
		} catch (NonExistingTrackException nete) {
			raceState = "The input is not valid, probably one of the tracks does not exist!";
			LOGGER.info(raceState);
		} catch (NullPointerException npe) {
			raceState = "NPE The input is not valid, probably one of the tracks does not exist!";
			LOGGER.info(raceState);
		}
		return raceState;
	}

}
