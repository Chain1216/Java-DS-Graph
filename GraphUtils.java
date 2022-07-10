/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/ >
 * Signed,
 * Author: YOUR NAME HERE
 * Penn email: <YOUR-EMAIL-HERE@seas.upenn.edu>
 * Date: YYYY-MM-DD
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GraphUtils {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 78327812893L;

    /**
     * Given a graph, this method returns the smallest number of edges from the src
     * node to the dest node, or 0 when src = dest, or 鈭�1 for any invalid input.
     * Invalid inputs are defined as: any of graph, src, or dest is null; no path
     * exists from src to dest; any of src or dest do not exist in graph.
     *
     * @param graph directed or undirected graph
     * @param src   source node
     * @param dest  destination node
     * @return the smallest number of edges from the src to dest, or -1 for any
     *         invalid input
     */
    public static int minDistance(Graph graph, String src, String dest) {
    	if(src == dest) {
    		return 0;
    	}
    	
    	if(graph == null || src == null || dest == null) {
    		return -1;
    	}
    	
    	if(!graph.containsNode(src) || !graph.containsNode(dest)) {
    		return -1;
    	}
    	
    	if(!graph.bfs(src,dest)) {
    		return -1;
    	}
        
    	return bfs(src, dest, graph);
    	
    }
    
    public static int bfs(String start, String elementToFind, Graph graph) {
    	int count = 1;
        Set<String> marked = new HashSet<>();
        Set<String> neighbours = graph.getNodeNeighbors(start);
        marked.add(start);
        
        while(!neighbours.contains(elementToFind)) {
        	for(String nei : neighbours) {
        		marked.add(nei);
        	}
        	
        	Set<String> nei2 = new HashSet<>();
        	for(String neii : neighbours) {
        		nei2.addAll(graph.getNodeNeighbors(neii));
        		nei2.removeAll(marked);
        	}
        	neighbours.addAll(nei2);
        	neighbours.removeAll(marked);
        	count++;
        	     	
        }

        return count;
    }

    /**
     * Given a graph, a src node contained in graph, and a distance of at least 1,
     * this method returns the set of all nodes, excluding src, for which the
     * smallest number of edges from src to each node is less than or equal to
     * distance; null is returned if there is any invalid input. Invalid inputs are
     * defined as: any of graph or src is null; src is not in graph; distance is
     * less than 1.
     *
     * @param graph    directed or undirected graph
     * @param src      source node
     * @param distance maximum distance from source to the nodes to include in
     *                 output set
     * @return set of all nodes, excluding src, for which the smallest number of
     *         edges from src to each node is less than or equal to distance, or
     *         null on invalid input
     */
    public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
    	
    	if(graph == null || src == null || distance < 1 ||!graph.containsNode(src)) {
    		return null;
    	}
    	
    	Set<String> setNodes = new HashSet<String>();
    	setNodes.addAll(graph.getAllNodes());
        setNodes.remove(src);
        
    	if(graph.getNumNodes() == 1 && distance == 1) {
    		return setNodes;
    	}
    	
    	for(String node : setNodes) {
    		if(bfs(src,node,graph) > distance) {
    			setNodes.remove(node);
    		}
    	}
    	
    	return setNodes;
	
    }
    
    
    

    /**
     * This method returns true only if the graph g is non-null and has at least
     * three nodes and values is non-null and represents a Hamiltonian cycle through
     * g.
     *
     * @param g      directed or undirected graph
     * @param values a sequence of vertices that must end in the starting node of
     *               the cycle
     * @return true only if values represents Hamiltonian cycle through g
     */
    
    public static boolean isHamiltonianCycle(Graph g, List<String> values) {
    	
    	if(g == null || values == null) {
    		return false;
    	}
    	
    	if(values.size() == 0) {
    		return false;
    	}
    	
    	
    	String head = values.get(0);
		String tail = values.get(values.size() - 1);
    	
    	if (!head.equals(tail)) {
			
			return false;
		}
    	
		Set<String> allNodes = g.getAllNodes();
		
		for (String node : allNodes) {
			if (!values.contains(node)) {
				return false;
			}
		}
		
		Set<String> visited = new HashSet<String>();
		
		for (int i = 0; i < values.size() - 1; i++) {
			
			if (g.containsNode(values.get(i)) && g.containsNode(values.get(i + 1))) {
				String start = values.get(i);
				String next = values.get(i + 1);
				if (i != values.size() - 2) {
					
					if (visited.contains(start) || visited.contains(next)) {
						return false;
					}
					
				} else {
					if (visited.contains(start)) {
						return false;
					}
				}
				
				if (!g.getNodeNeighbors(start).contains(next)) {
					return false;
				} else {
					
					visited.add(start);
				    //visited.add(next);
				}
			}
			else {
				return false;
			}

			
		}
		return true;
	}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}