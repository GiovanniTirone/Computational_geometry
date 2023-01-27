package algorithms.triangulation;

import primitives.edges.Edge2d;
import primitives.poligons.Polygon2d;
import primitives.vertices.Vertex2d;

import java.util.List;

public class Triangulation {

    public void triangulate_earClipping (Polygon2d poly, List<Edge2d> edgeList){

        poly.initialize_ear_status();

        List<Vertex2d> vertices = poly.getVertices();
        int no_vtx_to_process = vertices.size();

        Vertex2d v0, v1, v2, v3, v4;

      //  while(no_vtx_to_process < 3){
            for(int i=0; i<vertices.size(); i++) {
                v2 = vertices.get(i);
                System.out.println("Processing vertex: " + v2.getPointStr());
                System.out.println("It is an ear: " + v2.isEar());
                if(v2.isEar() && !v2.isProcessed()){
                    v3 = v2.getNext();
                    v4 = v3.getNext();
                    v1 = v2.getPrev();
                    v0 = v1.getPrev();

                    System.out.println("Create new edge: " + v1.getPointStr() + " - " + v3.getPointStr() );
                    edgeList.add(new Edge2d(v1,v3));
                    v2.setProcessed(true);
                    System.out.println("Remove vertex " + v2.getPointStr());
                    v1.setNext(v3);
                    v3.setPrev(v1);

                    if(v1.isConvex()){
                        System.out.println(v1.getPointStr() + " is convex");
                        v1.setEar(v0.checkDiagonal(v0,v3));
                        System.out.println("Is ear: " + v1.isEar());;
                    }

                    if(v3.isConvex()){
                        System.out.println(v3.getPointStr() + " is convex");
                        v3.setEar(v1.checkDiagonal(v1,v4));
                        System.out.println("Is ear: " + v3.isEar());
                    }


                    no_vtx_to_process--;
                    if(no_vtx_to_process<=3) break;
                }
            }
      //  }

    }


}
