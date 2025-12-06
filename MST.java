// A minimum spanning tree (MST) or minimum weighted spanning tree is a subset of the edges of a connected,
// edge-weighted unidirected graph that connects all the vertices together, without any cycles and with the 
// minimum possible total edge weight

// subgraph of a graph
// no cycle
// vertices connected
// weight minimum

// Prim's Algorithm
// MST Set

// sudo -
// minimum cost use PQ
// boolean vis[]
// PQ<vertex, cost> -> pair type -> min pair(cost)
// PQ(0, 0)
// while(pq.isnotempty)
// curr -> (v, cost)
// if (not vis)
// visit
// MST(ans) edge , edge AL<Edge> cost -> total mst cost
// neighbours -> add in pq

import java.util.*;

public class MST {
  static class Edge {
    int src;
    int dest;
    int wt;

    public Edge(int s, int d, int w) {
      this.src = s;
      this.dest = d;
      this.wt = w;
    }
  }

  public static void createGraph(ArrayList<Edge>[] graph) {
    graph[0].add(new Edge(0, 1, 10));
    graph[0].add(new Edge(0, 2, 15));
    graph[0].add(new Edge(0, 2, 30));

    graph[1].add(new Edge(1, 0, 10));
    graph[1].add(new Edge(1, 3, 40));

    graph[2].add(new Edge(2, 0, 15));
    graph[2].add(new Edge(2, 3, 50));

    graph[3].add(new Edge(3, 1, 40));
    graph[3].add(new Edge(3, 2, 50));
  }

  static class Pair implements Comparable<Pair> {
    int v;
    int cost;

    public Pair(int v, int c) {
      this.v = v;
      this.cost = c;
    }

    @Override
    public int compareTo(Pair p2) {
      return this.cost - p2.cost; // ascending order
    }
  }

  public static void prims(ArrayList<Edge> graph[]) {
    boolean vis[] = new boolean[graph.length];
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    pq.add(new Pair(0, 0));
    int finalCost = 0; // MST Cost/total min weight

    while (!pq.isEmpty()) {
      Pair curr = pq.remove();
      if (!vis[curr.v]) {
        vis[curr.v] = true;
        finalCost += curr.cost;

        for (int i = 0; i < graph[curr.v].size(); i++) {
          Edge e = graph[curr.v].get(i);
          pq.add(new Pair(e.dest, e.wt));
        }
      }
    }

    System.out.println("Final(min) cost of MST = " + finalCost);
  }

  public static void main(String[] args) {
    int V = 4;
    ArrayList<Edge>[] graph = new ArrayList[V];
    for (int i = 0; i < V; i++) {
      graph[i] = new ArrayList<>();
    }

    createGraph(graph);
    prims(graph);
  }
}

// pq internally a min heap
