package src.com.fdexheim;

import java.util.Random;
import src.com.fdexheim.avajlauncherflyers.Coordinates;

public class					WeatherProvider
{
	private WeatherProvider		weatherProvider = new WeatherProvider();
	private static String[]		weather = { "FOG", "RAIN", "SNOW", "SUN" };
	static Random				rngesus = new Random();
	static int					seedLongitude;
	static int					seedLatitude;
	static int					seedHeight;

	private						WeatherProvider() {
		weatherProvider = this;
		this.changeWeather();
	}

	static void						changeWeather() {	
		seedLongitude = rngesus.nextInt(12345);
		seedLatitude = rngesus.nextInt(12345);
		seedHeight = rngesus.nextInt(12345);
	}

	public WeatherProvider		getProvider() {
		return (weatherProvider);
	}

	public static String				getCurrentWeather(Coordinates coordinates) {
		int						rng;

		rng = coordinates.getLongitude() * seedLongitude + coordinates.getLatitude() * seedLatitude + coordinates.getHeight() * seedHeight;
		rng = rng % 4;
		if (rng < 0)
			rng = rng * -1;
		return (weather[rng]);
	}
}
