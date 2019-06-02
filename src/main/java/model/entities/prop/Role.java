package model.entities.prop;

public enum Role {
    UNAUTHORIZED("unauthorized"),
    USER("user"),
    ADMIN("admin");

    private String name;

    Role (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
