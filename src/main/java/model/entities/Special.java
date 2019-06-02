package model.entities;

import model.entities.prop.DiscountType;
import model.entities.prop.special.SpecialType;

import java.util.List;
import java.util.Objects;

public class Special {
    private int id;
    private String nameUa;
    private String nameEn;
    private String descriptionUa; //better to be saved in a file...
    private String descriptionEn;
    private DiscountType discountType;
    private int discountAmount;
    private List<Ride> ridesWithThisSpecial;
    private String rule;
    private SpecialType specialType;
    private boolean active;

    public Special() {
    }

    public Special(int id, String nameUa, String nameEn, String descriptionUa, String descriptionEn, DiscountType discountType, int discountAmount, List<Ride> ridesWithThisSpecial, String rule, SpecialType specialType, boolean active) {
        this.id = id;
        this.nameUa = nameUa;
        this.nameEn = nameEn;
        this.descriptionUa = descriptionUa;
        this.descriptionEn = descriptionEn;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.ridesWithThisSpecial = ridesWithThisSpecial;
        this.rule = rule;
        this.specialType = specialType;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public String getDescriptionUa() {
        return descriptionUa;
    }

    public void setDescriptionUa(String descriptionUa) {
        this.descriptionUa = descriptionUa;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public List<Ride> getRidesWithThisSpecial() {
        return ridesWithThisSpecial;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public SpecialType getSpecialType() {
        return specialType;
    }

    public void setSpecialType(SpecialType specialType) {
        this.specialType = specialType;
    }

    public void setRidesWithThisSpecial(List<Ride> ridesWithThisSpecial) {
        this.ridesWithThisSpecial = ridesWithThisSpecial;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Special special = (Special) o;
        return id == special.id &&
                discountAmount == special.discountAmount &&
                Objects.equals(nameUa, special.nameUa) &&
                Objects.equals(nameEn, special.nameEn) &&
                Objects.equals(descriptionUa, special.descriptionUa) &&
                Objects.equals(descriptionEn, special.descriptionEn) &&
                Objects.equals(rule, special.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameUa, nameEn, descriptionUa, descriptionEn, discountAmount, rule);
    }

    @Override
    public String toString() {
        return "Special{" +
                "id=" + id +
                ", nameUa='" + nameUa + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", descriptionUa='" + descriptionUa + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", discountType=" + discountType +
                ", discountAmount=" + discountAmount +
                ", rule='" + rule + '\'' +
                ", specialType=" + specialType +
                ", active=" + active +
                '}';
    }
}
