package model.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Ride {
    private int id;
    private User user;
    private Driver driver;
    private String addressFrom;
    private String addressTo;
    private int distance;
    private int cost;
    private LocalDateTime dateTime;
    private List<Special> specials;

    public Ride() {
    }

    public Ride(int id, User user, Driver driver, String addressFrom, String addressTo, int distance, int cost, LocalDateTime dateTime, List<Special> specials) {
        this.id = id;
        this.user = user;
        this.driver = driver;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.distance = distance;
        this.cost = cost;
        this.dateTime = dateTime;
        this.specials = specials;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Special> getSpecials() {
        return specials;
    }

    public void setSpecials(List<Special> specials) {
        this.specials = specials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ride ride = (Ride) o;
        return id == ride.id &&
                distance == ride.distance &&
                cost == ride.cost &&
                Objects.equals(user, ride.user) &&
                Objects.equals(driver, ride.driver) &&
                Objects.equals(addressFrom, ride.addressFrom) &&
                Objects.equals(addressTo, ride.addressTo) &&
                Objects.equals(dateTime, ride.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, driver, addressFrom, addressTo, distance, cost, dateTime);
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", user=" + user +
                ", driver=" + driver +
                ", addressFrom='" + addressFrom + '\'' +
                ", addressTo='" + addressTo + '\'' +
                ", distance=" + distance +
                ", cost=" + cost +
                ", dateTime=" + dateTime +
                ", specials=" + specials +
                '}';
    }
}
