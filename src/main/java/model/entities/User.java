package model.entities;

import model.entities.prop.Role;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private String email;
    private String firstNameEn;
    private String firstNameUa;
    private String lastNameEn;
    private String lastNameUa;
    private List<Ride> ridesHistory;
    private Role role;
    private int personalDiscount;
    private int additionalDiscount;
    private int moneySpent;
    private int ridesCount;

    public User() {
        this.moneySpent=0;
        this.personalDiscount=0;
        this.additionalDiscount=0;
        this.ridesCount=0;
    }

    public User(int id, String login, String password, String email, String firstNameEn, String firstNameUa, String lastNameEn, String lastNameUa, List<Ride> ridesHistory, Role role, int personalDiscount, int additionalDiscount, int moneySpent) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstNameEn = firstNameEn;
        this.firstNameUa = firstNameUa;
        this.lastNameEn = lastNameEn;
        this.lastNameUa = lastNameUa;
        this.ridesHistory = ridesHistory;
        this.role = role;
        this.personalDiscount = personalDiscount;
        this.additionalDiscount = additionalDiscount;
        this.moneySpent = moneySpent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getPersonalDiscount() {
        return personalDiscount;
    }

    public void setPersonalDiscount(int personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public int getAdditionalDiscount() {
        return additionalDiscount;
    }

    public void setAdditionalDiscount(int additionalDiscount) {
        this.additionalDiscount = additionalDiscount;
    }

    public int getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(int moneySpent) {
        this.moneySpent = moneySpent;
    }

    public int getRidesCount() {
        return ridesCount;
    }

    public void setRidesCount(int ridesCount) {
        this.ridesCount = ridesCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstNameEn, user.firstNameEn) &&
                Objects.equals(firstNameUa, user.firstNameUa) &&
                Objects.equals(lastNameEn, user.lastNameEn) &&
                Objects.equals(lastNameUa, user.lastNameUa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, firstNameEn, firstNameUa, lastNameEn, lastNameUa);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstNameEn='" + firstNameEn + '\'' +
                ", firstNameUa='" + firstNameUa + '\'' +
                ", lastNameEn='" + lastNameEn + '\'' +
                ", lastNameUa='" + lastNameUa + '\'' +
                //", ridesHistory=" + ridesHistory +
                ", role=" + role +
                ", personalDiscount=" + personalDiscount +
                ", additionalDiscount=" + additionalDiscount +
                ", moneySpent=" + moneySpent +
                ", ridesCount=" + ridesCount +
                '}';
    }
}
