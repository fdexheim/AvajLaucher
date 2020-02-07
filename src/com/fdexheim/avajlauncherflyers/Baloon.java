package src.com.fdexheim.avajlauncherflyers;

import src.com.fdexheim.WeatherTower;
import src.com.fdexheim.Coordinates;
import src.com.fdexheim.avajlauncherlog.AvajLauncherLog;
import src.com.fdexheim.avajlauncherexceptions.BadFileAccessException;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower		weatherTower;

	public			Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
		this.weatherTower = null;
		AvajLauncherLog.getLog().addLog("Baloon#" + name + "(" + Long.toString(id) + "): BALOONU\n");
		return ;
	}

	public void		updateConditions() {
		String		weather = weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "FOG" :
				AvajLauncherLog.getLog().addLog("Baloon#" + name + "(" + Long.toString(id) + "): FOGU\n");
				this.coordinates.adjustHeight(-3);
				break;
			case "RAIN" :
				AvajLauncherLog.getLog().addLog("Baloon#" + name + "(" + Long.toString(id) + "): RAINU\n");
				this.coordinates.adjustHeight(-5);
				break;
			case "SNOW" :
				AvajLauncherLog.getLog().addLog("Baloon#" + name + "(" + Long.toString(id) + "): SNOWU\n");
				this.coordinates.adjustHeight(-15);
				break;
			case "SUN" :
				AvajLauncherLog.getLog().addLog("Baloon#" + name + "(" + Long.toString(id) + "): SUNU\n");
				this.coordinates.adjustLongitude(2);
				this.coordinates.adjustHeight(4);
				break;
			default:
				break;
		}
		if (this.coordinates.getHeight() > 100)
			this.coordinates.setHeight(100);
		if (this.coordinates.getHeight() <= 0) {
			this.coordinates.setHeight(0);
			weatherTower.unregister(this);
		}
	}

	public void		registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		AvajLauncherLog.getLog().addLog("Tower says: Baloon#" + name + "(" + Long.toString(id) + ") registered to weather tower.\n");
	}
}
