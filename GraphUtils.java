

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {
		Set<Node> marked = new HashSet<Node>();
		Queue<Node> toExplore = new LinkedList<Node>();
		int dist = 0;
		if(graph == null || src == null || dest == null)
			return -1;
		if(!graph.containsElement(src) || !graph.containsElement(dest))
			return -1;
		Node start = new Node(src);
		DepthFirstSearch depthfs = new DepthFirstSearch(graph);
		if(!depthfs.dfs(start, dest))
			return -1;
		
		// if the start and the end element are the same then return 0
		if (start.getElement().equals(dest)) {
			return 0;
		}
		
		marked.add(start);
		toExplore.add(start);
		
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					if (neighbor.getElement().equals(dest)) {
						return dist;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
					dist++;
				}
			}
		}
		return dist;
		
		
	}
	

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {

		/* IMPLEMENT THIS METHOD! */
		
		return null; // this line is here only so this code will compile if you don't modify it
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {

		/* IMPLEMENT THIS METHOD! */
		Set<String> marked = new HashSet<String>();
		boolean firstElement = true;
		if(g == null || values == null || values.size() == 0)
			return false;
		/* Check to see if all the values in the List exist in the graph*/
		for(String val : values) {
			if (!g.containsElement(val))
				return false;
		}
		String start = values.get(0);
		marked.add(start);
		Set<Node> neighborNodes = g.getNodeNeighbors(g.getNode(start));
		/* Add the second element if it is a neighbor to the marked list */
		for(String val : values) {
			/*Ignore the firstElement of the list since it has already been taken care of above*/
			if(firstElement){
				firstElement = false;
				continue;
			}
			//Here we check if the new val is one of the neighbors of the previous element in the list
			if(!neighborNodes.contains(g.getNode(val)))
				return false;
			/*get the neighboring nodes from the value*/	
			neighborNodes = g.getNodeNeighbors(g.getNode(val));
			marked.add(val);
			
				
		}
		/*If the start and the end are not the same then return false*/
		if(start != values.get(values.size() -1))
			return false;
		if(marked.size() == g.getAllNodes().size())
			return true;
		return false;
		
	}
	
}
