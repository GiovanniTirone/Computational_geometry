package tests.algorithms.monotonePartitioning;

import algorithms.monotonePartitioning.MonotonePartitioning;
import dcel.dim2.PolygonDCEL2d;
import vector_points.points.Point2d;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class TestMonotonePartitioning06 {


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Point2d a = new Point2d(8, 5);
        Point2d b = new Point2d(4, 9);
        Point2d c = new Point2d(1, 7);
        Point2d d = new Point2d(1, 8);
        Point2d e = new Point2d(-6, 6);
        Point2d f = new Point2d(-3, 3);
        Point2d g = new Point2d(-5, 1);
        Point2d h = new Point2d(-7, 2.5f);
        Point2d i = new Point2d(-8, -3);
        Point2d j = new Point2d(-4, -6);
        Point2d k = new Point2d(-2,-4);
        Point2d m = new Point2d(3,-9);
        Point2d n = new Point2d(2,-1);
        Point2d l = new Point2d(6,-2);
        Point2d o = new Point2d(5,2);


        PolygonDCEL2d poly = new PolygonDCEL2d(Arrays.asList(a,b,c,d,e,f,g,h,i,j,k,m,n,l,o));
        MonotonePartitioning algo = new MonotonePartitioning();
        List<PolygonDCEL2d> partition =  algo.getMonotonePolys(poly);
        poly.printFaces();

        System.out.println(" ---------------------- ");

        int s = 1;
        for(PolygonDCEL2d pol : partition){
            System.out.println("Polygon " + s++);
            pol.printVertices();
        }

    }


}
