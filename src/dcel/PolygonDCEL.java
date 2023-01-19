package dcel;

import constants.dimensions.Dim;
import tests.utils.Wrapper;
import vector_points.points.Point;
import vector_points.vectors.Vector;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.WeakHashMap;

public class PolygonDCEL <  D extends Dim,
                            V extends Vector<D,V,P>,
                            P extends Point<D,P,V>,
                            VTX extends VertexDCEL<D,V,P,E,F,VTX>,
                            E extends EdgeDCEL<D,V,P,VTX,F,E>,
                            F extends FaceDCEL<D,V,P,VTX,E,F>>
{


    private final Constructor<VTX> constructor_VTX;
    private final Constructor<E> constructor_E;

    private final Constructor<F> constructor_F;


    protected List<VTX> vtxList;
    protected List<E> edgeList;
    protected List<F> faceList;
    protected E emptyEdge;

    // Construct the double connected edge list using the given points.
    // Assume the given points list is for polygon and have counter clockwise order
    public PolygonDCEL (List<P> points,Class<VTX> class_VTX,Class<E> class_E, Class<F> class_F) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.constructor_VTX = class_VTX.getConstructor(points.get(0).getClass());
        this.constructor_E = class_E.getConstructor(class_VTX);
        this.constructor_F = class_F.getConstructor();
        int size = points.size();
        if(size<3) return;

        // create vertices
        for(P point : points) {
            vtxList.add(constructor_VTX.newInstance(point));
        }

        // create edges
        for(int i=0; i<size-2; i++){
            E hfEdge = constructor_E.newInstance(vtxList.get(i));
            E twin = constructor_E.newInstance(vtxList.get(i+1));
            vtxList.get(i).setEdge(hfEdge);
            hfEdge.setTwin(twin);
            twin.setTwin(hfEdge);
            edgeList.add(hfEdge);
            edgeList.add(twin);
        }

        //create the last edge
        {
            E hfEdge = constructor_E.newInstance(vtxList.get(vtxList.size() - 1));
            E twin = constructor_E.newInstance(vtxList.get(0));
            hfEdge.setTwin(twin);
            twin.setTwin(hfEdge);
            edgeList.add(hfEdge);
            edgeList.add(twin);
            vtxList.get(vtxList.size()-1).setEdge(hfEdge);
        }

        //set prev and next for middle elements : 2 -> size-2
        for(int i=2; i<=edgeList.size()-3; i++) {

            //clockwise edges by construction of the list
            if(i%2==0)
            {
                edgeList.get(i).setNext(edgeList.get(i+2));
                edgeList.get(i).setPrev(edgeList.get(i-2));
            }
            else //clockwise edges
            {
                edgeList.get(i).setNext(edgeList.get(i-2));
                edgeList.get(i).setPrev(edgeList.get(i+2));
            }
        }

        //set prev and next for remaining elements
        {
            edgeList.get(0).setNext(edgeList.get(2));
            edgeList.get(0).setPrev(edgeList.get(edgeList.size() - 2));
            edgeList.get(1).setNext(edgeList.get(edgeList.size()-1));
            edgeList.get(1).setNext(edgeList.get(3));

            edgeList.get(edgeList.size()-2).setNext(edgeList.get(0));
            edgeList.get(edgeList.size()-2).setPrev(edgeList.get(edgeList.size() - 4));
            edgeList.get(edgeList.size()-1).setNext(edgeList.get(edgeList.size()-3));
            edgeList.get(edgeList.size()-1).setNext(edgeList.get(1));
        }

        //create the faces
        {
            //create the internal face f1 and external face f2
            F f1 = constructor_F.newInstance();
            F f2 = constructor_F.newInstance();
            f1.setOuter(edgeList.get(0));
            f2.addInnerEdge(edgeList.get(1));
            // f2 is unbounded face which wrap the f1.
            // So f1 is a hole in f2.
            // So have clockwise edges in innder edge list

            //set the faces to the edges of f1
            f1.outer.setFace(f1);
            E edge = f1.outer.next;
            while(edge != f1.outer){
                edge.setFace(f1);
                edge = edge.next;
            }

            //set the faces to the edges of f2
            f2.getInnerEdge(0).setFace(f2);
            edge = f2.getInnerEdge(0).next;
            while(edge != f1.getInnerEdge(0)){
                edge.setFace(f2);
                edge = edge.next;
            }
        }

    }

    public List<VTX> getVtxList() {
        return vtxList;
    }

    public List<E> getEdgeList() {
        return edgeList;
    }

    public List<F> getFaceList() {
        return faceList;
    }

    public Optional<VTX> getVertex (P point){
        for(VTX vtx : vtxList)
            if(point.equals(vtx.point)) return Optional.of(vtx);
        return Optional.empty();
    }

    public boolean splitFace (VTX vtx1, VTX vtx2) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Wrapper<E> edge1_wr = new Wrapper<>(null);
        Wrapper<E> edge2_wr = new Wrapper<>(null);
        getEdgesWithSameFaceAndGivenOrigins(vtx1,vtx2,edge1_wr,edge2_wr);

        if(edge1_wr.isNull() || edge2_wr.isNull()) return false;

        E edge1 = edge1_wr.getObj();
        E edge2 = edge2_wr.getObj();

        //check that verices aren't adjacents
        if(edge1.next.origin == vtx2 || edge1.prev.origin == vtx2)
            return false;


        //create half edges
        E half_edge1 = constructor_E.newInstance(vtx1);
        E half_edge2 = constructor_E.newInstance(vtx2);

        //set twin, next and prev for half edges
        half_edge1.setTwin(half_edge2);
        half_edge2.setTwin(half_edge1);
        half_edge1.setNext(edge2);
        half_edge2.setNext(edge1);
        half_edge1.setPrev(edge1.prev);
        half_edge2.setPrev(edge2.prev);

        //set next and prev for edge1 and edge2
        half_edge1.next.setPrev(half_edge1);
        half_edge2.next.setPrev(half_edge2);
        half_edge1.prev.setNext(half_edge1);
        half_edge2.prev.setNext(half_edge2);


        //memorize the face before change
        F prev_face = edge1.face;

        F newFace_1 = constructor_F.newInstance();
        newFace_1.setOuter(half_edge1);
        half_edge1.setFace(newFace_1);

        E tempEdge = half_edge1.next;
        while(tempEdge != half_edge1){
            tempEdge.setFace(newFace_1);
            tempEdge = tempEdge.next;
        }

        F newFace_2 = constructor_F.newInstance();
        newFace_2.setOuter(half_edge2);
        half_edge2.setFace(newFace_2);

        tempEdge = half_edge2.next;
        while(tempEdge != half_edge2){
            tempEdge.setFace(newFace_2);
            tempEdge = tempEdge.next;
        }

        faceList.add(newFace_1);
        faceList.add(newFace_2);
        faceList.remove(prev_face);
        return true;
    }


    private void getEdgesWithSameFaceAndGivenOrigins(
            VTX vtx1, VTX vtx2, Wrapper<E> edge1_wr, Wrapper<E> edge2_wr)
    {
        List<E> edges_with_vtx1_ori = new ArrayList<>();
        List<E> edges_with_vtx2_ori = new ArrayList<>();

        // Get all the edges with origin vtx1
        E edge_vtx1 = vtx1.edge;
        edges_with_vtx1_ori.add(edge_vtx1);

        E nextEdge = edge_vtx1.twin.next;
        while(nextEdge != vtx1.edge) {
            edges_with_vtx1_ori.add(nextEdge);
            nextEdge = nextEdge.twin.next;
        }

        // Get all the edges with origin vtx2
        E edge_vtx2 = vtx2.edge;
        edges_with_vtx2_ori.add(edge_vtx2);

        nextEdge = edge_vtx2.twin.next;
        while(nextEdge != vtx2.edge){
            edges_with_vtx2_ori.add(nextEdge);
            nextEdge = nextEdge.twin.next;
        }

        // Get two edges, one with origin v1 and other
        // with origin v2 and incident to same face
        for(E e_vtx1 : edges_with_vtx1_ori){
            for(E e_vtx2 : edges_with_vtx2_ori){
                if(e_vtx1.face.outer != null){
                    if(e_vtx1.face == e_vtx2.face){
                        edge1_wr.pointToNewObj(e_vtx1);
                        edge2_wr.pointToNewObj(e_vtx2);
                        return;
                    }
                }
            }
        }
    }




}
