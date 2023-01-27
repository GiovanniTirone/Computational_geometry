package dcel.general;


import constants.dimensions.Dim;
import vector_points.points.Point;
import vector_points.vectors.Vector;

public class EdgeDCEL < D extends Dim,
                        V extends Vector<D,V,P>,
                        P extends Point<D,P,V>,
                        VTX extends VertexDCEL<D,V,P,E,F,VTX>,
                        F extends FaceDCEL<D,V,P,VTX,E,F>,
                        E extends EdgeDCEL<D,V,P,VTX,F,E> >
{
    protected VTX origin;
    protected E twin;
    protected E next;
    protected E prev;
    protected F face;
    protected int id;
    private static int id_counter = -1;

    public EdgeDCEL () {}

    public EdgeDCEL (VTX origin) {
        this.id = ++id_counter;
        this.origin = origin;
    }

    public VTX destination () {
        return twin.origin;
    }

    public VTX getOrigin() {
        return origin;
    }

    public E getTwin() {
        return twin;
    }

    public E getNext() {
        return next;
    }

    public E getPrev() {
        return prev;
    }

    public F getFace() {
        return face;
    }

    public void setTwin(E twin) {
        this.twin = twin;
    }

    public void setNext(E next) {
        this.next = next;
    }

    public void setPrev(E prev) {
        this.prev = prev;
    }

    public void setFace(F face) {
        this.face = face;
    }
}
