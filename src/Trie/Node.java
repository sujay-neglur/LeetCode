package Trie;

public class Node {
    char letter;
    Node [] connections = new Node[26];
    boolean isEndOfWord = false;

    public Node(char letter){
        this.letter = letter;
        for(int i=0;i<26;i++){
            connections[i]= null;
        }
    }
}
