package src.com.fdexheim.avajlauncherflyers;

public abstract class Aircraft {
	protected long		id;
	protected String		name;
	protected Coordinates	coordinates;

	static long			idCounter = 0;

	protected Aircraft(String name, Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		this.id = nextId();
	}

	private long nextId() {
		idCounter++;
		return (idCounter);
	}
}
