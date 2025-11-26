// isBipartite
// Case 1: neighColor same -> FALSE
// Case 2: neighColor diff -> continue
// Case 3: neigh does not have color -> give color by checking neighColor

import java.util.*;

public class Bipartite {
  static class Edge {
    int src;
    int dest;

    public Edge(int s, int d) {
      this.src = s;
      this.dest = d;
    }
  }

  public static void createGraph(ArrayList<Edge>[] graph) {

    graph[0].add(new Edge(0, 1));
    graph[0].add(new Edge(0, 2));

    graph[1].add(new Edge(1, 0));
    graph[1].add(new Edge(1, 3));

    graph[2].add(new Edge(2, 0));
    graph[2].add(new Edge(2, 4));

    graph[3].add(new Edge(3, 1));
    graph[3].add(new Edge(3, 4));

    graph[4].add(new Edge(4, 2));
    graph[4].add(new Edge(4, 3));
  }

  public static boolean isBipartite(ArrayList<Edge>[] graph) { // O(v+E)
    int[] col = new int[graph.length];
    for (int i = 0; i < col.length; i++) {
      col[i] = -1; // no color
    }

    Queue<Integer> q = new LinkedList<>();

    for (int i = 0; i < graph.length; i++) {
      if (col[i] == -1) { // BFS
        q.add(i);
        col[i] = 0; // put first element yellow
        while (!q.isEmpty()) {
          int curr = q.remove();
          for (int j = 0; j < graph[curr].size(); j++) {
            Edge e = graph[curr].get(j); // neigh -> e.dest
            if (col[e.dest] == -1) {
              int nextCol = col[curr] == 0 ? 1 : 0;
              col[e.dest] = nextCol;
              q.add(e.dest);
            } else if (col[e.dest] == col[curr]) {
              return false; // not bipartite
            }
          }
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int V = 5;
    ArrayList<Edge>[] graph = new ArrayList[V];
    for (int i = 0; i < V; i++) {
      graph[i] = new ArrayList<>();
    }

    System.out.println(isBipartite(graph));
  }
}

// if graph doesn't have cycle -> BIPARTITE

// Acyclic -> TRUE
// Even Cycle -> TRUE
// Odd Cycle -> FALSE
