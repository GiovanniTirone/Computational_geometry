package dcel.dim2;


import constants.dimensions.Dim2;
import dcel.general.VertexDCEL;
import vector_points.points.Point2d;
import vector_points.vectors.Vector2d;

public class VertexDCEL2d
        extends VertexDCEL< Dim2,
                            Vector2d,
                            Point2d,
                            EdgeDCEL2d,
                            FaceDCEL2d,
                            VertexDCEL2d>
{

    protected String name;


    public VertexDCEL2d(Point2d point) {
        super(point);
        float x =   (float) Math.floor(point.x()) ==  point.x() ? Math.round(point.x()) : point.x();
        float y = point.y() - Math.round(point.y()) == 0 ? Math.round(point.y()) : point.y();
        this.name = "("+ x +","+ y +")";
    }


    public float x(){
        return point.x();
    }

    public float y() {
        return point.y();
    }

    public String getName() {
        return name;
    }
}
