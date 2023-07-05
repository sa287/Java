package F28DA_CW2;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.ListIterator;
import java.util.Scanner;

public class HelloFlyPlanner {

	public static void main(String[] args) {
		
		// The following code is from HelloJGraphT.java of the org.jgrapth.demo package
		
		System.err.println("The example code is from HelloJGraphT.java from the org.jgrapt.demo package.");
		System.err.println("Use similar code to build the small graph from Preliminary Part by hand.");
		System.err.println("Note that you will need to use a different graph class as SimpleGraph since your graph is not just a Simple Graph.");
		System.err.println("Once you understand how to build such graph by hand, move to Part A to build a more substantial graph.");
		// Code is from HelloJGraphT.java of the org.jgrapth.demo package (start)
        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        // note undirected edges are printed as: {<v1>,<v2>}
        System.out.println("-- toString output");
        // @example:toString:begin
        System.out.println(g.toString());
        // @example:toString:end
        System.out.println();
		// Code is from HelloJGraphT.java of the org.jgrapth.demo package (start)
        Graph<String, DefaultWeightedEdge> fpg = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        
        String edi = "Edinburgh";
        String dub = "Dubai";
        String hea = "Heathrow";
        String syd = "Sydney";
        String klm = "Kuala Lumpur";
        
        
        fpg.addVertex(edi);
        fpg.addVertex(dub);
        fpg.addVertex(hea);
        fpg.addVertex(syd);
        fpg.addVertex(klm);
     
        
        DefaultWeightedEdge ed = fpg.addEdge(edi, dub);
        fpg.setEdgeWeight(ed, 190);
        DefaultWeightedEdge de = fpg.addEdge(dub, edi);
        fpg.setEdgeWeight(de, 190);
        
        DefaultWeightedEdge eh = fpg.addEdge(edi, hea);
        fpg.setEdgeWeight(eh, 80);
        DefaultWeightedEdge he = fpg.addEdge(hea, edi);
        fpg.setEdgeWeight(he, 80);
        
        DefaultWeightedEdge hd = fpg.addEdge(hea, dub);
        fpg.setEdgeWeight(hd, 130);
        DefaultWeightedEdge dh = fpg.addEdge(dub, hea);
        fpg.setEdgeWeight(dh, 130);
        
        
        DefaultWeightedEdge hs = fpg.addEdge(hea, syd);
        fpg.setEdgeWeight(hs, 570);
        DefaultWeightedEdge sh = fpg.addEdge(syd, hea);
        fpg.setEdgeWeight(sh, 570);
        
        DefaultWeightedEdge dk = fpg.addEdge(dub, klm);
        fpg.setEdgeWeight(dk, 170);
        DefaultWeightedEdge kd = fpg.addEdge(klm, dub);
        fpg.setEdgeWeight(kd, 170);
        
        DefaultWeightedEdge ks = fpg.addEdge(klm, syd);
        fpg.setEdgeWeight(ks, 150);
        DefaultWeightedEdge sk = fpg.addEdge(syd, klm);
        fpg.setEdgeWeight(sk, 150);
        
        
        System.out.println("The following airports are used:");
        System.out.println(edi);
        System.out.println(dub);
        System.out.println(hea);
        System.out.println(syd);
        System.out.println(klm);
       
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the start airport");
        String start = scan.nextLine();
        
        System.out.println("Please enter the destination airport");
        String destination = scan.nextLine();
        
        
    
        
        DijkstraShortestPath<String, DefaultWeightedEdge> dijk = new DijkstraShortestPath<>(fpg);
        GraphPath<String, DefaultWeightedEdge> shortest = dijk.findPathBetween(fpg, start, destination);
        System.out.println("The shortest path is: " + shortest.toString());
        
        System.out.println("The cost of this path is: £" + dijk.getPathWeight(start, destination));
        
      
        
	}

}
