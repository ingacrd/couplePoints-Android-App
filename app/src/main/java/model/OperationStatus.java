package model;

public enum OperationStatus {
    ACCEPTED ("Redemption accepted"),
    REJECTED ("Redemption rejected"),
    REQUESTED("Redemption requested"),
    DONE ("Operation done");
    private String description;

    OperationStatus(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
