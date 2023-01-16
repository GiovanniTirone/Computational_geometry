package vectors;

import constants.dimensions.Dim;

import java.lang.reflect.Constructor;

public class Point <Dimension extends Dim , P extends Point, V extends Vector<Dimension, V>>  {

    Class<P> class_P;
    Constructor<P> constructor_P;
    private Dim dimObj;
    private int dim;
    private V vector;

    protected Point(Dim dim,Class<P> class_P) throws NoSuchMethodException {
        this.class_P = class_P;
        this.constructor_P = class_P.getConstructor(V);
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


}
