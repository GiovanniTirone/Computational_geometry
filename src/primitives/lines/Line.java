package primitives;

import constants.dimensions.Dim;
import vector_points.points.Point;
import vector_points.vectors.Vector;

import java.lang.reflect.Constructor;

public class Line<Dimension extends Dim, V extends Vector<Dimension,V,P>, P extends Point<Dimension,P,V>> {


    P origin;
    V dir;

    public Line () {}

    public Line (P origin, P end )  {
        this.origin = origin;
        this.dir = origin.vectorTo(end);
        this.dir.normalize();
    }

    public Line(P origin, V dir){
        this.origin = origin;
        this.dir = dir;
        this.dir.normalize();
    }

    public P getOrigin() {
        return origin;
    }

    public V getDir() {
        return dir;
    }

    public P getEnd() {
        return this.origin.apply(this.dir);
    }

    public float getMagnitude () {
        return this.dir.magnitude();
    }

    public void setOrigin(P origin) {
        this.origin = origin;
    }

    public void setDir(V dir) {
        this.dir = dir;
    }
}
