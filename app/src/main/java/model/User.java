package model;
import java.util.ArrayList;
public class User {
    private int id;
    private String email;
    private String name;
    private String password;
    private String photo;
    private int points;
    private int couple_id;
    private ArrayList<Operation> operation;
    public User() {
    }
    public User(int id, String email, String name, String password, String photo, int points, int couple_id, ArrayList<Operation> operation) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.photo = photo;
        this.points = points;
        this.couple_id = couple_id;
        this.operation = operation;
    }
    public void addOperation(Operation operation){
        this.operation.add(operation);

    }
    public int getCouple_id() {
        return couple_id;
    }
    public void setCouple_id(int couple_id) {
        this.couple_id = couple_id;
    }
    public ArrayList<Operation> getOperation() {
        return operation;
    }
    public void setOperation(ArrayList<Operation> operation) {
        this.operation = operation;
    }
    //Constructor to create/invite couple to registration
    public User(int id, String email){
        this.id = id;
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    @Override
    public String toString() {
        return name;
    }
}
