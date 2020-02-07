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
import java.lang.Integer;
import java.lang.NumberFormatException;

//import javax.print.attribute.standard.PrinterURI;

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

	private static int				parseIters() {
		String				line;
		int					ret;

		try {
			line = lineReader.readLine();
			ret = Integer.parseInt(line);
		} catch (IOException e) {
			e.printStackTrace();
			ret = -1;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ret = -1;
		}
		return (ret);
	}

/*	private static boolean			checkAircraftType(String type) throws BadScenarioFileException {
		for (String s : aircraftTypes) {
			if (s.equals(type) == true)
				return true;
		}
		throw new BadScenarioFileException("Invalid Scenario file : Unknown Aircraft type");
	}
*/
	private static void				parseAircrafts() throws BadScenarioFileException, BadFileAccessException {
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

		System.out.print("[Command parse iteration : ");
		System.out.print(Integer.toString(lineNumber));
		System.out.print("]\n");
		lineNumber++;

				splitLine = line.split(" ");
				if (splitLine.length != 5)
					throw new BadScenarioFileException("Invalid Scenario file : Incorrect number of arguments for aircraft creation");
				try {
					type = splitLine[0];
					name = splitLine[1];
					longitude = Integer.parseInt(splitLine[2]);
					latitude = Integer.parseInt(splitLine[3]);
					height = Integer.parseInt(splitLine[4]);

					add = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
					add.registerTower(weatherTower);

					if (longitude < 0 || latitude < 0 || height < 0 ) // what about height > 100 ?
						throw new BadScenarioFileException ("Invalid Scenario file : One of the starting coordinates is invalid");
				} catch (NumberFormatException e) {
					throw new BadScenarioFileException("Invalid Scenario file : One of the starting coordinates is invalid");
				} catch (BadAircraftTypeException e) {
					throw new BadScenarioFileException("Invalid Scenario file : One of the Aircrafts is of an unknown type");
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
			System.out.print("[Run Iteration : ");
			System.out.print(Integer.toString(i));
			System.out.print("]\n");
			weatherTower.conditionsChanged();
			i++;
		}
	}

	// ARGS[0] in java == ARGV[1] in c !!!!
	public static void		main(String[] args) {
		if (args.length == 0) {
			usage();
			System.exit(1);
		}
		else if (args.length > 1) {
			usage();
			System.out.println("Arguments after the first will be ignored...");
		}

		try {
			AvajLauncherLog.logger = new AvajLauncherLog("Simulation.txt");
		} catch (BadFileAccessException e) {
			e.printStackTrace();
		}

		try {
			weatherTower = new WeatherTower();
			simulationFile = new FileReader(args[0]);
			lineReader = new BufferedReader(simulationFile);
			simulatorIterations = parseIters();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("Iterations : ");
		System.out.print(Integer.toString(simulatorIterations));
		System.out.print("\n");
		try {
			parseAircrafts();
		} catch (BadScenarioFileException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (BadFileAccessException e) {
			e.printStackTrace();
			System.exit(0);
		}
		runSimulation();
		AvajLauncherLog.getLog().closeLog();
	}
}
