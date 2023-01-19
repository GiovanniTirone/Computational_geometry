package dcel;

import constants.dimensions.Dim;
import vector_points.points.Point;
import vector_points.vectors.Vector;

import java.util.ArrayList;
import java.util.List;

public class FaceDCEL < D extends Dim,
                        V extends Vector<D,V,P>,
                        P extends Point<D,P,V>,
                        VTX extends VertexDCEL<D,V,P,E,F,VTX>,
                        E extends EdgeDCEL<D,V,P,VTX,F,E>,
                        F extends FaceDCEL<D,V,P,VTX,E,F>>
{
    protected  E outer; //one of the counterClockWise edges of the outside border

    private List<E> inner; //list of representants of the borders of inner holes

    protected FaceDCEL () {}

    public void setOuter(E outer) {
        this.outer = outer;
    }

    public void addInnerEdge (E edge){
        this.inner.add(edge);
    }

    public E getInnerEdge(int i) {
        return this.inner.get(i);
    }

    public List<E> getOuterEdgeList() {
        List<E> edgeList = new ArrayList<>();
        if(outer != null){
            E nextEdge = outer.next;
            edgeList.add(outer);
            while(outer != nextEdge){
                edgeList.add(nextEdge);
                nextEdge = nextEdge.next;
            }
        }
        return edgeList;
    }

    public List<P> getOuterPoints () {
        List<P> pointList = new ArrayList<>();
        if(outer != null){
            E nextEdge = outer.next;
            pointList.add(outer.origin.point);
            while(nextEdge != outer){
                pointList.add(nextEdge.origin.point);
                nextEdge = nextEdge.next;
            }
        }
        return pointList;
    }





}
