package Graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    Node [] nodes;
    int index;
    HashMap<Integer,Integer> nodeIndex = new HashMap<>();

    public Graph(int size){
        nodes = new Node[size];
    }

    public void addNode(int name){
        nodes[index] = new Node(name);
        nodeIndex.put(name,index);
        index++;
    }

    public void addNeighbors(int name, int [] neighbors){
        int pos = nodeIndex.get(name);
        Node [] temp = new Node[neighbors.length];
        for(int i=0;i<neighbors.length;i++){
            temp[i]=nodes[nodeIndex.get(neighbors[i])];
        }
        nodes[pos].addNeighbors(temp);
    }

    public void dfs(int start){
        boolean visited [] = new boolean[nodes.length];
        visited[nodeIndex.get(start)]=true;
        dfs(nodes[nodeIndex.get(start)],visited);
    }

    private void dfs(Node start, boolean visited[]){
        if(start == null) return;
        System.out.println(start.name);
        for(int i=0;i<start.neighbors.length;i++){
            if(!visited[nodeIndex.get(start.neighbors[i].name)]){
                visited[nodeIndex.get(start.neighbors[i].name)] = true;
                dfs(start.neighbors[i],visited);
            }
        }
    }

    public void bfs(int start){
        boolean [] visited = new boolean[nodes.length];
        visited[nodeIndex.get(start)] = true;
        bfs(nodes[nodeIndex.get(start)],visited);
    }

    public void bfs(Node start, boolean visited[]){
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            Node current = queue.remove();
            System.out.println(current.name);
            for(int i=0;i<current.neighbors.length;i++){
                if(!visited[nodeIndex.get(current.neighbors[i].name)]){
                    visited[nodeIndex.get(current.neighbors[i].name)] = true;
                    queue.add(current.neighbors[i]);
                }
            }
        }
    }

}
