package tests.algorithms.monotonePartitioning.wrappers;

import algorithms.monotonePartitioning.wrappers.VertexDCEL2dWrapper;
import algorithms.monotonePartitioning.wrappers.Vtx2dWrap_Comparator;
import dcel.dim2.VertexDCEL2d;
import org.testng.annotations.Test;
import vector_points.points.Point2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestVtx2dWrap_Comparator {

    @Test
    public void testSortVertices_only_Y () throws NoSuchMethodException {

        Point2d a = new Point2d(0,3);
        Point2d b = new Point2d(0,-1);
        Point2d c = new Point2d(0,1);
        Point2d d = new Point2d(0,2);

        VertexDCEL2dWrapper vtxWr_a = new VertexDCEL2dWrapper(new VertexDCEL2d(a),null);
        VertexDCEL2dWrapper vtxWr_b = new VertexDCEL2dWrapper(new VertexDCEL2d(b),null);
        VertexDCEL2dWrapper vtxWr_c = new VertexDCEL2dWrapper(new VertexDCEL2d(c),null);
        VertexDCEL2dWrapper vtxWr_d = new VertexDCEL2dWrapper(new VertexDCEL2d(d),null);

        List<VertexDCEL2dWrapper> vertices = new ArrayList<>(Arrays.asList(vtxWr_a,vtxWr_b,vtxWr_c,vtxWr_d));
        vertices.sort(new Vtx2dWrap_Comparator());
        vertices.forEach(v -> System.out.println(v.getVtx().getName()));

    }

    @Test
    public void testSortVertices_same_Y_different_X () throws NoSuchMethodException {

        Point2d a = new Point2d(1,0);
        Point2d b = new Point2d(-1,0);
        Point2d c = new Point2d(2,0);
        Point2d d = new Point2d(0,0);

        VertexDCEL2dWrapper vtxWr_a = new VertexDCEL2dWrapper(new VertexDCEL2d(a),null);
        VertexDCEL2dWrapper vtxWr_b = new VertexDCEL2dWrapper(new VertexDCEL2d(b),null);
        VertexDCEL2dWrapper vtxWr_c = new VertexDCEL2dWrapper(new VertexDCEL2d(c),null);
        VertexDCEL2dWrapper vtxWr_d = new VertexDCEL2dWrapper(new VertexDCEL2d(d),null);

        List<VertexDCEL2dWrapper> vertices = new ArrayList<>(Arrays.asList(vtxWr_a,vtxWr_b,vtxWr_c,vtxWr_d));
        vertices.sort(new Vtx2dWrap_Comparator());
        vertices.forEach(v -> System.out.println(v.getVtx().getName()));

    }


}
