package Stacks;

class Node{
    int data;
    Node next;
    public Node(int data){
        this.data = data;
    }

}

public class Stack {
    Node top;
    public void push(int data){
        Node n = new Node(data);
        if(top == null){
            // first node in stack
            top = n;
        }
        else{
            n.next = top;
            top = n;
        }
    }

    public int pop(){
        int data = top.data;
        top = top.next;
        return data;
    }
    public void print(){
        Node start = top;
        while(start!=null){
            System.out.println(start.data);
            start = start.next;
        }
    }

    public int peek(){
        return top.data;
    }

    public boolean isEmpty(){
        return top==null;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        System.out.println(stack.isEmpty());
        stack.push(2);
        stack.push(1);
        stack.push(3);
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        stack.pop();
        stack.print();
    }
}
