package tests.primitives.lines;

import org.testng.Assert;
import org.testng.annotations.Test;
import primitives.lines.Line2d;
import utils.Intersection;
import vector_points.points.Point2d;

public class TestLinesIntersection {

    @Test
    public void TestIntersectionMagnitude1 () throws NoSuchMethodException {
        Line2d l1 = new Line2d(new Point2d(-1,0),new Point2d(1,0));
        Line2d l2 = new Line2d(new Point2d(0,-1),new Point2d(0,1));
        boolean result = Intersection.intersectionCheck(l1,l2);
        Point2d point = Intersection.intersectionPoint(l1,l2).get();
        Assert.assertTrue(result);
        Assert.assertEquals(point,new Point2d(0,0));
    }

    @Test
    public void TestIntersectionMagnitute2 ( ) throws NoSuchMethodException {
        Line2d l1 = new Line2d(new Point2d(-1,0),new Point2d(1,0));
        Line2d l2 = new Line2d(new Point2d(0,-2),new Point2d(0,1));
        boolean result = Intersection.intersectionCheck(l1,l2);
        Assert.assertTrue(result);
    }

}
