package LinkedList;


import java.util.HashMap;


public class DeepCopy {

    HashMap<Node,Node> visited = new HashMap<>();
    public Node getNode(Node node){
        if(node!=null){
            if(visited.containsKey(node)){
                return visited.get(node);
            }
            else{
                Node n = new Node(node.data,null,null);
                visited.put(node,n);
                return n;
            }
        }
        return null;
    }
    public Node copyRandomList(Node head) {
        if(head==null) return head;
        Node start = head;
        Node first = new Node(head.data,null, null);
        Node result= first;
        visited.put(head,first);
        while(start!=null){
            first.random = getNode(start.random);
            first.next = getNode(start.next);
            start = start.next;
            first = first.next;
        }
        return result;
    }

}
