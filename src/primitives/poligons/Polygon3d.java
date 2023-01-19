package primitives.poligons;

import constants.dimensions.Dim3;
import primitives.vertices.Vertex3d;
import vector_points.points.Point3d;
import vector_points.vectors.Vector3d;

import java.util.List;

public class Polygon3d extends Polygon<Dim3, Point3d, Vector3d, Vertex3d> {
    protected Polygon3d(List<Vertex3d> vertices) throws Exception {
        super(vertices);
    }
}
