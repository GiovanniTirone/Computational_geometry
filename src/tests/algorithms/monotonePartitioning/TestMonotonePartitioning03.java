package tests.algorithms.monotonePartitioning;

import algorithms.monotonePartitioning.MonotonePartitioning;
import dcel.dim2.PolygonDCEL2d;
import vector_points.points.Point2d;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class TestMonotonePartitioning03 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Point2d a = new Point2d(0, -3);
        Point2d b = new Point2d(2, -2);
        Point2d c = new Point2d(3, 0);
        Point2d d = new Point2d(1, 5);
        Point2d e = new Point2d(0, 1);
        Point2d f = new Point2d(-1, 5);
        Point2d g = new Point2d(-4, -1);
        Point2d h = new Point2d(-3, -3);
        Point2d i = new Point2d(-2, -2);

        PolygonDCEL2d poly = new PolygonDCEL2d(Arrays.asList(a,b,c,d,e,f,g,h,i));
        MonotonePartitioning algo = new MonotonePartitioning();
        List<PolygonDCEL2d> partition =  algo.getMonotonePolys(poly);
        poly.printFaces();

        System.out.println(" ---------------------- ");

        int k = 1;
        for(PolygonDCEL2d pol : partition){
            System.out.println("Polygon " + k++);
            pol.printVertices();
        }
    }


}
