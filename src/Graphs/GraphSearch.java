package Graphs;

public class GraphSearch {



    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);

        g.addNeighbors(1, new int[]{2,3});
        g.addNeighbors(2, new int[]{1,4});
        g.addNeighbors(3, new int[]{1,5});
        g.addNeighbors(4, new int[]{2});
        g.addNeighbors(5, new int[]{3});

        System.out.println("DFS");
        g.dfs(2);
        System.out.println("BFS");
        g.bfs(2);
    }
}
