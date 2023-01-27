package dcel.dim2;

import constants.dimensions.Dim2;
import dcel.general.EdgeDCEL;
import dcel.general.PolygonDCEL;
import vector_points.points.Point2d;
import vector_points.vectors.Vector2d;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PolygonDCEL2d
        extends PolygonDCEL
        <
                        Dim2,
                        Vector2d,
                        Point2d,
                        VertexDCEL2d,
                        EdgeDCEL2d,
                        FaceDCEL2d >
{
    public PolygonDCEL2d(List<Point2d> points)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        super(points, VertexDCEL2d.class, EdgeDCEL2d.class, FaceDCEL2d.class);
    }

   public EdgeDCEL2d getEdge (int i){
        return this.getEdgeList().get(i);
   }

}
