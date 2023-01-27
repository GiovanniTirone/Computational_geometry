package tests.algorithms.monotonePartitioning;

import algorithms.monotonePartitioning.SweepLine;
import algorithms.monotonePartitioning.wrappers.EdgeDCEL2dWrapper;
import dcel.dim2.EdgeDCEL2d;
import dcel.dim2.VertexDCEL2d;
import dcel.general.EdgeDCEL;
import org.testng.annotations.Test;
import vector_points.points.Point2d;

public class TestSweepLineComparator {

    @Test
    public void testComparator_01 ( ) throws NoSuchMethodException {

        Point2d point = new Point2d(0,0);

        Point2d o1 = new Point2d(-1,-1);
        Point2d d1 = new Point2d(-1,1);
        EdgeDCEL2d e1 = new EdgeDCEL2d(new VertexDCEL2d(o1));
        EdgeDCEL2d e1_twin = new EdgeDCEL2d(new VertexDCEL2d(d1));
        e1.setTwin(e1_twin);
        e1_twin.setTwin(e1);
        EdgeDCEL2dWrapper e1_wr = new EdgeDCEL2dWrapper(e1,null);

        Point2d o2 = new Point2d(1,-1);
        Point2d d2 = new Point2d(1,1);
        EdgeDCEL2d e2 = new EdgeDCEL2d(new VertexDCEL2d(o2));
        EdgeDCEL2d e2_twin = new EdgeDCEL2d(new VertexDCEL2d(d2));
        e2.setTwin(e2_twin);
        e2_twin.setTwin(e2);
        EdgeDCEL2dWrapper e2_wr = new EdgeDCEL2dWrapper(e2,null);


        SweepLine sweepLine = new SweepLine(point);

        sweepLine.add(e1_wr);
        sweepLine.add(e2_wr);

        sweepLine.forEach(e -> System.out.println(e.getEdge().getName()));
    }


    @Test
    public void testComparator_02 ( ) throws NoSuchMethodException {

        Point2d point = new Point2d(0,0);

        Point2d o1 = new Point2d(0,0);
        Point2d d1 = new Point2d(1,1);
        EdgeDCEL2d e1 = new EdgeDCEL2d(new VertexDCEL2d(o1));
        EdgeDCEL2d e1_twin = new EdgeDCEL2d(new VertexDCEL2d(d1));
        e1.setTwin(e1_twin);
        e1_twin.setTwin(e1);
        EdgeDCEL2dWrapper e1_wr = new EdgeDCEL2dWrapper(e1,null);

        Point2d o2 = new Point2d(0,0);
        Point2d d2 = new Point2d(2,-1);
        EdgeDCEL2d e2 = new EdgeDCEL2d(new VertexDCEL2d(o2));
        EdgeDCEL2d e2_twin = new EdgeDCEL2d(new VertexDCEL2d(d2));
        e2.setTwin(e2_twin);
        e2_twin.setTwin(e2);
        EdgeDCEL2dWrapper e2_wr = new EdgeDCEL2dWrapper(e2,null);


        SweepLine sweepLine = new SweepLine(point);

        sweepLine.add(e1_wr);
        sweepLine.add(e2_wr);

        sweepLine.forEach(e -> System.out.println(e.getEdge().getName()));
    }


    @Test
    public void testComparator_03 ( ) throws NoSuchMethodException {

        Point2d point = new Point2d(0,0);

        Point2d o1 = new Point2d(0,0);
        Point2d d1 = new Point2d(1,1);
        EdgeDCEL2d e1 = new EdgeDCEL2d(new VertexDCEL2d(o1));
        EdgeDCEL2d e1_twin = new EdgeDCEL2d(new VertexDCEL2d(d1));
        e1.setTwin(e1_twin);
        e1_twin.setTwin(e1);
        EdgeDCEL2dWrapper e1_wr = new EdgeDCEL2dWrapper(e1,null);

        Point2d o2 = new Point2d(0,0);
        Point2d d2 = new Point2d(0.5f,-1);
        EdgeDCEL2d e2 = new EdgeDCEL2d(new VertexDCEL2d(o2));
        EdgeDCEL2d e2_twin = new EdgeDCEL2d(new VertexDCEL2d(d2));
        e2.setTwin(e2_twin);
        e2_twin.setTwin(e2);
        EdgeDCEL2dWrapper e2_wr = new EdgeDCEL2dWrapper(e2,null);


        SweepLine sweepLine = new SweepLine(point);

        sweepLine.add(e1_wr);
        sweepLine.add(e2_wr);

        sweepLine.forEach(e -> System.out.println(e.getEdge().getName()));
    }

}
