package src.com.fdexheim;

import src.com.fdexheim.avajlauncherflyers.Flyable;
import java.util.ArrayList;

public class Tower
{
	private ArrayList<Flyable>			observers;
	private ArrayList<Flyable>			landingFlyers;

	public Tower() {
		observers = new ArrayList<>();
		landingFlyers = new ArrayList<>();
	}

	public void register(Flyable flyable) {
		observers.add(flyable);
	}

	public void unregister(Flyable flyable) {
		landingFlyers.add(flyable);
	}

	private void			removeLandingFlyers() {
		for (Flyable o : landingFlyers) {
			observers.remove(o);
		}
		landingFlyers.clear();
	}

	protected void			conditionsChanged() {
		for (Flyable o : observers) {
			o.updateConditions();
		}
		removeLandingFlyers();
	}
}
