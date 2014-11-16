package com.intenthq.horseracing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HorseRacingController {

    public static final String INPUT_ATT = "input";
    public static final String OUTPUT_ATT = "output";
    private HorseRacingService horseRacingService;

    @Autowired
    public HorseRacingController(final HorseRacingService horseRacingService) {
        this.horseRacingService = horseRacingService;
    }

    @RequestMapping("/horse-racing")
    public String horseRacing(ModelMap model) {
        return "horse-racing";
    }

    @RequestMapping("/horse-racing/exercise")
    public String exercise(@RequestParam(value="input", required=false) String input, ModelMap model) {
        try {
            if (!StringUtils.isEmpty(input)) {
                model.addAttribute(INPUT_ATT, input);
                model.addAttribute(OUTPUT_ATT, horseRacingService.processRace(input));
            }
        } catch (NoValidHorsesException e) {
            e.printStackTrace();
        }
        return "exercise";
    }

}
