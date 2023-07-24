
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Prim_Unorder_Array {
List<Edge>[] adj;
    int n;
    boolean[] visited;
    PriorityQueue<Edge> minHeap;
    
    Prim_Unorder_Array(int n) {
        this.n = n;
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        visited = new boolean[n];
        minHeap = new PriorityQueue<>((a, b) -> a.wight - b.wight);
    }
    
    void addEdge(int from, int to, int weight) {
        adj[from].add(new Edge(from, to, weight));
        adj[to].add(new Edge(to, from, weight));
        
    }
    
    void primMST() {
        List<Edge> edges = new ArrayList<>();
        int sum = 0;
        minHeap.offer(new Edge(0, 0, 0));
        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            int from = edge.source;
            int to = edge.destination;
            int weight = edge.wight;
            if (visited[to]) {
                continue;
            }
            visited[to] = true;
            edges.add(edge);
            sum += weight;
            
            for (Edge e : adj[to]) {
                if (!visited[e.destination]) {
                    minHeap.offer(e);
                }
            }
        }
        System.out.println("Total weight of MST by Prim's algorithm (Using Min-Heap): " + (double)sum);
        System.out.println("The edges in the MST are:");
        for (int i = 1; i < edges.size(); i++) {
            System.out.println("Edge from " + edges.get(i).source + " to " + edges.get(i).destination + " has weight " + (double)edges.get(i).wight);
        }
        
    }
    public static void run_UnorderArray_Prim() throws FileNotFoundException {
        
        Scanner scanner = new Scanner(new File("input.txt"));
        
        int numVertices = scanner.nextInt();
        int numEdges = scanner.nextInt();
        Prim_Unorder_Array p = new Prim_Unorder_Array(numVertices);
  
        
        do {            
            p.addEdge(scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
        } while (scanner.hasNext());
        
        
        
        
        long startTime = System.nanoTime();
        p.primMST();
        long endTime = System.nanoTime();
        
//        System.out.println("Total weight of MST by Prim's algorithm (Using Min-Heap): " + totalWeight);
        System.out.println("\nRunning time of Prim’s algorithm using Min-Heap is " + (endTime - startTime) + " Nano seconds");
    }
    
}
