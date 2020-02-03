package src.com.fdexheim.avajlauncherflyers;

import src.com.fdexheim.WeatherTower;
import src.com.fdexheim.Coordinates;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower		weatherTower;

	public			Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
		this.weatherTower = null;
		return ;
	}

	public void		updateConditions() {
		
	}

	public void		registerTower(WeatherTower weatherTower) {
		weatherTower.register(this);
		this.weatherTower = weatherTower;
		// log !
	}
}
