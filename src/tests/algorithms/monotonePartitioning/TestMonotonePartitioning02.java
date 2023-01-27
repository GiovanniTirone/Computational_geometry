package tests.algorithms.monotonePartitioning;

import algorithms.monotonePartitioning.MonotonePartitioning;
import dcel.dim2.PolygonDCEL2d;
import vector_points.points.Point2d;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMonotonePartitioning02 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {




        Point2d a = new Point2d(0,0);
        Point2d b = new Point2d(1,-2);
        Point2d c = new Point2d(2,0);
        Point2d d = new Point2d(2,2);
        Point2d e = new Point2d(-2,2);
        Point2d f = new Point2d(-2,0);
        Point2d g = new Point2d(-1,-2);


        List<Point2d> points = new ArrayList<>(Arrays.asList(a, b, c, d, e,f,g));

        PolygonDCEL2d poly = new PolygonDCEL2d(points);

        MonotonePartitioning algo = new MonotonePartitioning();

        List<PolygonDCEL2d> partition = algo.getMonotonePolys(poly);


        System.out.println(partition.size());

        int i = 1;
        for(PolygonDCEL2d pol : partition){
            System.out.println("Polygon " + i++);
            pol.printVertices();
        }

    }

}
