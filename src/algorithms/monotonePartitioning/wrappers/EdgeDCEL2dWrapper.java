package algorithms.monotonePartitioning.wrappers;

import algorithms.monotonePartitioning.Vtx_category;
import dcel.dim2.EdgeDCEL2d;
import dcel.dim2.VertexDCEL2d;
import vector_points.points.Point2d;

import java.util.Objects;

public class EdgeDCEL2dWrapper {

    protected EdgeDCEL2d edge;
    protected VertexDCEL2dWrapper helper;

    protected VertexDCEL2d origin;
    protected  VertexDCEL2d dest;

    public EdgeDCEL2dWrapper (EdgeDCEL2d edge,VertexDCEL2dWrapper helper){
        this.edge = edge;
        this.helper = helper;
        this.origin = edge.getOrigin();
        this.dest = edge.destination();
    }

    public void setHelper(VertexDCEL2dWrapper helper) {
        this.helper = helper;
    }

    public EdgeDCEL2d getEdge() {
        return edge;
    }

    public VertexDCEL2dWrapper getHelper() {
        return helper;
    }

    public Vtx_category getHelperCategory() {
        return this.helper.category;
    }

    public VertexDCEL2d getHelperVtx() {
        return this.helper.vtx;
    }

    public VertexDCEL2d getOrigin() {
        return origin;
    }

    public VertexDCEL2d getDest() {
        return dest;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EdgeDCEL2dWrapper that)) return false;
        return edge.equals(that.edge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(edge);
    }
}
