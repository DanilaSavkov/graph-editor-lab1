package model.vertecies;

import model.interfaces.Identified;

public class Vertex implements Identified {
    private double x;
    private double y;
    private String identifier;

    /*
     *      constructors
     */

    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /*
     *      getter's and setter's
     */

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
