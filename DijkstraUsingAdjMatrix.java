import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Graph {
    private int[][] adjacencyMatrix;
    private Map<String, Integer> vertexIndices;
    private ArrayList<String> vertices;

    public Graph(int size) {
        adjacencyMatrix = new int[size][size]; //2D array for adj matrix
        vertexIndices = new HashMap<>(); //sth like a dictionary, to map vertex alphabets to numerical values
        vertices = new ArrayList<>(); //uses an array to store the integers for the vertex values
    }

    public void addVertex(String vertex) {
        if (!vertexIndices.containsKey(vertex)) { //if vertex is NOT already present, so no duplicates in hashmap
            int index = vertices.size(); //the index of the new vertex is the current size of the array
            vertices.add(vertex); //add the new vertex into the ArrayList
            vertexIndices.put(vertex, index); //like a dictionary, maps the VertexName of vertex to its index
        }
    }

    public void addEdge(String source, String destination, int weight) {
        int sourceIndex = vertexIndices.get(source); //accesses the hashmap, to find out corresponding VertexIndex to VertexName
        int destIndex = vertexIndices.get(destination); //same as above, now for destination
        adjacencyMatrix[sourceIndex][destIndex] = weight; //assign the weight from Vertex source to Vertex destination
        //adjacencyMatrix[destIndex][sourceIndex] = weight; only add this if graph is unweighted
    }

    public void dijkstra(String startVertex) {
        int startIndex = vertexIndices.get(startVertex); //source index of vertex fed into the algo
        int[] distance = new int[vertices.size()]; //creates the array d[] in lecture notes. Distance array to be updated
        boolean[] visited = new boolean[vertices.size()]; //creates the boolean array, as in lec notes. tracks nodes already in S
        PriorityQueue pq = new PriorityQueue(); //using a pq, created using array

        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE; //initialise starting dist from source to all other nodes as infinity
        }

        distance[startIndex] = 0; //source node to itself
        pq.insert(startVertex, 0); //insert source into pq

        while (!pq.isEmpty()) {
            String currentVertex = pq.remove(); //pop lowest weight vertex out of arrayList
            int currentIndex = vertexIndices.get(currentVertex); //recover the popped vertexIndex using hashmap
            visited[currentIndex] = true; //Add that vertex in S (visited)

            for (int i = 0; i < vertices.size(); i++) {
                //for each vertex v adjacent to poppedVertex, if still unvisited and
                // thr exist an edge between current vertex and that other vertex
                if (!visited[i] && adjacencyMatrix[currentIndex][i] != 0) {
                    int newDistance = distance[currentIndex] + adjacencyMatrix[currentIndex][i]; //get new dist
                    if (newDistance < distance[i]) { // if newDistance is the shortest distance
                        distance[i] = newDistance; //set that distance as the new shortest distance
                        pq.insert(vertices.get(i), newDistance); //insert the new distance into the pq
                    }
                }
            }
        }

        // Print shortest distances from startVertex to all other vertices
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println("Shortest distance from " + startVertex + " to " + vertices.get(i) + ": " + distance[i]);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 1);
        graph.addEdge("D", "E", 3);

        graph.dijkstra("A");
    }
}