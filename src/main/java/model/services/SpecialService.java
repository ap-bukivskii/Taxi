package model.services;

import model.entities.Ride;
import model.entities.Special;

import java.util.List;

public interface SpecialService {
    List<Special> getApplicableSpecials(Ride ride);
}
