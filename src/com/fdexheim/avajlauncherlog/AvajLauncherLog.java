package src.com.fdexheim.avajlauncherlog;

import src.com.fdexheim.avajlauncherexceptions.BadFileAccessException;
import java.io.FileWriter;
import java.io.IOException;

public class	AvajLauncherLog {
	private static FileWriter		fWriter;
	private static boolean			backupStdout = true;
	public static AvajLauncherLog	logger = null;

	public static AvajLauncherLog	getLog() {
		return (logger);
	}

	public							AvajLauncherLog(String logFile) throws BadFileAccessException {
		if (logger != null)
			throw new BadFileAccessException("Attempt to instatiate a second logger");
		setupLog(logFile);
	}

	public void		toggleBackupStdout(boolean status) {
		backupStdout = status;
	}

	public void						setupLog(String logFile) {
		try {
			fWriter = new FileWriter(logFile);
			toggleBackupStdout(false);
			fWriter.write(" == Start of Log ==\n");
		}
		catch (IOException e) {
			toggleBackupStdout(true);
			System.out.print("Failed to setup log file, will be outputting to stdout instead");
		}
	}

	public void						addLog(String line) {
		try {
			if (backupStdout == true)
				System.out.println(line);
			else
				fWriter.write(line);
		}
		catch (IOException e) {
			System.out.println("Failed to log in file : " + line);
		}
	}

	public static void				closeLog() {
		try {
			fWriter.close();
		}
		catch (IOException e) {
			
		}
		logger = null;
	}
}
