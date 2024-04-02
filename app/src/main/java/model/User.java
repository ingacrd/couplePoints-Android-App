package model;

public class User {
    private int id;
    private String email;
    private String name;
    //    private String password;
    private String photo;
    private int points;

    public User(int id, String email, String name, String photo, int points) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.photo = photo;
        this.points = points;
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
}
