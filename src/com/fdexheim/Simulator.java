package src.com.fdexheim;

import src.com.fdexheim.avajlauncherflyers.Flyable;
import src.com.fdexheim.avajlauncherflyers.AircraftFactory;
import src.com.fdexheim.avajlauncherlog.AvajLauncherLog;
import src.com.fdexheim.avajlauncherexceptions.BadFileAccessException;
import src.com.fdexheim.avajlauncherexceptions.BadScenarioFileException;
import src.com.fdexheim.avajlauncherexceptions.BadAircraftTypeException;
import src.com.fdexheim.avajlauncherexceptions.AvajLauncherException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.Integer;
import java.lang.NumberFormatException;

public class Simulator
{
	static private WeatherTower		weatherTower;
	static private int				simulatorIterations;
	static private FileReader		simulationFile;
	static private BufferedReader	lineReader;
	static private String[]			aircraftTypes = { "Baloon", "Helicopter" , "JetPlane" };
	static private String[]			weatherEffects = { "FOG", "RAIN", "SNOW", "SUN" };

	private static void		usage() {
		System.out.println("Usage : add a single parsable file as a single argument");
	}

	private static int				parseIters() throws BadScenarioFileException {
		String				line;
		int					ret;

		line = null;
		try {
			line = lineReader.readLine();
			ret = Integer.parseInt(line);
		} catch (IOException e) {
			ret = -1;
		} catch (NumberFormatException e) {
			ret = -1;
		}
		if (ret < 0)
			throw new BadScenarioFileException("Invalid Scenario File : Iteration number '" + line + "' is either badly fornatted or below zero");
		return (ret);
	}

	private static void				parseAircrafts() throws BadScenarioFileException, BadFileAccessException, BadAircraftTypeException {
		String						line;
		String[]					splitLine;
		String						type;
		String						name;
		int							longitude;
		int							latitude;
		int							height;
		Flyable						add;
		int							lineNumber = 1;

		try {
			while ((line = lineReader.readLine()) != null) {
				lineNumber++;
				splitLine = line.split(" ");
				if (splitLine.length != 5)
					throw new BadScenarioFileException("Invalid Scenario file : Incorrect number of arguments for aircraft creation, requires 5, have " + Integer.toString(splitLine.length));
				try { 
					type = splitLine[0];
					name = splitLine[1];
					longitude = Integer.parseInt(splitLine[2]);
					latitude = Integer.parseInt(splitLine[3]);
					height = Integer.parseInt(splitLine[4]);

					add = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
					add.registerTower(weatherTower);

					if (longitude < 0 || latitude < 0 || height < 0 )
						throw new BadScenarioFileException ("Invalid Scenario file : line " + Integer.toString(lineNumber) + " : One of the starting coordinates is invalid");
				} catch (NumberFormatException e) {
					throw new BadScenarioFileException("Invalid Scenario file : line " + Integer.toString(lineNumber) + " : One of the starting coordinates is invalid");
				} catch (BadAircraftTypeException e) {
					throw new BadAircraftTypeException("Invalid Scenario file : line " + Integer.toString(lineNumber) + " : " + splitLine[0] + " is not a know aircraft type");
				}
			}
		} catch (IOException e) {
			throw new BadFileAccessException("An error occured when reading scenario file");
		}
	}

	private static void		runSimulation() {
		int					i;

		i = 1;
		while (i <= simulatorIterations) {
			weatherTower.changeWeather();
			weatherTower.conditionsChanged();
			i++;
		}
	}

	public static void		main(String[] args) {
		if (args.length == 0) {
			usage();
			System.exit(1);
		}
		else if (args.length > 1) {
			usage();
			System.out.println("Arguments after the first will be ignored...");
		}

		AvajLauncherLog.getLog().setupLog("Simulation.txt");
		try {
			weatherTower = new WeatherTower();
			simulationFile = new FileReader(args[0]);
			lineReader = new BufferedReader(simulationFile);
			simulatorIterations = parseIters();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (BadScenarioFileException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		try {
			parseAircrafts();
		} catch (BadScenarioFileException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (BadFileAccessException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (BadAircraftTypeException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		runSimulation();
		AvajLauncherLog.getLog().closeLog();
	}
}
