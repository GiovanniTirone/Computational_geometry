
import Constants.dimensions.Dim;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

public class Vector < Coord_type extends Number, Dimension extends Dim>{

    Class<Coord_type> coord_type_class;

    Class<Dimension> dimension_class;

    Dimension dimObj;

    int dim;

    Coord_type [] coords;

    public Vector (Class<Coord_type> coord_type_class, Dimension dim) {
        this.coord_type_class = coord_type_class;
        this.dimObj = dim;
        this.dim = dim.getValue();
        this.coords = (Coord_type[]) Array.newInstance(coord_type_class,dim.getValue());
    }


    public Vector (Class<Coord_type> coord_type_class, Dimension dim, Coord_type x, Coord_type y) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.coord_type_class = coord_type_class;
        this.dimension_class = dimension_class;
        this.dimObj = dim;
        this.coords = (Coord_type[]) Array.newInstance(coord_type_class, dim.getValue());
        this.dim = dim.getValue();
        this.coords[0] = x;
        this.coords[1] = y;
    }

    public Vector (Class<Coord_type> coord_type_class, Dimension dim, Coord_type x, Coord_type y, Coord_type z) {
        this.coord_type_class = coord_type_class;
        this.dimObj = dim;
        this.coords = (Coord_type[]) Array.newInstance(coord_type_class,dim.getValue());
        this.dim = dim.getValue();
        this.coords[0] = x;
        this.coords[1] = y;
        this.coords[2] = z;
    }


    public Coord_type get (int i) {
        return coords[i];
    }

    public void set(int i,Coord_type value) {
        this.coords[i] = value;
    }

    public Coord_type x ( ) {
        return this.coords[0];
    }

    public Coord_type y ( ) {
        return this.coords[1];
    }

    public Coord_type z ( ) {
        if(dim < 3) return null;
        return this.coords[2];
    }

    public Vector<Coord_type,Dimension> sum (Vector<Coord_type,Dimension> v){
        Vector vSum =  new Vector<>(coord_type_class,dimObj);
        for(int i=0; i<dim ; i++) vSum.set(i ,Double.valueOf(coords[i].doubleValue() + v.get(i).doubleValue()));

    }









}
