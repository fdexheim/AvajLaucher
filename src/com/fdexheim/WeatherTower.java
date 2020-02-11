package src.com.fdexheim;

import src.com.fdexheim.WeatherProvider;
import src.com.fdexheim.avajlauncherflyers.Coordinates;

public class WeatherTower extends Tower
{
	public		WeatherTower() {
		super();
	}

	public String		getWeather(Coordinates coordinates) {
		return (WeatherProvider.getCurrentWeather(coordinates));
	}
	void				changeWeather() {
		WeatherProvider.changeWeather();
	}
}
