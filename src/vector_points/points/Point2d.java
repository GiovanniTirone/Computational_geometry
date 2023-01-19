package vector_points.points;


import constants.dimensions.Dim2;
import constants.relativePositions.RelPos;
import vector_points.vectors.Vector2d;

import java.util.Arrays;

public class Point2d extends Point<Dim2,Point2d,Vector2d> {

    public Point2d ( ) throws NoSuchMethodException {
        super(Dim2.get(), Point2d.class, Vector2d.class);
        this.vector = new Vector2d();
    }


    public Point2d(Vector2d vector2d) throws NoSuchMethodException {
        super(Dim2.get(), Point2d.class, Vector2d.class);
        this.vector = vector2d;
    }

    public Point2d(float x, float y) throws NoSuchMethodException {
        super(Dim2.get(), Point2d.class, Vector2d.class);
        this.vector = new Vector2d(x,y);
    }

    public float x () {
        return this.vector.x();
    }

    public float y () {
        return this.vector.y();
    }

    public String getDetails () {
        return vector.getDetails();
    }

    public float areaTriangle2d (Point2d B, Point2d C) {
        //this = A
        Vector2d AB = this.vectorTo(B);
        Vector2d AC = this.vectorTo(C);
        return  AB.cross(AC) / 2;
    }


    public RelPos posRelToLine (Point2d A, Point2d B) {
        // this = C
        float area = A.areaTriangle2d(B,this);

        Vector2d AB = A.vectorTo(B);
        Vector2d AC = A.vectorTo(this);

        if(area>0) return RelPos.LEFT;
        if(area<0) return RelPos.RIGHT;
        if((AB.x()*AC.x()<0)||(AB.y()*AC.y()<0)) return RelPos.BEHIND;
        if(AB.magnitude() < AC.magnitude()) return RelPos.BEYOND;
        if(A.equals(this)) return RelPos.ORIGIN;
        if(B.equals(this)) return RelPos.DESTINATION;
        return RelPos.BETWEEN;
    }


    public boolean left(Point2d A,  Point2d  B)
    {
        return posRelToLine(A,B) == RelPos.LEFT;
    }

    public  boolean leftOrBeyond( Point2d A,  Point2d B)
    {
        RelPos position = posRelToLine(A,B);
        return (position == RelPos.LEFT || position == RelPos.BEYOND);
    }




}
