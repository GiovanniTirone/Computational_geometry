package algorithms.monotonePartitioning;

import algorithms.monotonePartitioning.wrappers.EdgeDCEL2dWrapper;
import vector_points.points.Point2d;

import java.util.Comparator;

public class SweepLineComparator implements Comparator<EdgeDCEL2dWrapper> {

    Point2d point; //this is the current position of the sweepLine

    public SweepLineComparator(Point2d point){
        this.point = point;
    }


    // compare considering the x coordinate of the intersection between the orizontal
    // line passing througt point and the two edges
    @Override
    public int compare(EdgeDCEL2dWrapper e1, EdgeDCEL2dWrapper e2) {
        boolean e1Lower =  computeX(e1) < computeX(e2);
        if(e1Lower) return -1;
        else if(e1 == e2) return 0;
        else return 1;
    }

    private float computeX(EdgeDCEL2dWrapper e) {
        float distY = e.getDest().y() - e.getOrigin().y();
        float x = point.x();
        if(distY != 0){
            x = (point.y() - e.getOrigin().y())*(e.getDest().x()-e.getOrigin().x()) /
                                distY + e.getOrigin().x();
        }
        return x;
    }


}
