package model;

import java.util.Date;

public class Operation {
    private int id;
    private String message;
    private int ownerId;
    private Date date;
    private int points;
    private OperationType type;
    private OperationStatus status;

    public Operation(int id, String message, int ownerId, Date date, int points, OperationType type, OperationStatus status) {
        this.id = id;
        this.message = message;
        this.ownerId = ownerId;
        this.date = date;
        this.points = points;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }


}
