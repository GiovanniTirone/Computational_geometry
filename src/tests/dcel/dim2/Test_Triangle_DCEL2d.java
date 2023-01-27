package tests.dcel.dim2;

import dcel.dim2.EdgeDCEL2d;
import dcel.dim2.PolygonDCEL2d;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.utils.Wrapper;
import vector_points.points.Point2d;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Test_Triangle_DCEL2d {

    private static Point2d a;
    private static Point2d b;
    private static Point2d c;
    private static PolygonDCEL2d triangle;

    static {
        try {
            a = new Point2d(-1,0);
            b = new Point2d(1,0);
            c = new Point2d(0,1);
            triangle = new PolygonDCEL2d(Arrays.asList(a,b,c));
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testConstructor_triangle () throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        triangle.printVertices();
        triangle.printEdges();
        triangle.printFaces();
    }


    @Test
    public static void testNextEdge_even () {
        Assert.assertEquals(triangle.getEdgeList().get(0).getNext(), triangle.getEdgeList().get(2));
        Assert.assertEquals(triangle.getEdgeList().get(2).getNext(),triangle.getEdgeList().get(4));
        Assert.assertEquals(triangle.getEdgeList().get(4).getNext(),triangle.getEdgeList().get(0));
    }

    @Test
    public static void testNextEdge_odd () {
        Assert.assertEquals(triangle.getEdgeList().get(1).getNext(), triangle.getEdgeList().get(5));
        Assert.assertEquals(triangle.getEdgeList().get(5).getNext(),triangle.getEdgeList().get(3));
        Assert.assertEquals(triangle.getEdgeList().get(3).getNext(),triangle.getEdgeList().get(1));
    }

    @Test
    public static void testPrevEdge_even () {
        Assert.assertEquals(triangle.getEdgeList().get(0).getPrev(), triangle.getEdgeList().get(4));
        Assert.assertEquals(triangle.getEdgeList().get(4).getPrev(),triangle.getEdgeList().get(2));
        Assert.assertEquals(triangle.getEdgeList().get(2).getPrev(),triangle.getEdgeList().get(0));
    }

    @Test
    public static void testPrevEdge_odd () {
        Assert.assertEquals(triangle.getEdgeList().get(1).getPrev(), triangle.getEdgeList().get(3));
        Assert.assertEquals(triangle.getEdgeList().get(3).getPrev(),triangle.getEdgeList().get(5));
        Assert.assertEquals(triangle.getEdgeList().get(5).getPrev(),triangle.getEdgeList().get(1));
    }

    @Test
    public static void testTwins (){
        Assert.assertEquals(triangle.getEdge(0).getTwin(),triangle.getEdge(1));
        Assert.assertEquals(triangle.getEdge(2).getTwin(),triangle.getEdge(3));
        Assert.assertEquals(triangle.getEdge(4).getTwin(),triangle.getEdge(5));
        Assert.assertEquals(triangle.getEdge(1).getTwin(),triangle.getEdge(0));
        Assert.assertEquals(triangle.getEdge(3).getTwin(),triangle.getEdge(2));
        Assert.assertEquals(triangle.getEdge(5).getTwin(),triangle.getEdge(4));
    }


    @Test
    public void test_getEdgesWithSameFaceAndGivenOrigins () {
        Wrapper<EdgeDCEL2d> edge1_wr = new Wrapper<>(null);
        Wrapper<EdgeDCEL2d> edge2_wr = new Wrapper<>(null);
        triangle.getEdgesWithSameFaceAndGivenOrigins(
                triangle.getVertex(a).get(),
                triangle.getVertex(c).get(),
                edge1_wr,
                edge2_wr  );
        Assert.assertEquals(edge1_wr.getObj(),triangle.getEdge(0));
        Assert.assertEquals(edge2_wr.getObj(),triangle.getEdge(4));
    }



}
