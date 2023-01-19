package primitives.vertices;

import constants.dimensions.Dim2;
import vector_points.points.Point2d;
import vector_points.vectors.Vector2d;

import java.util.ArrayList;
import java.util.List;

import static utils.Intersection.intersectionCheck;

public class Vertex2d extends Vertex<Dim2, Point2d, Vector2d,Vertex2d> {

    public Vertex2d(Point2d point) throws NoSuchMethodException {
        super(point, Vertex2d.class);
    }

    public Vertex2d(Point2d point, Vertex2d next, Vertex2d prev) throws NoSuchMethodException {
        super(point, next, prev, Vertex2d.class);
    }

    public boolean interiorCheck (Vertex2d vtx) {
        if(prevPoint().leftOrBeyond(point,nextPoint())) {
            // this is a convex vertex
            return prevPoint().left(point, vtx.getPoint())
                    && nextPoint().left(vtx.getPoint(), point);
        }
        // this is reflex vertex
        return ! (nextPoint().leftOrBeyond(point,vtx.getPoint())
                && prevPoint().left(vtx.getPoint(),point));
    }

    public boolean checkDiagonal (Vertex2d vtx1, Vertex2d vtx2){
        boolean prospect = true;
       /* List<Vertex2d> vertices = new ArrayList<>();
        Vertex2d next = vtx1.getNext();
        while(next.notEquals(vtx1)){
            vertices.add(next);
            next = next.getNext();
        }*/

        Vertex2d current, next;
       // current = vertices.get(0);
        current = this;
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
        }
        //while (current.notEquals(vertices.get(0)));
        while (current.notEquals(this));

        return prospect && vtx1.interiorCheck(vtx2) && vtx2.interiorCheck(vtx1);

    }

}
