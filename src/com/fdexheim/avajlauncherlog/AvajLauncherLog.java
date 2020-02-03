import src.com.fdexheim.avajlauncherexceptions.BadFileAccessException;
import java.io.FileWriter;
import java.io.IOException;

public class	AvajLauncherLog {
	private static FileWriter		fWriter;
	private static boolean			backupStdout = false;
	private static AvajLauncherLog	logger = null;

	public static AvajLauncherLog	getLog() throws BadFileAccessException {
		if (logger == null) {
			throw (BadFileAccessException("AvajLauncherException has not been setup\n"));
		}
		return (logger);
	}

	public							AvajLauncherLog() { }

	public void		toggleBackupStdout(boolean status) {
		backupStdout = status;
	}

	public void						setupLog() throws BadFileAccessException {
		logger = new AvajLauncherLog();
		try {
			fWriter = new FileWriter("simulation.txt");
		}
		catch (IOException e) {
			toggleBackupStdout(true);
			System.out.print("Failed to setup log file, will be outputting to stdout instead");
			throw (BadFileAccessException("An error occured when setting up logger"));
		}
	}

	public void						addLog(String line) throws BadFileAccessException {
		try {
			if (backupStdout == true)
				System.out.println(line);
			else
				fWriter.write(line);
		}
		catch (IOException e) {
			throw (BadFileAccessException("An error happened when writing log"));
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
