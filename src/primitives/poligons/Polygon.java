package primitives.poligons;

import constants.dimensions.Dim;
import primitives.vertices.Vertex;
import vector_points.points.Point;
import vector_points.vectors.Vector;

import java.util.LinkedList;
import java.util.List;

public class Polygon <D extends Dim, P extends Point<D,P,V>,
                    V extends Vector<D,V,P>, VTX extends Vertex<D,P,V,VTX>>
{

    protected LinkedList<VTX> vtxList;

    protected Polygon (List<VTX> vertices) throws Exception {
        int size = vertices.size();
        if(size<3) throw new Exception("The vertices must be at leat 3");
        this.vtxList = new LinkedList<>();
        for(VTX vtx : vertices){
            this.vtxList.addLast(vtx.clone());
        }
        for(int i =0; i<size; i++){
            this.vtxList.get(i).setNext(vtxList.get((i+1)%size));
            if(i != 0)
                vtxList.get(i).setPrev(vtxList.get(i-1));
            else
                vtxList.get(i).setPrev(vtxList.get(size-1));

        }
    }

    public LinkedList<VTX> getVertices () {
        return vtxList;
    }



}
