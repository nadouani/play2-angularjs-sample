package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer extends Model{

    @Id
    public Long id;
    public String name;

    public static Finder<Long, Customer> find = new Finder<Long, Customer>(Long.class, Customer.class);

}
