// Cycle Detection
// Case 1: vis[neigh] true, parent no -> TRUE
// Case 2: vis[neigh] true, parent yes -> CONTINUE
// Case 3: vis[neigh] false -> normal dfs call(visit) cycle -> TRUE

import java.util.*;

public class Cycle {
  static class Edge {
    int src;
    int dest;

    public Edge(int s, int d) {
      this.src = s;
      this.dest = d;
    }
  }

  // public static void createGraph(ArrayList<Edge>[] graph) {

  // graph[0].add(new Edge(0, 1));
  // graph[0].add(new Edge(0, 2));
  // graph[0].add(new Edge(0, 3));

  // graph[1].add(new Edge(1, 0));
  // graph[1].add(new Edge(1, 2));

  // graph[2].add(new Edge(2, 0));
  // graph[2].add(new Edge(2, 1));

  // graph[3].add(new Edge(3, 0));
  // graph[3].add(new Edge(3, 4));

  // graph[4].add(new Edge(4, 3));
  // }

  public static void createGraph(ArrayList<Edge>[] graph) { // TRUE -> cycle
    graph[0].add(new Edge(0, 2));
    graph[1].add(new Edge(1, 0));
    graph[2].add(new Edge(2, 3));
    graph[3].add(new Edge(3, 0));
  }

  // public static boolean detectCycle(ArrayList<Edge>[] graph) { // O(V+E)
  // (Undirected Graph)
  // boolean[] visit = new boolean[graph.length];
  // for (int i = 0; i < graph.length; i++) {
  // if (!visit[i]) {
  // if (detectCycleUtil(graph, visit, i, -1)) {
  // return true;
  // // cycle exists in one of the parts
  // }
  // }
  // }
  // return false;
  // }

  // public static boolean detectCycleUtil(ArrayList<Edge>[] graph, boolean[]
  // visit, int curr, int par) {
  // visit[curr] = true;

  // for (int i = 0; i < graph[curr].size(); i++) {
  // Edge e = graph[curr].get(i);
  // // case3
  // if (!visit[e.dest]) {
  // if (detectCycleUtil(graph, visit, e.dest, curr)) {
  // return true;
  // }
  // }
  // // case1
  // else if (visit[e.dest] && e.dest != par) {
  // return true;
  // }
  // // case2 -> do nothing -> continue
  // }
  // return false;
  // }

  public static boolean isCycle(ArrayList<Edge>[] graph) { // O(V+E) (Directed Graph)
    boolean[] visit = new boolean[graph.length];
    boolean[] stack = new boolean[graph.length];

    for (int i = 0; i < graph.length; i++) {
      if (!visit[i]) {
        if (isCycleUtil(graph, i, visit, stack)) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean isCycleUtil(ArrayList<Edge>[] graph, int curr, boolean[] visit, boolean[] stack) {
    visit[curr] = true;
    stack[curr] = true;

    for (int i = 0; i < graph[curr].size(); i++) {
      Edge e = graph[curr].get(i);
      if (stack[e.dest]) { // cycle exists
        return true;
      }

      if (!visit[e.dest] && isCycleUtil(graph, e.dest, visit, stack)) {
        return true;
      }
    }
    stack[curr] = false;
    return false;
  }

  public static void main(String[] args) {
    // int V = 5;
    int V = 4;
    ArrayList<Edge>[] graph = new ArrayList[V];
    for (int i = 0; i < V; i++) {
      graph[i] = new ArrayList<>();
    }

    createGraph(graph);
    // System.out.println(detectCycle(graph));
    System.out.println(isCycle(graph));
  }
}

// Directed Graph
// parent with DFS approach -> fails
// in this neighbour visited but not a parent

// Approach
// visited true
// stack true mean it comes in call
// visit all neighbour check
// if my nieghbour already in stack -> Cycle exists
// for unvisited neighbour -> isCycle(neighbour) -> TRUE
// stack[curr] = false
