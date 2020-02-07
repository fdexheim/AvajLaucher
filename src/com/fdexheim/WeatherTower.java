package src.com.fdexheim;

import src.com.fdexheim.WeatherProvider;

public class WeatherTower extends Tower
{
	public		WeatherTower() {
		super();
	}

	public String		getWeather(Coordinates coordinates) {
		return (WeatherProvider.getCurrentWeather(coordinates));
	}
	protected void		changeWeather() {
		
	}
}
