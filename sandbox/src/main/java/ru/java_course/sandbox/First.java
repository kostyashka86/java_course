package ru.java_course.sandbox;

public class First {

    public static void main (String[] args) {

        Point p1 = new Point(0,0);
        Point p2 = new Point(-30,-40);

        System.out.println("Расстояние между точками  = " + p1.distance(p2));
        System.out.println("Расстояние до точки 6, 8  = " + p1.distance(6,8));
    }

}