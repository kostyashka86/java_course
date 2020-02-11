package ru.java_course.sandbox;

public class First {

    public static void main (String[] args) {

        Point p1 = new Point(3,4);
        Point p2 = new Point(-3,-4);

        System.out.println("Расстояние между точками  = " + distance(p1,p2));

    }

    public static double distance(Point p1, Point p2) {

        return Math.sqrt((p2.x2 - p1.x1) * (p2.x2 - p1.x1) + (p2.y2 - p1.y1)*(p2.y2 - p1.y1));

    }

}