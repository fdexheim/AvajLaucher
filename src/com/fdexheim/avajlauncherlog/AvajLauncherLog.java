package src.com.fdexheim.avajlauncherlog;

import src.com.fdexheim.avajlauncherexceptions.BadFileAccessException;
import java.io.FileWriter;
import java.io.IOException;

public class	AvajLauncherLog {
	private static FileWriter		fWriter;
	private static boolean			backupStdout = true;
	private static AvajLauncherLog	logger = new AvajLauncherLog();

	public static AvajLauncherLog	getLog() {
		return (logger);
	}

	public							AvajLauncherLog() {
		logger = this;
	}

	public void		toggleBackupStdout(boolean status) {
		backupStdout = status;
	}

	public void						setupLog(String logFile) {
		try {
			fWriter = new FileWriter(logFile);
			toggleBackupStdout(false);
		}
		catch (IOException e) {
			toggleBackupStdout(true);
			System.out.print("[WARNING] Failed to setup log file, will be outputting to stdout instead\n");
		}
	}

	public void						addLog(String line) {
		try {
			if (backupStdout == true) {	
				System.out.print(line);
			}
			else
				fWriter.write(line);
		}
		catch (IOException e) {
			System.out.println("Failed to log in file : " + line);
		}
	}

	public static void				closeLog() {
		try {
			if (backupStdout == false)
				fWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();	
		}
		logger = null;
	}
}
