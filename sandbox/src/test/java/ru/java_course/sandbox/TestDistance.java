package ru.java_course.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDistance {

    @Test
    public void testDistance(){
        Point p1 = new Point(0, 0);
        Point p2 = new Point(40, 30);
        Assert.assertEquals(p1.distance(p2), 50);
    }

}
