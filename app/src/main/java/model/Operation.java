package model;
import java.util.Date;
public class Operation {
    private int id;
    private String message;
    private String date;
    private int points;
    private OperationType type;
    private OperationStatus status;
    public Operation() {
    }
    public Operation(int id, String message, String date, int points, OperationType type, OperationStatus status) {
        this.id = id;
        this.message = message;
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

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
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
