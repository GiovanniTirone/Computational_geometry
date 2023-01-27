package tests.algorithms.monotonePartitioning;

import algorithms.monotonePartitioning.MonotonePartitioning;
import dcel.dim2.PolygonDCEL2d;
import vector_points.points.Point2d;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class TestMonotonePartitioning05 {


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Point2d a = new Point2d(2, 4);
        Point2d b = new Point2d(6, 2);
        Point2d c = new Point2d(8, 5);
        Point2d d = new Point2d(9, 7);
        Point2d e = new Point2d(5, 9);
        Point2d f = new Point2d(4, 6);
        Point2d g = new Point2d(1, 8);
        Point2d h = new Point2d(-2, 6.5f);
        Point2d i = new Point2d(0, 5.5f);
        Point2d j = new Point2d(1, 4.5f);

        PolygonDCEL2d poly = new PolygonDCEL2d(Arrays.asList(a,b,c,d,e,f,g,h,i,j));
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
