package primitives.poligons;

import constants.dimensions.Dim2;
import primitives.vertices.Vertex2d;
import vector_points.points.Point2d;
import vector_points.vectors.Vector2d;

import java.util.List;


public class Polygon2d extends Polygon<Dim2, Point2d, Vector2d, Vertex2d> {
    public Polygon2d(List<Vertex2d> vertices) throws Exception {
        super(vertices);
    }

    public boolean checkDiagonal (Vertex2d vtx1, Vertex2d vtx2) {
        return vtxList.get(0).checkDiagonal(vtx1,vtx2);
    }


    /*
    public boolean checkDiagonal (Vertex2d vtx1, Vertex2d vtx2){

        //manca parte se polygon == null,  video 26

        boolean prospect = true;
        Vertex2d current, next;
        current = this.vtxList.get(0);
        do{
            // verifico che la diagonale non interseca nessun lato del poligono
            next = current.getNext();
            if(current.notEquals(vtx1) && next.notEquals(vtx1)
                    && current.notEquals(vtx2) && next.notEquals(vtx2)
                    && intersectionCheck(vtx1.getPoint(),vtx2.getPoint(),current.getPoint(),next.getPoint())){
                prospect = false;
                break;
            }
            current = next;
        } while (current.notEquals(vtxList.get(0)));

        return prospect && vtx1.interiorCheck(vtx2) && vtx2.interiorCheck(vtx1);
    }*/


    public void initialize_ear_status () {
        Vertex2d v0, v1 ,v2;
        v1 = vtxList.get(0);
        do{
            v0 = v1.getPrev();
            v2 = v1.getNext();
            if(v0.formConvexAngleWith(v1,v2))
                v1.setEar(checkDiagonal(v0,v2));
            v1 = v1.getNext();
        } while (v1.notEquals(vtxList.get(0)));
    }


}
