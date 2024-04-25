package model;

public enum OperationType {
    GOOD_POINTS ("Give good points"),
    BAD_POINTS ("Give bad points"),
    REDEEM("Redeem points"),
    DEFAULT("Default points");


    private String description;

    OperationType(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
