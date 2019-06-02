package model.entities.prop;

public enum CarType {

    SEDAN("sedan","m.type.sedan",1),
    WAGON("wagon","m.type.wagon",1.1),
    VAN("van","m.type.van",1.1),
    SUV("suv","m.type.suv",1.3);

    private String name;
    private String message;
    private double costScaler;

    CarType(String name,String message, double costScaler) {
        this.name = name;
        this.message = message;
        this.costScaler = costScaler;
    }

    public String getName() {
        return name;
    }
    public double getCostScaler() {
        return costScaler;
    }

    public String getMessage() {
        return message;
    }
}
