package tests.algorithms.triangulation;

import org.testng.Assert;
import org.testng.annotations.Test;
import primitives.edges.Edge2d;
import primitives.poligons.Polygon2d;
import primitives.vertices.Vertex2d;
import algorithms.triangulation.Triangulation;
import vector_points.points.Point2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestTriangulationNonConvex {

    @Test
    public void testNonConvex ( ) throws Exception {

        Vertex2d a = new Vertex2d(new Point2d(0, 0));
        Vertex2d b = new Vertex2d(new Point2d(2, 0));
        Vertex2d c = new Vertex2d(new Point2d(3, 2));
        Vertex2d d = new Vertex2d(new Point2d(1, 1));
        Vertex2d e = new Vertex2d(new Point2d(0, 3));


        a.setPrev(e);
        a.setNext(b);
        b.setPrev(a);
        b.setNext(c);
        c.setPrev(b);
        c.setNext(d);
        d.setPrev(c);
        d.setNext(e);
        e.setPrev(d);
        e.setNext(a);

        List<Vertex2d> vertices = new ArrayList<>(Arrays.asList(a,b,c,d,e));

        Polygon2d square = new Polygon2d(vertices);

        Edge2d ab = new Edge2d(a,b);
        Edge2d bc = new Edge2d(b,c);
        Edge2d cd = new Edge2d(c,d);
        Edge2d de = new Edge2d(d,e);
        Edge2d ea = new Edge2d(e,a);


        List<Edge2d> edges = new ArrayList<>(Arrays.asList(ab,bc,cd,de,ea));


        Triangulation triangulation = new Triangulation();

        triangulation.triangulate_earClipping(square,edges);

        edges.forEach(edge2d -> System.out.println(edge2d.getDetails()));

        Assert.assertTrue(edges.contains(new Edge2d(a,c)));
        Assert.assertTrue(edges.contains(new Edge2d(a,d)));


    }
}
