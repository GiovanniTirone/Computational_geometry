package vectors;
import constants.dimensions.Dim2;

public class Vector2d extends Vector<Dim2,Vector2d> {

    public Vector2d () throws NoSuchMethodException {
        super(Dim2.get(),Vector2d.class);
    }

    public Vector2d (float x, float y) throws NoSuchMethodException {
        super(Dim2.get(),Vector2d.class);
        this.coords[0] = x;
        this.coords[1] = y;
    }


    public float x ( ) {
        return this.coords[0];
    }

    public float y ( ) {
        return this.coords[1];
    }

    public float cross (Vector2d v){
        return this.x() * v.y() - this.y() * v.x();
    }


}
