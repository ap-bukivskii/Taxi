package model.entities.prop.special.impl;

import model.entities.User;
import model.entities.prop.special.CheckSpecialApplicable;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CheckHoursSpecialApplicable implements CheckSpecialApplicable {
    private ResourceBundle regex = ResourceBundle.getBundle("regexps");


    /**
     *{@inheritDoc}
     */
    @Override
    public boolean execute(String rule, User user) {
        return rule.matches(regex.getString("regexp.special.rule.hourly"))
                &&Integer.parseInt(rule.split(",")[0]) <= LocalDateTime.now().getHour()
                &&Integer.parseInt(rule.split(",")[1]) >  LocalDateTime.now().getHour();
    }
}
