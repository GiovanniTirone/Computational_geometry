package primitives.lines;

import constants.dimensions.Dim2;
import vector_points.points.Point2d;
import vector_points.vectors.Vector2d;

public class Line2d extends Line<Dim2, Vector2d, Point2d> {

    public Line2d (Point2d origin, Point2d end){
        super(origin,end);
    }

    public Line2d(Point2d origin, Vector2d dir) {
        super(origin,dir);
    }
}
