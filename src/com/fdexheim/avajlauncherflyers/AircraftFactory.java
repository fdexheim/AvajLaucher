package src.com.fdexheim.avajlauncherflyers;

import src.com.fdexheim.Coordinates;

public class AircraftFactory {
	public Flyable 		newAircraft(String type, String name, int longitude, int latitude, int height) {
		Flyable			ret;
		switch (type) {
			case "Helicopter" :
				ret = new Helicopter(name, new Coordinates(longitude, latitude, height));
				break ;
			case "Jetplane" :
				ret = new JetPlane(name, new Coordinates(longitude, latitude, height));
				break ;
			case "Baloon" :
				ret = new Baloon(name, new Coordinates(longitude, latitude, height));
				break ;
			default :
//				throw BadAircraftTypeException();
				break;
		}
		return (ret);
	}
}
