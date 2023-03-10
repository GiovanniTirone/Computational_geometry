package dcel.general;

import constants.dimensions.Dim;
import vector_points.points.Point;
import vector_points.vectors.Vector;

public class VertexDCEL<D extends Dim,
                        V extends Vector<D,V,P>,
                        P extends Point<D,P,V>,
                        E extends EdgeDCEL<D,V,P,VTX,F,E>,
                        F extends FaceDCEL<D,V,P,VTX,E,F>,
                        VTX extends VertexDCEL<D,V,P,E,F,VTX>>

{
    protected P point;
    protected E edge;

    protected VertexDCEL(P point){
        this.point = point;
    }

    public void setEdge(E edge) {
        this.edge = edge;
    }

    public String getPointStr () {
        return point.getDetails();
    }

    public E getEdge() {
        return edge;
    }

    public VTX getPrevVtx ( ) {
        return edge.prev.origin;
    }

    public VTX getNextVtx ( ){
        return edge.next.origin;
    }

    public P getPoint () {return point;}
}
