package tests.primitives.lines;

import org.testng.Assert;
import org.testng.annotations.Test;
import primitives.lines.Line2d;
import vector_points.points.Point2d;
import vector_points.vectors.Vector2d;


public class TestLineClass {

    @Test
    public void testClasses () throws NoSuchMethodException {
        Line2d l1 = new Line2d(new Point2d(0, 3), new Point2d(2, 1));
        System.out.println(l1.getClass());
        System.out.println(l1.getDir().getClass());
        System.out.println(l1.getOrigin().getClass());
        System.out.println(l1.getEnd().getClass());
    }

    @Test
    public void testGetDir (  ) throws NoSuchMethodException {
        Point2d a = new Point2d(1,3);
        Point2d b = new Point2d(3,4);
        Vector2d AB = a.vectorTo(b);
        Line2d l = new Line2d(a,b);
        Assert.assertEquals(l.getDir(),AB);
    }

    @Test
    public void testGetOriginAndEnd () throws NoSuchMethodException {
        Point2d a = new Point2d(1,3);
        Point2d b = new Point2d(3,4);
        Line2d l = new Line2d(a,b);
        Assert.assertEquals(l.getOrigin(),a);
        System.out.println(l.getEnd().getDetails());
        System.out.println(b.getVector().getDetails());
        Assert.assertEquals(l.getEnd(),b);
    }

}
