package model.services.impl;

import model.dao.DAOFactory;
import model.entities.Ride;
import model.entities.Special;
import model.services.SpecialService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImplSpecialService implements SpecialService {
    private static final Logger LOGGER = LogManager.getLogger(ImplSpecialService.class);
    private static volatile ImplSpecialService instance;
    private ImplSpecialService() {
    }

    public static ImplSpecialService getInstance(){
        if(instance == null){
            synchronized (ImplSpecialService.class){
                if(instance == null){
                    instance = new ImplSpecialService();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Special> getApplicableSpecials(Ride ride) {
        LOGGER.info("Getting specials applicable for the ride");
        return DAOFactory.getInstance().getSpecialDAO().findAll().stream()
                .filter(s->s.getSpecialType().getCheckSpecialApplicable().execute(s.getRule(),ride.getUser()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
