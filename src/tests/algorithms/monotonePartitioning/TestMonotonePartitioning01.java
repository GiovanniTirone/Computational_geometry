package tests.algorithms.monotonePartitioning;

import algorithms.monotonePartitioning.MonotonePartitioning;
import dcel.dim2.PolygonDCEL2d;
import vector_points.points.Point2d;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMonotonePartitioning01 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        /*
              /\  /\
             /  \/  \
            |        |
            |________|

         */


        Point2d a = new Point2d(-1,0);
        Point2d b = new Point2d(1,0);
        Point2d c = new Point2d(1,2);
        Point2d d = new Point2d(0,1);
        Point2d e = new Point2d(-1,2);

        List<Point2d> points = new ArrayList<>(Arrays.asList(a, b, c, d, e));

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
