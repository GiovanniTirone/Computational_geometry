package algorithms.monotonePartitioning;

import algorithms.monotonePartitioning.wrappers.EdgeDCEL2dWrapper;
import dcel.general.EdgeDCEL;
import vector_points.points.Point2d;

import java.util.Optional;
import java.util.TreeSet;

public class SweepLine extends TreeSet<EdgeDCEL2dWrapper> {

    public SweepLine(Point2d startPoint){
        super(new SweepLineComparator(startPoint));
    }

    protected Optional<EdgeDCEL2dWrapper> findEdgeWrapper (EdgeDCEL edgeDCEL){
        for(EdgeDCEL2dWrapper edgeWr : this)
            if(edgeWr.getEdge().equals(edgeDCEL))
                return Optional.of(edgeWr);
        return Optional.empty();
    }


}
