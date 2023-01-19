package utils;

import primitives.lines.Line3d;
import primitives.planes.Plane;
import vector_points.points.Point3d;
import vector_points.vectors.Vector3d;

public class Distance {

    public  static  float distance (Line3d line, Point3d P) {
        //attenzione a t negativo
        Vector3d OP = line.getOrigin().vectorTo(P);
        float t = line.getDir().dot(OP);
        Point3d T = line.getOrigin().apply(line.getDir().scalar(t));
        Vector3d TP = P.vectorTo(T);
        return TP.magnitude();
     }

    public static float distance (Plane plane, Point3d Q){
        return plane.getNormal().dot(Q.getVector()) - plane.getD();
    }

}
