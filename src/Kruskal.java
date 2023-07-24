
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Kruskal {

    
  
    public static void run_Kruskal(){
    Scanner in = null;
        int vertex, edge, i = 0;
        int[] id;

        Stack<Edge> stack = new Stack<>();

        // try-catch (File not found)
        try {
            File input = new File("input.txt");
            in = new Scanner(input);

        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
            System.exit(1);
        }
        
        vertex = in.nextInt();
        edge = in.nextInt();

        id = new int[vertex];

        do {
            if (i < vertex) {
                id[i] = i;
                i++;
            }

            stack.push(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));

        } while (in.hasNext());
        long begin = System.nanoTime();
        Applay_Kruskal(stack, id);
        long end = System.nanoTime();
        System.out.printf("\nRunning Time of Kruskal’s algorithm using Union-Find approach \nis %d Nano seconds.\n",(end - begin));
    }

    static void Applay_Kruskal(Stack stack, int[] id) {
        sortStack(stack);
        int sum = 0,index=0;
        int[] soruce,distenation,weghit;
        soruce =new int[id.length-1];
        distenation =new int[id.length-1];
        weghit =new int[id.length-1];
        
        Edge stackPeek;
        while (!stack.empty()&&index!=id.length-1) {
            stackPeek = (Edge) stack.pop();
            if (id[stackPeek.getSource()] != id[stackPeek.getDestination()]) {
                Union(stackPeek.getSource(), stackPeek.getDestination(), id);
                
                soruce[index]=stackPeek.getSource();
                distenation[index]=stackPeek.getDestination();
                weghit[index]=stackPeek.getWight();
                
                sum += stackPeek.getWight();
                index++;
                
            }
            

        }

        System.out.printf("Total weight of MST by Kruskal's algorithm: %d\nThe edges in the MST are:\n",sum);
        
        for (index = 0; index <soruce.length ; index++) {
            System.out.printf("Edge from %d to %d has weight %.1f\n",soruce[index],distenation[index],(double)weghit[index]);
        }
    }

    static void sortStack(Stack stack) {
        Stack<Edge> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            Edge stackPeek = (Edge) stack.pop();
            while (!tempStack.isEmpty() && stackPeek.getWight() < tempStack.peek().getWight()) {
                stack.push(tempStack.pop());
            }
            tempStack.push(stackPeek);
        }
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    public static void Union(int a, int b, int[] id) {
        int aid = id[a];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == aid) {
                id[i] = id[b];
            }
        }

    }
}

