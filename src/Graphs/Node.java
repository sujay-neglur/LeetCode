package Graphs;

public class Node {
    int name;
    Node [] neighbors;
    public Node(int name){
        this.name = name;
    }
    public Node(int name, Node [] neighbors){
        this.name = name;
        this.neighbors = neighbors;
    }

    public void addNeighbors(Node [] neighbors){
        this.neighbors = neighbors;
    }
}
