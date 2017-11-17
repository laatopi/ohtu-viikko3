package ohtu;

import java.util.ArrayList;

public class Submission {

    private int week;
    private float hours;
    private ArrayList<Integer> exercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public float getHours() {
        return this.hours;
    }

    public void setExercises(ArrayList<Integer> exercises) {
        this.exercises = exercises;
    }

    public ArrayList getExercises() {
        return this.exercises;
    }

    public String toString(String maksimi) {
        return "Viikko " + week + ": Tehtyjä tehtäviä yhteensä: " + getExercises().size()
                + " (maksimi " + maksimi +"), aikaa kului " + hours + " tuntia, tehdyt tehtävät: "
                + this.exercises;
    }

}
