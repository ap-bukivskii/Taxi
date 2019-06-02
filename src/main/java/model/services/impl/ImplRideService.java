package model.services.impl;

import model.entities.Ride;
import model.entities.Special;
import model.entities.User;
import model.services.RideService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class ImplRideService implements RideService {
    private static final Logger LOGGER = LogManager.getLogger(ImplRideService.class);

    private static volatile ImplRideService instance;

    private ImplRideService() {
    }

    public static ImplRideService getInstance(){
        if(instance == null){
            synchronized (ImplRideService.class){
                if(instance == null){
                    instance = new ImplRideService();
                }
            }
        }
        return instance;
    }

    @Override
    public int calculateCost(Ride ride) {
        LOGGER.info("Calculate ride cost method");
        double cost = ride.getDistance()*9+50;
        cost *= ride.getDriver().getCarType().getCostScaler();
        cost -= cost*ride.getUser().getPersonalDiscount()/100;
        cost -= cost*ride.getUser().getPersonalDiscount()/100;
        List<Special> specials = ride.getSpecials();
        if(specials != null){
            for (Special s: specials) {
                if(s.getDiscountType().getName().equals("raw_cost")){
                    cost -= s.getDiscountAmount();
                }else{
                    cost -= cost*s.getDiscountAmount()/100;
                }
                System.out.println("cost = "+cost);
            }
        }
        if (cost<0) {cost=0;}
        return (int)cost;
    }


}
