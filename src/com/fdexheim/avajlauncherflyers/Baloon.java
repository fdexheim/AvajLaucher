package src.com.fdexheim.avajlauncherflyers;

import src.com.fdexheim.WeatherTower;
import src.com.fdexheim.avajlauncherlog.AvajLauncherLog;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower	weatherTower;

	public					Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
		this.weatherTower = null;
		return ;
	}

	public void				updateConditions() {
		String				weather = weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "FOG" :
				AvajLauncherLog.getLog().addLog("Baloon#" + name + "(" + Long.toString(id) + "): OOOOOOOOOOH NOOOOOOOOOO This fog is making ambushes from an enemy [[[STAND]]] very easy\n");
				this.coordinates.adjustHeight(-3);
				break;
			case "RAIN" :
				AvajLauncherLog.getLog().addLog("Baloon#" + name + "(" + Long.toString(id) + "): SONOVABISH this rain is an annoyance we could do without.\n");
				this.coordinates.adjustHeight(-5);
				break;
			case "SNOW" :
				AvajLauncherLog.getLog().addLog("Baloon#" + name + "(" + Long.toString(id) + "): OOOH MY GOOOOOOOOOOD is this snow the work of an enemy [[[STAND]]] ?!\n");
				this.coordinates.adjustHeight(-15);
				break;
			case "SUN" :
				AvajLauncherLog.getLog().addLog("Baloon#" + name + "(" + Long.toString(id) + "): Weather is clear and Sun's high. Watch out for mirrors Jotaro\n");
				this.coordinates.adjustLongitude(2);
				this.coordinates.adjustHeight(4);
				break;
			default:
				break;
		}
		if (this.coordinates.getHeight() > 100)
			this.coordinates.setHeight(100);
		if (this.coordinates.getHeight() <= 0) {
			AvajLauncherLog.getLog().addLog("Baloon#" + name + "(" + Long.toString(id) + "): HORY SHEEET Damn the enemy, we shall finish this on foot\n");
			AvajLauncherLog.getLog().addLog("Tower says: Baloon#" + name + "(" + Long.toString(id) + ") unregistered from weather tower.\n");
			this.coordinates.setHeight(0);
			weatherTower.unregister(this);
		}
	}

	public void				registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		AvajLauncherLog.getLog().addLog("Tower says: Baloon#" + name + "(" + Long.toString(id) + ") registered to weather tower.\n");
	}
}
