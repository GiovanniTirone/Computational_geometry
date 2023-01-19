package primitives.lines;

import constants.dimensions.Dim3;
import vector_points.points.Point3d;
import vector_points.vectors.Vector3d;

public class Line3d extends Line<Dim3, Vector3d , Point3d> {

    public Line3d () {
        super();
    }

    public Line3d (Point3d origin, Point3d end){
        super(origin,end);
    }

    public  Line3d(Point3d origin, Vector3d dir){
        super(origin,dir);
    }

}
