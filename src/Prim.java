import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prim {
    

    static class Vertex {
        List<Edgee> edges = new ArrayList<>();
    }

    public static void prim(Vertex[] vertices, int start,int numberOfE) {
        boolean[] visited = new boolean[vertices.length];
        //PriorityQueue<Edgee> pq = new PriorityQueue<>();
       List<Edgee> edges = new ArrayList<>();
       MinHeap heap = new MinHeap(numberOfE);
        heap.insertion(new Edgee(0, 0, 0));
        int totalWeight = 0;

        while (!heap.Empty()) {
            Edgee edge = heap.MinExtraction();
            int node = edge.getDestination();
            if (visited[node]) {
                continue;
            }
            edges.add(edge);
            visited[node] = true;
            totalWeight += edge.getWight();
                

            for (Edgee e : vertices[node].edges) {
                
                heap.insertion(e);
            }
        }
        System.out.printf("Total weight of MST by Prim's algorithm (Using unordered Min-\nPriority queue):%.1f\nThe edges in the MST are:\n",(double)totalWeight);
        for (int i = 1; i < edges.size(); i++) {
            System.out.printf("Edge from %d to %d has weight %.1f\n",edges.get(i).getSource(),edges.get(i).getDestination(),(double)edges.get(i).getWight());
        }
       
        
    }

   
    public static void run_MinHeap_Prim() throws FileNotFoundException{
    Scanner scanner = new Scanner(new File("input.txt"));
        int numVertices = scanner.nextInt();
        int numEdges = scanner.nextInt();
        Vertex[] vertices = new Vertex[numVertices];
        for (int i = 0; i < numVertices; i++) {
            vertices[i] = new Vertex();
        }

        for (int i = 0; i < numEdges; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            vertices[from].edges.add(new Edgee(to, weight,from));
            vertices[to].edges.add(new Edgee(from, weight,to));
        }
        long begin = System.nanoTime();
        prim(vertices, 0,numEdges*2);
        long end = System.nanoTime();
        
       System.out.printf("\nRunning time of Prim’s algorithm using unordered Min-Priority \nQueue is %d Nano seconds\n",(end-begin));
   
    
    }
}
