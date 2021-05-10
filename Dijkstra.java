/*Dijkstra's Shortest Path Algorithm
Research Project
By: Geoff Crowley*/

import java.util.*;
import java.io.*;

public class Dijkstra {

    final int INFINITY = 2147483647;//max value of Integer
    int n, i, j, currentNode;
    int source, goal;
    int node1, node2, cost;
    long start = 0;
    long end = 0;
    boolean foundNode = false;
    ArrayList<Integer> visited = new ArrayList<Integer>();//N' ??
    ArrayList<Integer> unvisited = new ArrayList<Integer>();
    int[] predecessors;
    int[] distances;
    String[] line = new String[3];
    int[][] costs;
    Random randNum = new Random();
    Scanner scan = new Scanner(System.in);
    private static final String FILE_4 = "/home/geoff/MSU/AdvGIS/topo4.txt";
    private static final String FILE_8 = "/home/geoff/MSU/AdvGIS/topo8.txt";
    private static final String FILE_16 = "/home/geoff/MSU/AdvGIS/topo16.txt";
    String file;
    String fileLine;
    Scanner fileScan;
    PriorityQueue<Integer> nodeQueue;
    
  public void initialize() {
    System.out.println("Enter the number of nodes you would like in the graph...");
	n = scan.nextInt();
	while(n < 2) { //validation of n
        System.out.println("You must enter a value greater than or equal to 2. Please enter the number of nodes you would like in the graph...");
        n = scan.nextInt();
	}
	costs = new int[n][n];
	predecessors = new int[n];//represent continuously updated path and final shotest path
	distances = new int[n];
	nodeQueue = new PriorityQueue<Integer>(n);
	source = 0;
	System.out.println("Please enter a number between 1 and " + (n-1) + " to represent the goal router...");
	goal = scan.nextInt();  
	for(i = 0; i < n; i++) {

		if(i == 0) {
		  distances[i] = 0;//all nodes have a zero distance from themselves.
		}
		else {
		  distances[i] = INFINITY;
		}
	}
	if(n == 4) {
        file = FILE_4;
    }
    if(n == 8) {
        file = FILE_8;
    }
    if(n == 16) {
        file = FILE_16;
    }
    try {
    	fileScan = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException fnf) {
        System.out.println("File not found.");
    }
    while(fileScan.hasNext()) {
    fileLine = fileScan.nextLine();
    line = fileLine.split("   ");
    for(int i = 0; i < line.length; i++) {
        System.out.print(line[i] + ", ");
        if(i == 2) {
            System.out.println();
        }
    }
    node1 = Integer.parseInt(line[0]);
    node2 = Integer.parseInt(line[1]);
    cost = Integer.parseInt(line[2]);
    costs[node1][node2] = cost;
    costs[node2][node1] = cost;
	}
	for(i = 0; i < n; i++) {

		for(j = 0; j < n; j++) {

			System.out.print(costs[i][j] + " ");
			if(j == n-1) {
				System.out.println();
			}
		}
	}
	//make source node the start node in path
	predecessors[0] = 0;
	
	//add all nodes to unvisited
	for(i = 0; i < n; i++) {
	 unvisited.add(i);
	 System.out.println("Node at: " + i + " is " + unvisited.get(i) + " in unvisited");
    }
    
    //add distances to PriorityQueue: nodeQueue
    for(i = 0; i < n; i++) {
     nodeQueue.add(distances[i]);
    }
  }//end initialize()
  
  public void updatePath(int currNode, int[][] c, int[] d, int[] p, ArrayList<Integer> u) {//update path from node m which has current smallest value
    //since we know m, in main, add it to visited list before running this method.
    int newWeight = 0;
    for (int i = currNode; i <= currNode; i++) {// node we are visiting

      for(int j = 0; j < u.size(); j++) {// iterate through each node in unvisited
        newWeight = d[i] + c[i][u.get(j)];
    
       if(newWeight < d[u.get(j)]) {//if weight between nodes is less than current value in distance
	     d[u.get(j)] = newWeight;
	     p[u.get(j)] = i;
       }//if
      }//for j
    }//for i
    costs = c;
    distances = d;
    predecessors = p;
    unvisited = u;
  }//end updatePath()
    
  public void traverseGraph() {
        while(unvisited.size() != 0) {
        //select next node to be visited
        currentNode = nodeQueue.poll();//extract min
        System.out.println("1currentNode: " + currentNode);
        //find node # corresponding to this distance
        for(i = 0; i < unvisited.size(); i++) {//compare current to each unvisited
          if(distances[unvisited.get(i)] == currentNode && foundNode != true) {
            currentNode = unvisited.get(i);//switch currentNode to node #
            System.out.println("2currentNode: " + currentNode);
            visited.add(unvisited.get(i));
            unvisited.remove(i);
            foundNode = true;
          }
        }
        foundNode = false;
        updatePath(currentNode, costs, distances, predecessors, unvisited);
    
        System.out.println();
        //update PriorityQueue with new distances of ONLY unvisited nodes
        nodeQueue.clear();
        for(i = 0; i < unvisited.size(); i++) {
        System.out.println(distances[unvisited.get(i)] + " is being added to nodeQueue");
        nodeQueue.add(distances[unvisited.get(i)]);
        }
    
        System.out.println("Distances");
        for(i = 0; i < n; i ++) {
            System.out.print(distances[i] + "  ");
        }
        System.out.println();
    
        }//while
  }//end traverseGraph()


  public static void main(String args[]) {
    int i;
    Dijkstra dijkstra = new Dijkstra();
    dijkstra.initialize();
    dijkstra.start = System.currentTimeMillis();
    dijkstra.traverseGraph();
    dijkstra.end = System.currentTimeMillis();
    System.out.println("Computation took: " + (dijkstra.end - dijkstra.start) + " milliseconds.");
    System.out.println("Final parent list: ");
    for(i = 0; i < dijkstra.n; i++) {
        System.out.print(dijkstra.predecessors[i] + ", ");
    }
    System.out.println();
  }
    
}
