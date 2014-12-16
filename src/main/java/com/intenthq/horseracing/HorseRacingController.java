package com.intenthq.horseracing;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intenthq.horseracing.io.RaceInputException;
import com.intenthq.horseracing.io.RaceInputParser;
import com.intenthq.horseracing.io.RaceOutputFormatter;
import com.intenthq.horseracing.processor.RaceProcessor;

@Controller
public class HorseRacingController {

    public static final String INPUT_ATT = "input";
    public static final String OUTPUT_ATT = "output";

    @RequestMapping("/horse-racing")
    public String horseRacing(ModelMap model) {
        return "horse-racing";
    }

    @RequestMapping("/horse-racing/exercise")
    public String exercise(@RequestParam(value="input", required=false) String input, ModelMap model) throws IOException {
    	if (!StringUtils.isEmpty(input)) {
    		String output;
    		
    		try {
	    		RaceProcessor raceProcessor = new RaceProcessor();
	    		
	    		new RaceInputParser(raceProcessor).parse(input);
	    		
	    		output = RaceOutputFormatter.format(raceProcessor.getRace());
    		
    		} catch(RaceInputException ex) {
    			output = ex.getMessage();
    		}
	    		
            model.addAttribute(INPUT_ATT, input);
            model.addAttribute(OUTPUT_ATT, output);
		}
        return "exercise";
    }

}
