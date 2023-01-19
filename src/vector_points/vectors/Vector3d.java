package vector_points.vectors;

import constants.dimensions.Dim3;
import vector_points.points.Point3d;

public class Vector3d extends Vector<Dim3,Vector3d, Point3d> {

    public Vector3d () throws NoSuchMethodException {
        super(Dim3.get(), Vector3d.class);
    }

    public Vector3d(float x, float y,float z) throws NoSuchMethodException {
        super(Dim3.get(), Vector3d.class);
        this.coords[0] = x;
        this.coords[1] = y;
        this.coords[2] = z;
    }

    public float x ( ) {
        return this.coords[0];
    }

    public float y ( ) {
        return this.coords[1];
    }

    public float z ( ){
        return this.coords[2];
    }

    public Vector3d cross (Vector3d v)  {
        float x_, y_, z_;
        x_ = this.y() * v.z() - this.z() * v.y();
        y_ = -( v.z() * this.x() - this.z() * v.x());
        z_ = this.x() * v.y() - v.x() * this.y();
        Vector3d result = null;
        try {
            result = new Vector3d(x_, y_, z_);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
