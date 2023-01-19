package tests.utils;

import org.testng.Assert;
import org.testng.annotations.Test;
import primitives.lines.Line2d;
import vector_points.points.Point2d;
import vector_points.vectors.Vector2d;

import static utils.Angle.angleLines;

public class AngleTest {

    @Test
    public void testAngleLines2d () throws NoSuchMethodException {
        Point2d l1_origin = new Point2d(0,3);
        Vector2d l1_dir = new Vector2d(2,1);
        Point2d l2_origin = new Point2d(0,5);
        Vector2d l2_dir = new Vector2d(-2,1);
        Line2d l1 = new Line2d(l1_origin,l1_dir);
        Line2d l2 = new Line2d(l2_origin,l2_dir);
        Float angle = angleLines(l1, l2);
        System.out.println(angle);
        Assert.assertEquals(angle.intValue(),53);
    }
}
