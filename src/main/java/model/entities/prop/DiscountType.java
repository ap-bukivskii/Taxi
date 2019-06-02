package model.entities.prop;

public enum DiscountType {

    PERCENT("percent"),
    RAW_COST("raw_cost");

    private String name;

    DiscountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
