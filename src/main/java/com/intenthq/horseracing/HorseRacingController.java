package com.intenthq.horseracing;

import com.intenthq.horseracing.parser.InputParser;
import com.intenthq.horseracing.parser.OutputFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HorseRacingController {

    public static final String INPUT_ATT = "input";
    public static final String OUTPUT_ATT = "output";
    private final HorseRacing horseRacing;

    public HorseRacingController() {
        horseRacing = new HorseRacing(new InputParser(), new OutputFormatter());
    }

    @RequestMapping("/horse-racing")
    public String horseRacing(ModelMap model) {
        return "horse-racing";
    }

    @RequestMapping("/horse-racing/exercise")
    public String exercise(@RequestParam(value="input", required=false) String input, ModelMap model) {
		if (!StringUtils.isEmpty(input)) {
            model.addAttribute(INPUT_ATT, input);
            model.addAttribute(OUTPUT_ATT, horseRacing.startRaceWith(input));
		}
        return "exercise";
    }

}
