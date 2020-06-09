package com.nijjwal.coronatrackerforusregion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nijjwal.coronatrackerforusregion.models.LocationStats;
import com.nijjwal.coronatrackerforusregion.services.CoronaVirusDataService;

@RestController
@RequestMapping("/usregioncovidstats")
public class HomeController {

	@Autowired
	public CoronaVirusDataService locationStats;

	@GetMapping("/")
	public List<LocationStats> home() {

		return locationStats.getAllStats();
	}
}
