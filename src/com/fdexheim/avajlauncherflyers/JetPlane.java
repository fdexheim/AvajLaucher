package src.com.fdexheim.avajlauncherflyers;

import src.com.fdexheim.WeatherTower;
import src.com.fdexheim.avajlauncherlog.AvajLauncherLog;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower	weatherTower;

	public					JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
		this.weatherTower = null;
		return ;
	}

	public void				updateConditions() {
		String				weather = weatherTower.getWeather(this.coordinates);
		switch (weather) {
			case "FOG" :
				AvajLauncherLog.getLog().addLog("JetPlane#" + name + "(" + Long.toString(id) + "): We cannot see. Scout, heeeeeeelp !\n");
				this.coordinates.adjustLatitude(1);
				break;
			case "RAIN" :
				AvajLauncherLog.getLog().addLog("JetPlane#" + name + "(" + Long.toString(id) + "): Is sniper emptying his bottles or is it just that the rain is of un unusual color ?\n");
				this.coordinates.adjustLatitude(5);
				break;
			case "SNOW" :
				AvajLauncherLog.getLog().addLog("JetPlane#" + name + "(" + Long.toString(id) + "): Snow ? In summer ? Or is it nuclear winter again ? The dosimeter only says 3.6 roetgens\n");
				this.coordinates.adjustHeight(-7);
				break;
			case "SUN" :
				AvajLauncherLog.getLog().addLog("JetPlane#" + name + "(" + Long.toString(id) + "): Taaaaaste the suuuuuuuuun\n");
				this.coordinates.adjustLatitude(10);
				this.coordinates.adjustHeight(2);
				break;
			default:
				break;
		}
		if (this.coordinates.getHeight() > 100)
			this.coordinates.setHeight(100);
		if (this.coordinates.getHeight() <= 0) {
			AvajLauncherLog.getLog().addLog("Tower says: JetPlane#" + name + "(" + Long.toString(id) + ") unregistered from weather tower.\n");
			this.coordinates.setHeight(0);
			weatherTower.unregister(this);
		}
	
	}

	public void				registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		AvajLauncherLog.getLog().addLog("Tower says: JetPlane#" + name + "(" + Long.toString(id) + ") registered to weather tower.\n");
	}
}
