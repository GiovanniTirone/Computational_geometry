package tests.algorithms.monotonePartitioning;

import algorithms.monotonePartitioning.SweepLine;
import algorithms.monotonePartitioning.SweepLineComparator;
import algorithms.monotonePartitioning.wrappers.EdgeDCEL2dWrapper;
import dcel.dim2.EdgeDCEL2d;
import dcel.dim2.VertexDCEL2d;
import org.testng.annotations.Test;
import vector_points.points.Point2d;

import java.util.TreeSet;

public class TestSweepLine {

    @Test
    public void testRemove () throws NoSuchMethodException {
        SweepLine sweepLine = new SweepLine(new Point2d(0,0));
        EdgeDCEL2d ed = new EdgeDCEL2d(new VertexDCEL2d(new Point2d(2,3)));
        EdgeDCEL2d ed_twin = new EdgeDCEL2d(new VertexDCEL2d(new Point2d(4,4)));
        ed.setTwin(ed_twin);
        ed_twin.setTwin(ed);
        EdgeDCEL2dWrapper edWr = new EdgeDCEL2dWrapper(ed,null);
        sweepLine.add(edWr);
        sweepLine.forEach(e -> System.out.println(e.getEdge().getName()));
        sweepLine.remove(edWr);
        if(sweepLine.isEmpty()) System.out.println("the sweepLine is empty");
        else sweepLine.forEach(e -> System.out.println(e.getEdge().getName()));
    }


}
