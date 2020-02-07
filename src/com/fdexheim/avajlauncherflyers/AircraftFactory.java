package src.com.fdexheim.avajlauncherflyers;

import src.com.fdexheim.avajlauncherexceptions.BadAircraftTypeException;
import src.com.fdexheim.Coordinates;

public class AircraftFactory {
	public static Flyable 		newAircraft(String type, String name, int longitude, int latitude, int height) throws BadAircraftTypeException {
		Flyable			ret;

		ret = null;
		switch (type) {
			case "Helicopter" :
				ret = new Helicopter(name, new Coordinates(longitude, latitude, height));
				break ;
			case "JetPlane" :
				ret = new JetPlane(name, new Coordinates(longitude, latitude, height));
				break ;
			case "Baloon" :
				ret = new Baloon(name, new Coordinates(longitude, latitude, height));
				break ;
			default :
				throw new BadAircraftTypeException("Unknown Aircraft type");
		}
		return (ret);
	}
}
