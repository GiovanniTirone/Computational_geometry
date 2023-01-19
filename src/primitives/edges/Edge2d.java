package primitives.edges;

import constants.dimensions.Dim2;
import primitives.vertices.Vertex2d;
import vector_points.points.Point2d;
import vector_points.vectors.Vector2d;

public class Edge2d extends Edge<Dim2, Point2d, Vector2d, Vertex2d,Edge2d> {
    public Edge2d(Vertex2d v1, Vertex2d v2) {
        super(v1, v2);
    }
}
