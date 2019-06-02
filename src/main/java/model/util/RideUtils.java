package model.util;

import model.entities.Ride;
import model.entities.Special;

import java.util.List;

public class RideUtils {
    public static String generateSQLLinkRS(Ride ride) {
        String SQLRequest;
        List<Special> specials = ride.getSpecials();
        if (specials == null || specials.isEmpty()) { //should never be null but who knows...
            SQLRequest = "";
        } else {
            SQLRequest = "INSERT INTO ride_has_special (ride_idride, special_idspecial) VALUES ";

            for (Special special : specials) {
                SQLRequest = SQLRequest.concat("(?," + special.getId() + "),");
            }

            SQLRequest =SQLRequest.replaceAll(",$", ";"); // not so many specials so concating like this doesn't look too bad
            System.out.println(SQLRequest);
        }
        return SQLRequest;

    }
}