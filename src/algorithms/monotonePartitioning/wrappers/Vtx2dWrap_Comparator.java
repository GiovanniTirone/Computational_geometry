package algorithms.monotonePartitioning.wrappers;

import java.util.Comparator;

public class Vtx2dWrap_Comparator implements Comparator<VertexDCEL2dWrapper> {


    @Override
    public int compare(VertexDCEL2dWrapper v1, VertexDCEL2dWrapper v2) {
        if(v1.y() > v2.y()) return -1;
        if(v1.y() == v2.y() && v1.x() < v2.x()) return -1;
        return 1;
    }
}
