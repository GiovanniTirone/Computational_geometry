package tests.vector_points.points;

import org.testng.Assert;
import org.testng.annotations.Test;
import vector_points.points.Point2d;
import vector_points.vectors.Vector2d;

public class TestPoint {

    @Test
    public void testApply ( ) throws NoSuchMethodException {
        Point2d p = new Point2d(1,1);
        Vector2d v = new Vector2d(2,2);
        Point2d result = p.apply(v);
        Point2d expected = new Point2d(3,3);
        Assert.assertEquals(result,expected);
    }

    @Test
    public void testVectorTo () throws NoSuchMethodException {
        Point2d p = new Point2d(1,1);
        Point2d q = new Point2d(3,3);
        Vector2d result = p.vectorTo(q);
        System.out.println(result.getDetails());
        Vector2d expected = new Vector2d(2,2);
        Assert.assertEquals(result,expected);
    }

}
