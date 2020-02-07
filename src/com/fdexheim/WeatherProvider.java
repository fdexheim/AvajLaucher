package src.com.fdexheim;

public class					WeatherProvider
{
	private WeatherProvider		weatherProvider;
	private static String[]		weather = { "FOG", "RAIN", "SNOW", "SUN" };

	private						WeatherProvider() {

	}

	public WeatherProvider		getProvider() {
		return (weatherProvider);
	}

	public static String				getCurrentWeather(Coordinates coordinates) {
		int						rng;
	//	int						seed = 123456;

	//	rng = (coordinates.getLongitude() * seed + coordinates.getLongitude()) % coordinates.getHeight(); BAD !
	//	rng = rng % 4;
		rng = 0;
		System.out.print("WeatherProvider:getCurrentWeather called, returning : " + weather[rng]);
		return (weather[rng]);
	}
}
