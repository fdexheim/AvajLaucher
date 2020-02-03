package src.com.fdexheim.avajlauncherflyers;

import src.com.fdexheim.WeatherTower;

public interface Flyable {
	void		updateConditions();
	void		registerTower(WeatherTower WeatherTower);
}
