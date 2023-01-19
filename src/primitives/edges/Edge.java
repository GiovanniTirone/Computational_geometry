package primitives.edges;

import constants.dimensions.Dim;
import primitives.vertices.Vertex;
import vector_points.points.Point;
import vector_points.vectors.Vector;

import java.util.Objects;

public class Edge < D extends Dim,
                    P extends Point<D,P,V>,
                    V extends Vector<D,V,P>,
                    VTX extends Vertex<D,P,V,VTX>,
                    E extends Edge<D,P,V,VTX,E> >
{
    VTX vtx1;
    VTX vtx2;

    public Edge (VTX v1, VTX v2 ) {
        this.vtx1 = v1;
        this.vtx2 = v2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge<?, ?, ?, ?, ?> edge)) return false;
        return vtx1.getPoint().equals(edge.vtx1.getPoint()) && vtx2.getPoint().equals(edge.vtx2.getPoint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(vtx1, vtx2);
    }

    public String getDetails () {
        return vtx1.getPointStr() + " - " + vtx2.getPointStr();
    }


}
