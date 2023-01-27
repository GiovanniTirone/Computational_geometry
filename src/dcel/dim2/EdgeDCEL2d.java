package dcel.dim2;

import constants.dimensions.Dim2;
import dcel.general.EdgeDCEL;
import vector_points.points.Point2d;
import vector_points.vectors.Vector2d;

public class EdgeDCEL2d
    extends EdgeDCEL
        <
            Dim2,
            Vector2d,
            Point2d,
            VertexDCEL2d,
            FaceDCEL2d,
            EdgeDCEL2d  >
{

    protected String name;

    public EdgeDCEL2d(VertexDCEL2d origin) {
        super(origin);
    }

    @Override
    public void setTwin (EdgeDCEL2d edge) {
        this.twin = edge;
        this.name = origin.name + " - " + destination().name;
    }

    public String getName() {
        return name;
    }
}
