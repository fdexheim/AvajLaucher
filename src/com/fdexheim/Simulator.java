package src.com.fdexheim;

import java.util.Scanner;
import java.io.File;
import src.com.fdexheim.avajlauncherlog.AvajLauncherLog;

public class Simulator
{
	public static void		usage() {
		System.out.println("Usage : add a single parsable file as a single argument");
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

		AvajLaucherLog.setupLog();

		File				simulationFile;
		Scanner				sc;
		String				line;
		int					simulatorIterations;

/*
		try {
			try {
				simulationFile = new File(args[0]);
			}
			catch (NullPointerException e) {
				System.out.println("Failed to open file\n");
				System.exit(1);
			}
		
		
		
			try {
				if (simulationFile.canRead() == false) {
					System.out.println("[Error] : file cannot be read");
					System.exit(1);
				}
			}
			catch (SecurityException e) {
				throw new BadSimulationFileException("Error : file cannot be read");
			}
		
		
		
			if (simulatorIterations <= 0)
		

			while (1) {
				try {
				
				}
				catch (AvajLaucherException e) {
				
				}
			}
		}
		catch (AvajLaucherException e) {
			
		}
*/
	}


}
