package tests.vector_points.vectors;

import org.testng.Assert;
import org.testng.annotations.Test;
import vector_points.vectors.Vector2d;

public class TestVector {

    @Test
    public void testSum () throws NoSuchMethodException {
        Vector2d v = new Vector2d(1,1);
        Vector2d w  = new Vector2d(2,2 );
        Vector2d s = v.sum(w);
        Vector2d expected = new Vector2d(3,3);
        Assert.assertEquals(s, expected);
    }


}
