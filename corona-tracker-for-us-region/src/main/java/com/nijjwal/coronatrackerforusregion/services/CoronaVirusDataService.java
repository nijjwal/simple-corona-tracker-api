package com.nijjwal.coronatrackerforusregion.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nijjwal.coronatrackerforusregion.models.LocationStats;

@Service
/**
 * @Service tells Spring Framework to create an instance of a class.
 * @author Nijjwal
 *
 */
public class CoronaVirusDataService {

	public static final String US_VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
	private List<LocationStats> allStats = new ArrayList<>();

	public List<LocationStats> getAllStats() {
		return allStats;
	}

	public void setAllStats(List<LocationStats> allStats) {
		this.allStats = allStats;
	}

	@PostConstruct
	@Scheduled(cron = "1 * * * * *")
	/**
	 * @PostConstruct annotation tells Spring, after you construct instance of this
	 *                service execute this method.
	 * @throws IOException
	 */
	public List<LocationStats> fetchVirusData() throws IOException {
		List<LocationStats> newStats = new ArrayList<>();

		// 1. Create URL object
		URL url = new URL(US_VIRUS_DATA_URL);

		// 2. Make a call to Git Repository and store data.
		Reader reader = new InputStreamReader(new BOMInputStream(url.openStream()), "UTF-8");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

		for (CSVRecord record : records) {
			// 3. Get state/province name and city name
			String stateOrProvince = record.get("Province_State");
			String city = record.get("Admin2");

			/*
			 * 4. Get the header name of the last column i.e. <<the latest date>> for which
			 * the data is available
			 */
			List<String> listOfAllHeaderNamesForThisRecords = record.getParser().getHeaderNames();
			String latestDate = listOfAllHeaderNamesForThisRecords.get(listOfAllHeaderNamesForThisRecords.size() - 1);

			// 5. Country
			String country = record.get("iso3");

			// 6. Based on the latest date column pull total number of confirmed cases.
			String totalNumOfConfirmedCases = record.get(latestDate);

			// 7. Create an object of LocationStats and initialize it
			LocationStats locationStats = new LocationStats(stateOrProvince, city, latestDate, country,
					totalNumOfConfirmedCases);

			// System.out.println(locationStats);

			// 8. Add information of a location to the list
			newStats.add(locationStats);
		}

		// 9. Re-initialize the model (for concurrency, not 100% solves the concurrency
		// issue)
		allStats = newStats;

		return allStats;

	}
}
