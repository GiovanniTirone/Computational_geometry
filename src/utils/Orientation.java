package utils;

import constants.relativePositions.RelPos;
import vector_points.points.Point2d;
import vector_points.vectors.Vector2d;

public class Orientation {
    /*
    public static float areaTriangle2d (Point2d a, Point2d b, Point2d c) {
        Vector2d AB = a.vectorTo(b);
        Vector2d AC = a.vectorTo(c);
        return  AB.cross(AC) / 2;
    }

    public static RelPos orientation2d (Point2d a, Point2d b, Point2d c){
        float area = areaTriangle2d(a,b,c);

        Vector2d AB = a.vectorTo(b);
        Vector2d AC = a.vectorTo(c);

        if(area>0) return RelPos.LEFT;
        if(area<0) return RelPos.RIGHT;
        if((AB.x()*AC.x()<0)||(AB.y()*AC.y()<0)) return RelPos.BEHIND;
        if(AB.magnitude() < AC.magnitude()) return RelPos.BEYOND;
        if(a==c) return RelPos.ORIGIN;
        if(b==c) return RelPos.DESTINATION;
        return RelPos.BETWEEN;
    }

    public static boolean left(Point2d a,  Point2d  b,  Point2d  c)
    {
        return orientation2d(a, b, c) == RelPos.LEFT;
    }

    public  static  boolean leftOrBeyond( Point2d a,  Point2d b,  Point2d c)
    {
        RelPos position = orientation2d(a, b, c);
        return (position == RelPos.LEFT || position == RelPos.BEYOND);
    }
    */
}
