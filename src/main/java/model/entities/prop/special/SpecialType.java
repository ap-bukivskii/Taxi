package model.entities.prop.special;

import model.entities.prop.special.impl.CheckDaysSpecialApplicable;
import model.entities.prop.special.impl.CheckHoursSpecialApplicable;
import model.entities.prop.special.impl.CheckRideCountSpecialApplicable;

public enum SpecialType {
    WEEK_DAYS("week_days", new CheckDaysSpecialApplicable()),
    DAY_HOURS("day_hours", new CheckHoursSpecialApplicable()),
    RIDE_COUNT("ride_count", new CheckRideCountSpecialApplicable());

    SpecialType(String name, CheckSpecialApplicable appliable) {
        this.name = name;
        this.checkSpecialApplicable = appliable;
    }

    private String name;
    private CheckSpecialApplicable checkSpecialApplicable;

    public String getName() {
        return name;
    }

    public CheckSpecialApplicable getCheckSpecialApplicable() {
        return checkSpecialApplicable;
    }
}
