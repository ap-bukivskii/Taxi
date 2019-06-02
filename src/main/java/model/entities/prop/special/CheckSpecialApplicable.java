package model.entities.prop.special;

import model.entities.User;

public interface CheckSpecialApplicable {

    /**
     * Method for checking if the {@link model.entities.Special} is applicable for the ride
     * @param rule determines the according checking rule
     * @param user for individual user specials
     * @return true if the special is applicable to the {@code user} regarding the {@code rule}
     */
    boolean execute (String rule, User user);
}
