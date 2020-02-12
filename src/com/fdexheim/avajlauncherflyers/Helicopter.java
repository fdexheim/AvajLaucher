package src.com.fdexheim.avajlauncherflyers;

import src.com.fdexheim.WeatherTower;
import src.com.fdexheim.avajlauncherlog.AvajLauncherLog;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower	weatherTower;

	public					Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
		this.weatherTower = null;
		return ;
	}

	public void				updateConditions() {
		String				weather = weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "FOG" :
				AvajLauncherLog.getLog().addLog("Helicopter#" + name + "(" + Long.toString(id) + "): This visible mass of condensed droplets or frozen crystals suspended in the atmosphere is an impediment to our mission \n");
				this.coordinates.adjustLongitude(1);
				break;
			case "RAIN" :
				AvajLauncherLog.getLog().addLog("Helicopter#" + name + "(" + Long.toString(id) + "): Precipitation of condensed water !\n");
				this.coordinates.adjustLongitude(5);
				break;
			case "SNOW" :
				AvajLauncherLog.getLog().addLog("Helicopter#" + name + "(" + Long.toString(id) + "): Time to play Touhou with the snowflakes !\n");
				this.coordinates.adjustHeight(-12);
				break;
			case "SUN" :
				AvajLauncherLog.getLog().addLog("Helicopter#" + name + "(" + Long.toString(id) + "): Never fly too close to the sun !\n");
				this.coordinates.adjustLongitude(10);
				this.coordinates.adjustHeight(2);
				break;
			default:
				break;
		}
		if (this.coordinates.getHeight() > 100)
			this.coordinates.setHeight(100);
		if (this.coordinates.getHeight() <= 0) {
			AvajLauncherLog.getLog().addLog("Helicopter#" + name + "(" + Long.toString(id) + "): What goes up... must come down.\n");
			AvajLauncherLog.getLog().addLog("Tower says: Helicopter#" + name + "(" + Long.toString(id) + ") unregistered from weather tower.\n");
			this.coordinates.setHeight(0);
			weatherTower.unregister(this);
		}
	
	}

	public void				registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		AvajLauncherLog.getLog().addLog("Tower says: Helicopter#" + name + "(" + Long.toString(id) + ") registered to weather tower.\n");
	}
}
