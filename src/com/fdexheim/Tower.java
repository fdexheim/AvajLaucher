package src.com.fdexheim;

import src.com.fdexheim.avajlauncherflyers.Flyable;
import java.util.Vector;

public class Tower
{
	private Vector<Flyable>			observers;

	public Tower() {
		observers = new Vector<Flyable>();
	}

	public void register(Flyable flyable) {
		observers.add(flyable);
	}

	public void unregister(Flyable flyable) {
		observers.removeElement(flyable);
		flyable = null;
	}

	protected void conditionsChanged() {

	}
}
