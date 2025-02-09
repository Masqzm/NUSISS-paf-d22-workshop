package paf.day22.model;

import java.sql.Date;

public class Rsvp {
    private int id;
    private String name;
    private String email; 
    private String phone;
    private Date confirmation_date;
    private String comments;

    public Rsvp() {}
    public Rsvp(int id, String name, String email, String phone, Date confirmation_date, String comments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.confirmation_date = confirmation_date;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Rsvp [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", confirmation_date="
                + confirmation_date + ", comments=" + comments + "]";
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getConfirmation_date() {
        return confirmation_date;
    }
    public void setConfirmation_date(Date confirmation_date) {
        this.confirmation_date = confirmation_date;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
}
