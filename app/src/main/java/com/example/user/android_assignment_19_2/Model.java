package com.example.user.android_assignment_19_2;

/**
 * Created by User on 02-02-2018.
 */

public class Model {
    //public is a method and fields can be accessed by the members of any class.
    //class is a collections of objects.

    String name,Votes,id;
    public Model(String name, String votes, String id) {
        // creating parameterized  constructor
        this.name = name;
        Votes = votes;
        this.id = id;
    }
    public Model() {
        //creating a default constructor
    }
    public String getName() {
        return name;
    }
    //it will get the name
    public void setName(String name) {
        this.name = name;
    }
    //it will set the name
    public String getVotes() {
        return Votes;
    }
    //it will get the votes
    public void setVotes(String votes) {
        Votes = votes;
    }
    //it will set the votes
    public String getId() {
        return id;
    }
    //it will get the id
    public void setId(String id) {
        this.id = id;
    }
    //it will set the id
}
