import java.util.*;

public class Traversals {
  static class Edge {
    int src;
    int dest;
    int weight;

    public Edge(int s, int d, int w) {
      this.src = s;
      this.dest = d;
      this.weight = w;
    }
  }

  public static void createGraph(ArrayList<Edge>[] graph) {

    graph[0].add(new Edge(0, 1, 1));
    graph[0].add(new Edge(0, 2, 1));

    graph[1].add(new Edge(1, 0, 1));
    graph[1].add(new Edge(1, 3, 1));

    graph[2].add(new Edge(2, 0, 1));
    graph[2].add(new Edge(2, 4, 1));

    graph[3].add(new Edge(3, 1, 1));
    graph[3].add(new Edge(3, 4, 1));
    graph[3].add(new Edge(3, 5, 1));

    graph[4].add(new Edge(4, 2, 1));
    graph[4].add(new Edge(4, 3, 1));
    graph[4].add(new Edge(4, 5, 1));

    graph[5].add(new Edge(5, 3, 1));
    graph[5].add(new Edge(5, 4, 1));
    graph[5].add(new Edge(5, 6, 1));
    graph[5].add(new Edge(6, 5, 1));
  }

  public static void bfs(ArrayList<Edge>[] graph) { // O(V+E)
    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[graph.length];
    q.add(0); // source = 0;

    while (!q.isEmpty()) {
      int curr = q.remove();

      if (!visited[curr]) { // visit curr
        System.out.print(curr + " ");
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
          Edge e = graph[curr].get(i);
          q.add(e.dest);
        }
      }
    }
  }

  // dfs-
  // recursion
  // 1. visit curr node
  // 2. visit all neighbors
  // for(int i=0; to k){
  // if(!visit[arr]){
  // dfs(neighbor i);}}

  public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] visited) { // O(V+E)
    // visit
    System.out.print(curr + " ");
    visited[curr] = true;

    for (int i = 0; i < graph[curr].size(); i++) {
      Edge e = graph[curr].get(i);
      if (!visited[e.dest]) {
        dfs(graph, e.dest, visited);
      }
    }
  }

  public static void main(String[] args) {
    int V = 7;
    ArrayList<Edge>[] graph = new ArrayList[V];
    for (int i = 0; i < V; i++) {
      graph[i] = new ArrayList<>();
    }

    createGraph(graph);

    // bfs(graph);
    dfs(graph, 0, new boolean[V]);
  }
}
