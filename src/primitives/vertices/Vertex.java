package primitives.vertices;

import constants.dimensions.Dim;
import vector_points.points.Point;
import vector_points.vectors.Vector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class Vertex <D extends Dim,
                     P extends Point<D,P,V>,
                     V extends Vector<D,V,P>,
                     VTX extends Vertex<D,P,V,VTX>>
{

    private String name;
    private Class<VTX> class_VTX;

    private Constructor<VTX> constructor_VTX;

    protected P point;

    protected VTX next;

    protected VTX prev;

    protected boolean ear;

    protected boolean processed;

    protected Vertex(P point,Class<VTX> class_VTX) throws NoSuchMethodException {
        this.point = point;
        this.class_VTX = class_VTX;
        this.constructor_VTX = class_VTX.getConstructor(point.getClass(),class_VTX,class_VTX);
    }

    protected Vertex(P point, VTX next ,VTX prev, Class<VTX> class_VTX) throws NoSuchMethodException {
        this.point = point;
        this.next = next;
        this.prev = prev;
        this.class_VTX = class_VTX;
        this.constructor_VTX = class_VTX.getConstructor(point.getClass(),class_VTX,class_VTX);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public V vectorTo (VTX vtx) {
        return point.vectorTo(vtx.getPoint());
    }

    public P getPoint() {
        return point;
    }

    public void setPoint(P point) {
        this.point = point;
    }

    public VTX getNext() {
        return next;
    }

    public void setNext(VTX next) {
        this.next = next;
    }

    public VTX getPrev() {
        return prev;
    }

    public void setPrev(VTX prev) {
        this.prev = prev;
    }

    public P prevPoint ( ) {
        return this.prev.getPoint();
    }

    public P nextPoint ( ) {
        return this.next.getPoint();
    }


    public boolean isEar() {
        return ear;
    }

    public void setEar(boolean ear) {
        this.ear = ear;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public VTX clone(){
        VTX vtx_clone;
        try {
            vtx_clone =  constructor_VTX.newInstance(point,next,prev);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return  vtx_clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex<?, ?, ?, ?> vertex)) return false;
        return point.equals(vertex.point) && Objects.equals(next, vertex.next) && Objects.equals(prev, vertex.prev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, next, prev);
    }

    public boolean notEquals (VTX vtx){
        return !this.equals(vtx);
    }

    public float angleWith (VTX A ,VTX B) {
        // this = origin
        V OA = this.vectorTo(A);
        V OB = this.vectorTo(B);
        return OA.angleWith(OB);
    }

    public boolean formConvexAngleWith (VTX A, VTX B) {
        return angleWith(A,B) < 180;
    }

    public boolean isConvex () {
        return this.formConvexAngleWith(prev,next);
    }

    public String getPointStr () {
        return point.getDetails();
    }

}
