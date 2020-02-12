package ru.java_course.sandbox;


public class Point{

    public double x, y;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public  double distance(double x, double y) {

        return Math.sqrt((this.x - x) * (this.x - x) + (this.y - y)*(this.y - y));

    }

    public double distance(Point p)
    {
        return distance(p.x, p.y);
    }

}

