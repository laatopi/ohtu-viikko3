/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;

/**
 *
 * @author laatopi
 */
class Course {

    private String name;
    private String term;
    private ArrayList<Integer> exercises;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setExercises(ArrayList<Integer> exercises) {
        this.exercises = exercises;
    }

    public ArrayList getExercises() {
        return this.exercises;
    }

    @Override
    public String toString() {
        return "Kurssi: " + this.name + ", " + getTerm();
    }
}
