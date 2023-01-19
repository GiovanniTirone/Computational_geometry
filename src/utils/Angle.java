package utils;

import constants.dimensions.Dim;
import primitives.lines.Line;
import primitives.lines.Line3d;
import primitives.planes.Plane;
import vector_points.points.Point;
import vector_points.vectors.Vector;

public class Angle {

    public static<D extends Dim, V extends Vector<D,V,P>, P extends Point<D,P,V> , L extends Line<D,V,P>>
    float angleLines (L l1, L l2){
        return l1.getDir().angleWith(l2.getDir());
    }

    public static float angleLinePlane (Line3d l , Plane p) {
        float dot = l.getDir().dot(p.getNormal());
        float theta = (float) Math.acos(Math.abs(dot));
        float angle = (float) Math.toDegrees(theta);
        return 90 - angle;
    }

    public static float anglePlanes (Plane p1, Plane p2){
        return p1.getNormal().angleWith(p2.getNormal());
    }


}
