package tests.triangulation;

import primitives.edges.Edge2d;
import primitives.poligons.Polygon2d;
import primitives.vertices.Vertex2d;
import triangulation.Triangulation;
import vector_points.points.Point2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestTriangulation {


    public static void main(String[] args) throws Exception {


        Vertex2d a = new Vertex2d(new Point2d(0, 0));
        Vertex2d b = new Vertex2d(new Point2d(1, 0));
        Vertex2d c = new Vertex2d(new Point2d(1, 1));
        Vertex2d d = new Vertex2d(new Point2d(0, 1));
        a.setName("a");
        b.setName("b");
        c.setName("c");
        d.setName("d");

        a.setPrev(d);
        a.setNext(b);
        b.setPrev(a);
        b.setNext(c);
        c.setPrev(b);
        c.setNext(d);
        d.setPrev(c);
        d.setNext(a);

        List<Vertex2d> vertices = new ArrayList<>(Arrays.asList(a,b,c,d));

        Polygon2d square = new Polygon2d(vertices);

        Edge2d ab = new Edge2d(a,b);
        Edge2d bc = new Edge2d(b,c);
        Edge2d cd = new Edge2d(c,d);
        Edge2d da = new Edge2d(d,a);


        List<Edge2d> edges = new ArrayList<>(Arrays.asList(ab,bc,cd,da));


        Triangulation triangulation = new Triangulation();

        triangulation.triangulate_earClipping(square,edges);

        edges.forEach(edge2d -> System.out.println(edge2d.getDetails()));


        }
}

