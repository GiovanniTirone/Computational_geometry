package utils;

import constants.relativePositions.RelPos;
import primitives.lines.Line2d;
import primitives.lines.Line3d;
import primitives.planes.Plane;
import vector_points.points.Point2d;
import vector_points.points.Point3d;
import vector_points.vectors.Vector2d;
import vector_points.vectors.Vector3d;

import java.util.Optional;



public class Intersection {

    //interection of two segments


    public static boolean intersectionCheck(Point2d a, Point2d b, Point2d c, Point2d d){

        RelPos ab_c = c.posRelToLine(a,b);
        RelPos ab_d = d.posRelToLine(a,b);
        RelPos cd_a = a.posRelToLine(c,d);
        RelPos cd_b = b.posRelToLine(c,d);

        if(    ab_c == RelPos.BETWEEN || ab_c == RelPos.ORIGIN || ab_c == RelPos.DESTINATION
                || ab_d == RelPos.BETWEEN || ab_d == RelPos.ORIGIN || ab_d == RelPos.DESTINATION
                || cd_a == RelPos.BETWEEN || cd_a == RelPos.ORIGIN || cd_a == RelPos.DESTINATION
                || cd_b == RelPos.BETWEEN || cd_b == RelPos.ORIGIN || cd_b == RelPos.DESTINATION
        )
        {
            return true;
        }
        return (ab_c == RelPos.LEFT)^(ab_d == RelPos.LEFT) && (cd_a== RelPos.LEFT)^(cd_b == RelPos.LEFT);

    }


    public  static  boolean intersectionCheck(Line2d l1, Line2d l2){
        Point2d l1_start = l1.getOrigin();
        Point2d l1_end = l1.getEnd();
        Point2d l2_start = l2.getOrigin();
        Point2d l2_end = l2.getEnd();
        return intersectionCheck(l1_start,l1_end,l2_start,l2_end);
    }
    public static Optional<Point2d> intersectionPoint (Point2d a, Point2d b, Point2d c, Point2d d) throws NoSuchMethodException {
        Vector2d AB = a.vectorTo(b);
        Vector2d CD = c.vectorTo(d);
        Vector2d n = new Vector2d(CD.y(),-CD.x());
        float deno = n.dot(AB);
        if(deno != 0)
        {
            Vector2d AC = a.vectorTo(c);
            float number = n.dot(AC);
            float t = number /deno;
            float x = a.x() + t * AB.x();
            float y = a.y() + t * AB.y();
            return Optional.of(new Point2d(x,y));
        }
        else return Optional.empty();
    }

    public static Optional<Point2d> intersectionPoint (Line2d l1,Line2d l2) throws NoSuchMethodException {
        Point2d l1_start = l1.getOrigin();
        Point2d l1_end = l1.getEnd();
        Point2d l2_start = l2.getOrigin();
        Point2d l2_end = l2.getEnd();
        return intersectionPoint(l1_start,l1_end,l2_start,l2_end);
    }

    public static Optional<Point3d> intersection (Line3d line, Plane plane) throws NoSuchMethodException {
        Vector3d n = plane.getNormal();
        float D = plane.getD();
        Vector3d d = line.getDir();
        Point3d O = line.getOrigin();

        float nd = n.dot(d);

        if(nd == 0){
            return Optional.empty();
        }
        else{
            Point3d intersectionPoint = new Point3d();
            float t = ((-1) * n.dot(O.getVector()) + D) / nd;
            intersectionPoint.setX(O.x() + t * d.x());
            intersectionPoint.setY(O.y() + t * d.y());
            intersectionPoint.setZ(O.z() + t * d.z());
            return Optional.of(intersectionPoint);
        }
    }

    public static Optional<Line3d> intersect (Plane p1, Plane p2) throws NoSuchMethodException {
        Vector3d n1 = p1.getNormal();
        Vector3d n2 = p2.getNormal();
        float d1 = p1.getD();
        float d2 = p2.getD();

        Vector3d direction = n1.cross(n2);

        // planes parallels
        if(direction.magnitude() == 0)
            return Optional.empty();

        float n1n2 = n1.dot(n2);
        float n1n2_2 = n1n2 * n1n2;

        float a = (d2 * n1n2 - d1) / (n1n2_2 -1);
        float b = (d1 * n1n2 - d2) / (n1n2_2 -1);

        Point3d point = new Point3d(n1.scalar(a).sum(n2.scalar(b)));
        direction.normalize();

        return Optional.of( new Line3d(point,direction) );


    }



}
