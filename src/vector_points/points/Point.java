package vector_points.points;

import constants.dimensions.Dim;
import vector_points.vectors.Vector;
import vector_points.vectors.Vector3d;

import java.lang.reflect.Constructor;
import java.util.Objects;


public class Point <Dimension extends Dim , P extends Point<Dimension,P,V>, V extends Vector<Dimension, V,P>>  {

    Class<P> class_P;

  //  Class<V> class_V;
    Constructor<P> constructor_P;

  //  Constructor<V> constructor_V;

    private Dim dimObj;
    private int dim;
    protected V vector;



    protected Point(Dim dim,Class<P> class_P,Class<V> class_V) throws NoSuchMethodException {
        this.class_P = class_P;
        this.constructor_P = class_P.getConstructor(class_V);
       // this.class_V = class_V;
       // this.constructor_V = class_V.getConstructor();
        this.dimObj = dim;
        this.dim = dim.getValue();
    }

    public V getVector() {
        return vector;
    }

    public void set(int i, float value) {
        vector.set(i,value);
    }

    public P apply (V v) {
        P end = null;
        try {
            end  = constructor_P.newInstance(vector.sum(v));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return end;
    }

    public V vectorTo (P target) {
        return target.getVector().minus(this.vector);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point<?, ?, ?> point)) return false;
        return vector.equals(point.vector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vector);
    }

    public float angleWith (P A ,P B) {
        // this = origin
        V OA = this.vectorTo(A);
        V OB = this.vectorTo(B);
        return OA.angleWith(OB);
    }

    public boolean formConvexAngleWith (P A, P B) {
        return angleWith(A,B) <180;
    }

    public String getDetails () {
        return vector.getDetails();
    }

}
