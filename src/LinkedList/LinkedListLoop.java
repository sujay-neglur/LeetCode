package LinkedList;

import java.util.HashSet;
import java.util.Random;

public class LinkedListLoop {
    Node first;
    Node end;
    static int length =0;
    public void add(int data){
        Node n = new Node(data);
        if(end!=null){
            end.next = n;
            end = n;
        }
        else{
            first = n;
            end = n;
        }
        length++;
    }

    public int getStart(Node slow, Node fast){
        while (true){
            slow = slow.next;
            fast = fast.next;
            if(slow==fast){
                return slow.data;
            }
        }
    }

    public int getFirstNodeOfLoopRunner(LinkedListLoop listLoop){
        Node slow = listLoop.first;
        Node fast = listLoop.first;
        while(true){
            slow = slow.next;
            fast = fast.next.next;
            System.out.println("Slow at "+slow.data);
            System.out.println("Fast at "+fast.data);
            if(slow==fast){
                return getStart(listLoop.first,fast);
            }
        }
    }

    public Node getNodeByNumber(int value){
        Node n = first;
        for(int i=0;i<value;i++){
            n=n.next;
        }
        return n;
    }

    public int getFirstNodeOfLoop(LinkedListLoop linkedListLoop){
        HashSet<Node> visited = new HashSet<>();
        Node start = linkedListLoop.first;
        while(true){
            if(visited.contains(start)){
                return start.data;
            }
            if(start==null){
                return Integer.MAX_VALUE;
            }
            visited.add(start);
            start = start.next;

        }
    }
    public static void print(LinkedListLoop loop){
        HashSet<Node> visited = new HashSet<>();
        Node start = loop.first;
        while(start!=null){
            System.out.print(start.data);
            if(start.next!=null) System.out.print("-->");
            if(visited.contains(start)){
                break;
            }
            visited.add(start);
            start = start.next;
        }
        System.out.println();
//        System.out.println(visited);
    }
    public static void main(String[] args) {
        LinkedListLoop linkedListLoop = new LinkedListLoop();
        HashSet<Integer> added = new HashSet<>();
        while(length!=10){
            int num = new Random().nextInt(50);
            if(!added.contains(num)){
                linkedListLoop.add(num);
                added.add(num);
            }
        }

        int randomNode = new Random().nextInt(length);
        System.out.println(randomNode);
        Node loopStart = linkedListLoop.getNodeByNumber(randomNode);
        linkedListLoop.end.next = loopStart;
        print(linkedListLoop);
        System.out.println(linkedListLoop.getFirstNodeOfLoopRunner(linkedListLoop));
    }
}
