package org.launchcode.Adulting.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Quest {

    // fields
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private int points;

    @ManyToOne
    private Category category;



    // constructors
    public Quest(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public Quest() { }



    // getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
