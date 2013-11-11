package models;


import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends Model{

    @Id
    public Long id;

    public String username;
    public String password;

    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);

    public static User authenticate(String username, String password) {
        return find.where()
                .eq("username", username)
                .eq("password", password)
                .findUnique();
    }

    public static User byUsername(String username){
        return find.where()
                .eq("username", username)
                .findUnique();
    }
}
