package primitives.planes;

import vector_points.points.Point3d;
import vector_points.vectors.Vector3d;

public class Plane {

    Vector3d normal;
    float d ;

    public Plane () {}

    public Plane (Vector3d normal, float constant) {
        this.normal = normal;
        normal.normalize();
        this.d = constant;
    }

    public Plane(Point3d a, Point3d b, Point3d c)  {
        Vector3d AB = a.vectorTo(b);
        Vector3d AC = a.vectorTo(c);

        this.normal = AB.cross(AC);
        normal.normalize();
        this.d = AB.dot(AC);
    }

    public Vector3d getNormal() {
        return normal;
    }

    public float getD() {
        return d;
    }
}
