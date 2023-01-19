package vector_points.vectors;

import constants.dimensions.Dim;
import vector_points.points.Point;
import vector_points.points.Point3d;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;



public class Vector <Dimension extends Dim , V extends Vector<Dimension,V,P> ,P extends Point<Dimension,P,V>> {

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


    public V scalar (float k){
        V result;
        try {
            result = constructor_V.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for(int i=0; i<dim ; i++) result.set(i , coords[i] * k);
        return result;
    }

    public V sum (V v){
        V result;
        try {
            result = constructor_V.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for(int i=0; i<dim ; i++) result.set(i , coords[i] + v.get(i));
        return result;
    }

    public V minus (V v){
        V result;
        try {
            result = constructor_V.newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        for(int i=0; i<dim ; i++) result.set(i , coords[i] - v.get(i));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector<?, ?, ?> vector)) return false;
        return Arrays.equals(coords, vector.coords);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coords);
    }

    public boolean isMinor (V vector) {
        for (int i = 0; i < dim; i++)
        {
            if (this.coords[i] < vector.get(i)) return true;
			else if (this.coords[i] > vector.get(i)) return false;
        }
        return false;
    }

    public boolean isGreater (V vector){
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

    public float dot (V vector){
        float product = 0;
        for (int i = 0; i < dim; i++)
            product += coords[i] * vector.get(i);
        return product;
    }

    public float angleWith (V v){
        float dot = this.dot(v);
        float theta = (float) Math.acos(Math.abs(dot/(magnitude()*v.magnitude())));
        return (float) Math.toDegrees(theta);
    }

    public boolean formConvexAngleWith ( V  v){
        return this.angleWith(v) <  180;
    }

    public String getDetails () {
        return Arrays.toString(coords);
    }

    // -------------------------  STATIC METHODS --------------------

    public static float scalerTripleProduct (Vector3d v1, Vector3d v2, Vector3d v3) {
        return v1.dot(v2.cross(v3));
    }

    public static boolean collinear (Vector3d v ,Vector3d w) {
        float check_1 = v.x() * w.y() - v.y() * w.x();
        float check_2 = v.y() * w.z() - v.z() * w.y();
        float check_3 = v.x() * w.z() - v.z() * w.x();
        return check_1 == 0 && check_2 == 0 && check_3 == 0;
    }

    public static boolean collinear (Point3d a, Point3d b, Point3d c){
        Vector3d AB = a.vectorTo(b);
        Vector3d AC = a.vectorTo(c);
        return collinear(AB,AC);
    }

    public static boolean coplaner (Vector3d v ,Vector3d w, Vector3d t) {
        float value = scalerTripleProduct(v,w,t);
        return value == 0;
    }

}
