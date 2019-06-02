package model.entities.prop.special.impl;

import model.entities.User;
import model.entities.prop.special.CheckSpecialApplicable;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CheckDaysSpecialApplicable implements CheckSpecialApplicable {

    private ResourceBundle regex = ResourceBundle.getBundle("regexps");

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean execute(String rule, User user) {
        return rule.matches(regex.getString("regexp.special.rule.daily"))&&rule.contains(LocalDateTime.now().getDayOfWeek().toString());
    }
}
