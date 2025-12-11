import java.util.*;

public class DisjointSet {
  static int n = 7;
  static int par[] = new int[n];
  static int rank[] = new int[n];

  public static void init() {
    for (int i = 0; i < n; i++) {
      par[i] = i;
    }
  }

  public static int find(int x) {
    if (x == par[x]) {
      return x;
    }
    return par[x] = find(par[x]);
  }

  public static void union(int a, int b) {
    int parA = find(a);
    int parB = find(b);

    if (rank[parA] == rank[parB]) {
      par[parB] = parA;
      rank[parA]++;
    } else if (rank[parA] < rank[parB]) {
      par[parA] = parB;
    } else {
      par[parB] = parA;
    }
  }

  // path compression

  public static void main(String args[]) {
    init();
    union(1, 3);
    System.out.println(find(3));
    union(2, 4);
    union(3, 6);
    union(1, 4);
    System.out.println(find(3));
    System.out.println(find(4));
    union(1, 5);
  }
}

// for cycle detection
// kruskal's algo (MST)

// Disjoint Set DS
// Union find
// merge ds

// Find (in which set element belongs to)
// Union {join}

// to store and track non-overlapping sets

// Implementation (Optimized)
// Parent + union by rank

// Find-
// int n, par[n]-> par[i]=i, rank[n]-> 0

// find(x)
// if(x=par[x])
// return x
// return find(par[x])

// union(a,b)
// parA, parB
// find(a), find(b)
// rank- parA, parB equal
// -> par[parA] = parB
// rank[parB]++

// tc -> find-> O(1)
// tc -> union-> O(1)
// mathematically tc-> O(4K)