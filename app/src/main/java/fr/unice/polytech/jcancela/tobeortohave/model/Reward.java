package fr.unice.polytech.jcancela.tobeortohave.model;

/**
 * Created by Joel CANCELA VAZ on 28/05/2017.
 */

public class Reward {


    private int pointsToReach;
    private String name;
    private String description;

    public Reward(int pointsToReach, String name, String description) {
        this.pointsToReach = pointsToReach;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getPointsToReach() {
        return pointsToReach;
    }
}
