package vectors;

import constants.dimensions.Dim;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;



public class Vector <Dimension extends Dim , V extends Vector > {

    Class<V> class_V;

    Constructor<V> constructor_V;

    Dimension dimObj;

    int dim;

    float [] coords;


    public Vector (Dimension dim,Class<V> class_V) throws NoSuchMethodException {
        this.dimObj = dim;
        this.class_V = class_V;
        this.constructor_V = class_V.getConstructor();
        this.dim = dim.getValue();
        this.coords = new float[dim.getValue()];
    }


    public float get (int i) {
        return coords[i];
    }

    public void set(int i,float value) {
        this.coords[i] = value;
    }


    public V sum (Vector<Dimension,V> v){
        V result = null;
        try {
            result = constructor_V.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for(int i=0; i<dim ; i++) result.set(i , coords[i] + v.get(i));
        return result;
    }

    public V minus (Vector<Dimension,V> v){
        V result;
        try {
            result = constructor_V.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for(int i=0; i<dim ; i++) v.set(i , coords[i] - v.get(i));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector<?, ?> vector)) return false;
        return Arrays.equals(coords, vector.coords);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coords);
    }

    public boolean isMinor (Vector<Dimension,V> vector) {
        for (int i = 0; i < dim; i++)
        {
            if (this.coords[i] < vector.get(i)) return true;
			else if (this.coords[i] > vector.get(i)) return false;
        }
        return false;
    }

    public boolean isGreater (Vector<Dimension,V> vector){
        if (this.equals(vector)) return false;
        return !(this.isMinor(vector));
    }

    public float magnitude (){
        float value = 0.0f;
        for (int i = 0; i < dim; i++)
            value += Math.pow(coords[i], 2.0);
        return (float) Math.sqrt(value);
    }

    public void normalize () {
        float mag = magnitude();
        for (int i = 0; i < dim; i++)
            set(i, coords[i] / mag);
    }

    public float dot (Vector<Dimension,V> vector){
        float product = 0;
        for (int i = 0; i < dim; i++)
            product += coords[i] * vector.get(i);
        return product;
    }

}
