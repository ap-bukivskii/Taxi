package model.entities.prop.special.impl;

import model.entities.User;
import model.entities.prop.special.CheckSpecialApplicable;

import java.util.ResourceBundle;

public class CheckRideCountSpecialApplicable implements CheckSpecialApplicable {
    private ResourceBundle regex = ResourceBundle.getBundle("regexps");

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean execute(String rule, User user) {
        return rule.matches(regex.getString("regexp.special.rule.counts"))
               // && user.getRidesCount()!=0
                && user.getRidesCount()%Integer.parseInt(rule)==0;
    }
}
