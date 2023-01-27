package algorithms.monotonePartitioning.wrappers;

import algorithms.monotonePartitioning.Vtx_category;
import dcel.dim2.EdgeDCEL2d;
import dcel.dim2.VertexDCEL2d;


public class VertexDCEL2dWrapper  {

    protected VertexDCEL2d vtx;
    protected Vtx_category category;

    public VertexDCEL2dWrapper(VertexDCEL2d vtx, Vtx_category category) {
       this.vtx = vtx;
       this.category = category;
    }

    public Vtx_category getCategory() {
        return category;
    }

    public VertexDCEL2d getVtx() {
        return vtx;
    }

    public float x () {
        return this.vtx.x();
    }

    public float y () {
        return this.vtx.y();
    }

    public EdgeDCEL2d getEdge( ) {
        return this.vtx.getEdge();
    }

}
