package tests.dcel.dim2;

import dcel.dim2.PolygonDCEL2d;
import org.testng.annotations.Test;
import vector_points.points.Point2d;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


public class Test_Pentagon_DCEL2d {

    private static Point2d a;
    private static Point2d b;
    private static Point2d c;
    private static Point2d d;
    private static Point2d e;
    private static PolygonDCEL2d poly;

    static  {
        try {
            a = new Point2d(-1,0);
            b = new Point2d(1,0);
            c = new Point2d(1,2);
            d = new Point2d(0,1);
            e = new Point2d(-1,2);
            poly = new PolygonDCEL2d(Arrays.asList(a,b,c,d,e));
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Test
    public static void testConstructor () throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        poly.printVertices();
        poly.printEdges();
        poly.printFaces();
    }

    @Test
    public static void testSlitFace () throws InvocationTargetException, InstantiationException, IllegalAccessException {
        poly.splitFace(poly.getVertex(a).get(),poly.getVertex(d).get());
        poly.printFaces();
    }






}
