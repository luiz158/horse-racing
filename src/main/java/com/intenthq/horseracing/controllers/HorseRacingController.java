package com.intenthq.horseracing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HorseRacingController {

	@RequestMapping("/horse-racing")
	public String horseRacing(ModelMap model) {
		return "horse-racing";
	}
}
