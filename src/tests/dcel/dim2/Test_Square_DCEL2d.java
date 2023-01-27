package tests.dcel.dim2;

import algorithms.monotonePartitioning.MonotonePartitioning;
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

public class Test_Square_DCEL2d {

    private static Point2d a;
    private static Point2d b;
    private static Point2d c;
    private static Point2d d;

    private static PolygonDCEL2d square;

    static {
        try {
            a = new Point2d(-1,0);
            b = new Point2d(1,0);
            c = new Point2d(1,1);
            d = new Point2d(-1,1);
            square = new PolygonDCEL2d(Arrays.asList(a,b,c,d));
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void test_getEdgesWithSameFaceAndGivenOrigins_square ()  {
        Wrapper<EdgeDCEL2d> edge1_wr = new Wrapper<>(null);
        Wrapper<EdgeDCEL2d> edge2_wr = new Wrapper<>(null);
        square.getEdgesWithSameFaceAndGivenOrigins(square.getVertex(a).get(),square.getVertex(c).get(),edge1_wr,edge2_wr);
        Assert.assertEquals(edge1_wr.getObj(),square.getEdge(0));
        Assert.assertEquals(edge2_wr.getObj(),square.getEdge(4));
    }

    @Test
    public void test_SplitFace () throws InvocationTargetException, InstantiationException, IllegalAccessException {
        square.splitFace(square.getVertex(a).get(),square.getVertex(c).get());
        square.printFaces();
    }

    @Test
    public void test_monotonePartitioning () throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MonotonePartitioning algo  = new MonotonePartitioning();
        algo.getMonotonePolys(square);
        square.printFaces();
    }



}
