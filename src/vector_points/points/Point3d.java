package vector_points.points;

import constants.dimensions.Dim3;
import vector_points.vectors.Vector3d;

public class Point3d extends Point<Dim3,Point3d,Vector3d> {


    public Point3d () throws NoSuchMethodException {
        super(Dim3.get(), Point3d.class, Vector3d.class);
        this.vector = new Vector3d();
    }

    public Point3d(Vector3d vector3d) throws NoSuchMethodException {
        super(Dim3.get(), Point3d.class, Vector3d.class);
        this.vector = vector3d;
    }

    public float x () {
        return this.vector.x();
    }

    public float y () {
        return this.vector.y();
    }

    public float z ( ){return this.vector.z(); }

    public void setX (float value) {
        this.vector.set(0,value);
    }

    public void setY (float value) {
        this.vector.set(1,value);
    }

    public void setZ (float value) {
        this.vector.set(2,value);
    }



}
