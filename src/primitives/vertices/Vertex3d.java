package primitives.vertices;

import constants.dimensions.Dim3;
import vector_points.points.Point3d;
import vector_points.vectors.Vector3d;

public class Vertex3d extends Vertex<Dim3, Point3d, Vector3d,Vertex3d> {
    protected Vertex3d(Point3d point) throws NoSuchMethodException {
        super(point, Vertex3d.class);
    }

    protected Vertex3d(Point3d point, Vertex3d next, Vertex3d prev) throws NoSuchMethodException {
        super(point, next, prev, Vertex3d.class);
    }
}
