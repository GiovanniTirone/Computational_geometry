package tests.algorithms.monotonePartitioning;

import algorithms.monotonePartitioning.MonotonePartitioning;
import algorithms.monotonePartitioning.Vtx_category;
import dcel.dim2.EdgeDCEL2d;
import dcel.dim2.VertexDCEL2d;
import org.testng.Assert;
import org.testng.annotations.Test;
import vector_points.points.Point2d;

public class TestVtxCategory {

    @Test
    public void test_90_degree_upper_left () throws NoSuchMethodException {
        VertexDCEL2d c = new VertexDCEL2d(new Point2d(0,-1));
        VertexDCEL2d b = new VertexDCEL2d(new Point2d(0,0));
        VertexDCEL2d a = new VertexDCEL2d(new  Point2d(1,0));

        EdgeDCEL2d ab = new EdgeDCEL2d(a);
        EdgeDCEL2d bc = new EdgeDCEL2d(b);
        EdgeDCEL2d ca = new EdgeDCEL2d(c);

        ab.setNext(bc);
        ab.setPrev(ca);
        bc.setNext(ca);
        bc.setPrev(ab);
        ca.setNext(ab);
        ca.setPrev(bc);

        a.setEdge(ab);
        b.setEdge(bc);
        c.setEdge(ca);


        MonotonePartitioning mp = new MonotonePartitioning();
        Assert.assertEquals(mp.computeCategory(b), Vtx_category.START);

    }

    @Test
    public void test_90_degree_upper_right () throws NoSuchMethodException {
        VertexDCEL2d a = new VertexDCEL2d(new Point2d(0,-1));
        VertexDCEL2d b = new VertexDCEL2d(new Point2d(0,0));
        VertexDCEL2d c = new VertexDCEL2d(new  Point2d(-1,0));

        EdgeDCEL2d ab = new EdgeDCEL2d(a);
        EdgeDCEL2d bc = new EdgeDCEL2d(b);
        EdgeDCEL2d ca = new EdgeDCEL2d(c);

        ab.setNext(bc);
        ab.setPrev(ca);
        bc.setNext(ca);
        bc.setPrev(ab);
        ca.setNext(ab);
        ca.setPrev(bc);

        a.setEdge(ab);
        b.setEdge(bc);
        c.setEdge(ca);


        MonotonePartitioning mp = new MonotonePartitioning();
        Assert.assertEquals(mp.computeCategory(b), Vtx_category.START);
    }


    @Test
    public static void test_equal_y_nextIsRight () {

    }



}
