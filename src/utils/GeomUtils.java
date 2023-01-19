package utils;

import constants.relativePositions.RelPos;
import vector_points.points.Point2d;
import vector_points.points.Point3d;
import vector_points.vectors.Vector2d;
import vector_points.vectors.Vector3d;

public class GeomUtils {






   /* public static boolean left (Point3d a,  Point3d b,  Point3d c)
    {
        return orientation3d(a,b,c) == RelPos.LEFT;
    }
    */


    /*
    public static boolean left( Line2d l, const Point2d& p)
    {
        auto line_dir = l.getDir();
        Vector2f line_normal(-line_dir[Y], line_dir[X]);
        auto value = dotProduct(line_normal, p);
        return (dotProduct(line_normal, p) - l.getD()) < 0 ? false : true;
    }

    bool jmk::left(const Line2d& l, const Point2d& p)
    {
        auto line_normal = l.normal();
        auto value = dotProduct(line_normal, p);
        auto d = dotProduct(line_normal, l.point());
        return (dotProduct(line_normal, p) - d) < 0 ? false : true;
    }
    */

    /*
    boolean right( Point3d a,  Point3d b,  Point3d c)
    {
        return orientation3d(a, b, c) == RelPos.RIGHT;
    }

     */



    /*
    boolean leftOrBeyond( Point3d a,  Point3d b,  Point3d c)
    {
        RelPos position = orientation3d(a, b, c);
        return (position == RelPos.LEFT || position == RelPos.BEYOND);
    }

    public  static  boolean leftOrBetween( Point3d a,  Point3d b,  Point3d c)
    {
        RelPos position = orientation3d(a, b, c);
        return (position == RelPos.LEFT || position == RelPos.BETWEEN);
    }

     */

    /*
    public static boolean incone(const Vertex2dSimple* v1, const Vertex2dSimple* v2)
    {
        if (jmk::leftOrBeyond(v1->point, v1->next->point, v1->prev->point)) {
        // v1 is vonvx vertex
        return jmk::left(v1->point, v2->point, v1->prev->point)
			&& jmk::left(v2->point, v1->point, v1->next->point);
    }

        // v1 is relex vertex
        return !(jmk::leftOrBeyond(v1->point, v2->point, v1->next->point)
		&& jmk::leftOrBeyond(v2->point, v1->point, v1->prev->point));
    }*/

}
