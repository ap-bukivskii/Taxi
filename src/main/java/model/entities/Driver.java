package model.entities;

import model.entities.prop.CarType;

import java.util.List;
import java.util.Objects;

public class Driver {
    private int id;
    private String email;
    private String firstNameEn;
    private String firstNameUa;
    private String lastNameEn;
    private String lastNameUa;
    private double rating;
    private int ridesCount;
    private CarType carType;
    private List<Ride> ridesHistory;

    public Driver() {
        this.rating = 0;
        this.ridesCount = 0;
    }

    public Driver(int id, String email, String firstNameEn, String firstNameUa, String lastNameEn, String lastNameUa, int rating, int ridesCount, CarType carType, List<Ride> ridesHistory) {
        this.id = id;
        this.email = email;
        this.firstNameEn = firstNameEn;
        this.firstNameUa = firstNameUa;
        this.lastNameEn = lastNameEn;
        this.lastNameUa = lastNameUa;
        this.rating = rating;
        this.ridesCount = ridesCount;
        this.carType = carType;
        this.ridesHistory = ridesHistory;
    }

    public Driver(String email, String firstNameEn, String firstNameUa, String lastNameEn, String lastNameUa, CarType carType) {
        this.email = email;
        this.firstNameEn = firstNameEn;
        this.firstNameUa = firstNameUa;
        this.lastNameEn = lastNameEn;
        this.lastNameUa = lastNameUa;
        this.carType = carType;
        this.rating = 0;
        this.ridesCount = 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getFirstNameUa() {
        return firstNameUa;
    }

    public void setFirstNameUa(String firstNameUa) {
        this.firstNameUa = firstNameUa;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getLastNameUa() {
        return lastNameUa;
    }

    public void setLastNameUa(String lastNameUa) {
        this.lastNameUa = lastNameUa;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRidesCount() {
        return ridesCount;
    }

    public void setRidesCount(int ridesCount) {
        this.ridesCount = ridesCount;
    }

    public List<Ride> getRidesHistory() {
        return ridesHistory;
    }

    public void setRidesHistory(List<Ride> ridesHistory) {
        this.ridesHistory = ridesHistory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id &&
                rating == driver.rating &&
                ridesCount == driver.ridesCount &&
                Objects.equals(email, driver.email) &&
                Objects.equals(firstNameEn, driver.firstNameEn) &&
                Objects.equals(firstNameUa, driver.firstNameUa) &&
                Objects.equals(lastNameEn, driver.lastNameEn) &&
                Objects.equals(lastNameUa, driver.lastNameUa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstNameEn, firstNameUa, lastNameEn, lastNameUa, rating, ridesCount);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstNameEn='" + firstNameEn + '\'' +
                ", firstNameUa='" + firstNameUa + '\'' +
                ", lastNameEn='" + lastNameEn + '\'' +
                ", lastNameUa='" + lastNameUa + '\'' +
                ", rating=" + rating +
                ", ridesCount=" + ridesCount +
                ", carType=" + carType +
               // ", ridesHistory=" + ridesHistory +
                '}';
    }
}
