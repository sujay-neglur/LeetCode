package Queue;


class Node{
    int data;
    Node next;
    public Node(int data){
        this.data = data;
    }
}
public class Queue {
    Node front;
    Node rear;

    public void enqueue(int data){
        Node n = new Node(data);
        if(rear!=null){
            rear.next = n;
            rear = n;
        }
        else{
            rear = n;
            front = n;
        }
    }

    public int peek(){
        return front.data;
    }

    public boolean isEmpty(){
        return front==null;
    }
    public int dequeue(){
        int data = front.data;
        front = front.next;
        return data;
    }

    public void print(){
        Node start = front;
        while (start!=null){
            System.out.println(start.data);
            start = start.next;
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        System.out.println(queue.isEmpty());
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("Peek "+queue.peek());
        queue.dequeue();
        queue.print();
    }
}
