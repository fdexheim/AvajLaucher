package src.com.fdexheim.avajlauncherexceptions;

public class BadFileAccessException extends AvajLauncherException {
	public BadFileAccessException(String errorMessage) {
		super(errorMessage);
	}
	public BadFileAccessException() {
		super();
	}
}
