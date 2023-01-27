package algorithms.monotonePartitioning;

import algorithms.monotonePartitioning.wrappers.EdgeDCEL2dWrapper;
import algorithms.monotonePartitioning.wrappers.VertexDCEL2dWrapper;
import algorithms.monotonePartitioning.wrappers.Vtx2dWrap_Comparator;
import dcel.dim2.EdgeDCEL2d;
import dcel.dim2.FaceDCEL2d;
import dcel.dim2.PolygonDCEL2d;
import dcel.dim2.VertexDCEL2d;
import vector_points.points.Point2d;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class MonotonePartitioning {



    public Vtx_category computeCategory (VertexDCEL2d vtx){
        VertexDCEL2d prevVtx = vtx.getPrevVtx();
        VertexDCEL2d nextVtx = vtx.getNextVtx();

        if(prevVtx == null || nextVtx == null)
            return Vtx_category.INVALID;

        Point2d prev_p = prevVtx.getPoint();
        Point2d p = vtx.getPoint();
        Point2d next_p = nextVtx.getPoint();

        // if next is left to prev_point -> there is a convex angle
        boolean nextIsLeft = next_p.left(prev_p,p);

        if(p.y() > prev_p.y() && p.y() > next_p.y()) {
            if (nextIsLeft)
                return Vtx_category.START;
            else
                return Vtx_category.SPLIT;
        }
        else if(p.y() < prev_p.y() && p.y() < next_p.y() ){
            if(nextIsLeft)
                return Vtx_category.END;
            else
                return Vtx_category.MERGE;
        }
        else if(p.y() == prev_p.y() || p.y() == next_p.y()){ //90 degree
            if(nextIsLeft)
                return Vtx_category.START;
            else
                return null; //todo rivedere
        }
        else {
            return Vtx_category.REGULAR;
        }
    }

    public List<PolygonDCEL2d> getMonotonePolys (PolygonDCEL2d poly) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<VertexDCEL2dWrapper> vertices = new ArrayList<>();
        for(VertexDCEL2d vtx : poly.getVtxList())
            vertices.add(new VertexDCEL2dWrapper(vtx,computeCategory(vtx)));

        vertices.sort(new Vtx2dWrap_Comparator());

        Point2d sweep_point = new Point2d(
                vertices.get(0).x(), vertices.get(0).y());

        SweepLine sweepLine = new SweepLine(sweep_point);
        HashMap<EdgeDCEL2d, EdgeDCEL2dWrapper> edge_map = new HashMap<>();

        for(VertexDCEL2dWrapper vtx : vertices)
        {
            sweep_point.setX(vtx.x());
            sweep_point.setY(vtx.y());

            switch(vtx.getCategory())
            {
                case START -> {
                    handle_start (vtx,sweepLine,edge_map);
                }
                case END -> {
                    handle_end (vtx,sweepLine,edge_map,poly);
                }
                case REGULAR -> {
                    handle_regular (vtx,sweepLine,edge_map,poly );
                }
                case SPLIT -> {
                    handle_split (vtx,sweepLine,edge_map,poly);
                }
                case MERGE -> {
                    handle_merge (vtx,sweepLine,edge_map,poly );
                }
            }
        }

        List<List<Point2d>> poly_pieces_vertices = new ArrayList<>();

        for(FaceDCEL2d face : poly.getFaceList()) {
            EdgeDCEL2d firstEdge = face.getOuter();
            if(firstEdge != null) {
                List<Point2d> piece_vertices = new ArrayList<>();
                piece_vertices.add(firstEdge.getOrigin().getPoint());
                EdgeDCEL2d nextEdge = firstEdge.getNext();
                while(nextEdge != firstEdge){
                    piece_vertices.add(nextEdge.getOrigin().getPoint());
                    nextEdge = nextEdge.getNext();
                }
                poly_pieces_vertices.add(piece_vertices);
            }
        }

        List<PolygonDCEL2d> monotonePolys = new ArrayList<>();

        for(List<Point2d> piece_points : poly_pieces_vertices){
            monotonePolys.add(new PolygonDCEL2d(piece_points));
        }

        return monotonePolys;

    }

    private void handle_start
            (
                    VertexDCEL2dWrapper vtx,
                    SweepLine sweepLine,
                    Map<EdgeDCEL2d,EdgeDCEL2dWrapper> edge_map
            )
            throws InvocationTargetException, InstantiationException, IllegalAccessException
    {
        EdgeDCEL2dWrapper edge = new EdgeDCEL2dWrapper(vtx.getEdge(),vtx);
        sweepLine.add(edge);
        edge_map.put(vtx.getEdge(),edge);
    }

    private void handle_end
            (
                    VertexDCEL2dWrapper vtx,
                    SweepLine sweepLine,
                    Map<EdgeDCEL2d,EdgeDCEL2dWrapper> edge_map,
                    PolygonDCEL2d poly
            )
            throws InvocationTargetException, InstantiationException, IllegalAccessException
    {
        EdgeDCEL2dWrapper prevEdgeWr = edge_map.get(vtx.getEdge().getPrev());
        if(prevEdgeWr.getHelperCategory() == Vtx_category.MERGE)
            poly.splitFace(vtx.getVtx(),prevEdgeWr.getHelper().getVtx());
        sweepLine.remove(prevEdgeWr);
    }

    private void handle_split
            (
                    VertexDCEL2dWrapper vtx,
                    SweepLine sweepLine,
                    Map<EdgeDCEL2d,EdgeDCEL2dWrapper> edge_map,
                    PolygonDCEL2d poly
            )
            throws InvocationTargetException, InstantiationException, IllegalAccessException
    {
        EdgeDCEL2dWrapper edgeWr = new EdgeDCEL2dWrapper(vtx.getEdge(),vtx);
        EdgeDCEL2dWrapper foundEdge = sweepLine.ceiling(edgeWr);
        EdgeDCEL2dWrapper ej;  // ej = leftEdge
        if(foundEdge == null)
        {
            if(sweepLine.size()>0)
            {
                ej = sweepLine.last();
                poly.splitFace(vtx.getVtx(),ej.getHelperVtx());
                ej.setHelper(vtx);
            }
        }
        else if(foundEdge != sweepLine.first())
        {
            ej = sweepLine.lower(foundEdge);
            poly.splitFace(vtx.getVtx(),ej.getHelperVtx());
            ej.setHelper(vtx);
        }
        sweepLine.add(edgeWr);
        edge_map.put(vtx.getEdge(), edgeWr);
    }

    private void handle_merge
            (
                    VertexDCEL2dWrapper vtx,
                    SweepLine sweepLine,
                    Map<EdgeDCEL2d,EdgeDCEL2dWrapper> edge_map,
                    PolygonDCEL2d poly
            )
            throws InvocationTargetException, InstantiationException, IllegalAccessException
    {
        EdgeDCEL2dWrapper prevEdgeWr = edge_map.get(vtx.getEdge().getPrev());
        if(prevEdgeWr.getHelperCategory() == Vtx_category.MERGE)
            poly.splitFace(vtx.getVtx(),prevEdgeWr.getHelperVtx());
        sweepLine.remove(prevEdgeWr);
        EdgeDCEL2dWrapper edge = new EdgeDCEL2dWrapper(vtx.getEdge(),vtx);
        EdgeDCEL2dWrapper found = sweepLine.ceiling(edge);
        EdgeDCEL2dWrapper ej;
        if(found == null && sweepLine.size()>0) {
            ej = sweepLine.last();
            if(ej.getHelperCategory() == Vtx_category.MERGE)
                poly.splitFace(vtx.getVtx(),ej.getHelperVtx());
            ej.setHelper(vtx);
        }
        else if (found != sweepLine.first()){
            ej = sweepLine.lower(found);
            if(ej.getHelperCategory() == Vtx_category.MERGE)
                poly.splitFace(vtx.getVtx(),ej.getHelperVtx());
            ej.setHelper(vtx);
        }
    }

    private void handle_regular
            (
                    VertexDCEL2dWrapper vtx,
                    SweepLine sweepLine,
                    Map<EdgeDCEL2d,EdgeDCEL2dWrapper> edge_map,
                    PolygonDCEL2d poly
            )
            throws InvocationTargetException, InstantiationException, IllegalAccessException
    {
        float prev_y = vtx.getEdge().getPrev().getOrigin().y();
        float current_y = vtx.y();
        float next_y = vtx.getEdge().getNext().getOrigin().y();

        EdgeDCEL2dWrapper edgeWr = new EdgeDCEL2dWrapper(vtx.getEdge(),vtx);

        // if this condition is satisfied, becouse of we count vertices
        // in counterclockwise direction, the interior of the poly must be right
        // to the vtx
        if(prev_y >= current_y && current_y >= next_y){
            EdgeDCEL2dWrapper prevEdge = edge_map.get(vtx.getEdge().getPrev());
            if(prevEdge.getHelperCategory() == Vtx_category.MERGE)
                poly.splitFace(vtx.getVtx(),prevEdge.getHelperVtx());
            sweepLine.remove(prevEdge);
            sweepLine.add(edgeWr);
            edge_map.put(vtx.getEdge(), edgeWr);

        }
        else{
            EdgeDCEL2dWrapper found = sweepLine.ceiling(edgeWr);
            EdgeDCEL2dWrapper ej;
            if(found == null) {
                if (sweepLine.size() > 0) {
                    ej = sweepLine.last();
                    if (ej.getHelperCategory() == Vtx_category.MERGE)
                        poly.splitFace(vtx.getVtx(), ej.getHelperVtx());
                    ej.setHelper(vtx);
                }
            }
            else if(found != sweepLine.first()){
                ej = sweepLine.lower(found);
                if(ej.getHelperCategory() == Vtx_category.MERGE)
                    poly.splitFace(vtx.getVtx(),ej.getHelperVtx());
                ej.setHelper(vtx);
            }
        }
    }





}
