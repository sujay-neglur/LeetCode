package Q103;

import java.io.BufferedReader;
import java.util.*;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x){
        val = x;
    }
}
class Solution{
    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        boolean leftToRight = true;
        Stack<TreeNode> current = new Stack<>();
        current.push(root);
        Stack<TreeNode> next = new Stack<>();
        while(!current.isEmpty()){
            ArrayList<Integer> levelResult = new ArrayList<>();
            if(leftToRight){
                while(!current.isEmpty()){
                    TreeNode top = current.pop();
                    System.out.println("Element popped is from if "+top.val);
                    if(top!=null){
                        levelResult.add(top.val);
                    }
                    if(top.left!=null){
                        next.push(top.left);
                    }
                    if(top.right!=null){
                        next.push(top.right);
                    }
                }
            }
            else{
                while(!current.isEmpty()){
                    TreeNode top = current.pop();
                    System.out.println("Element popped is from else "+top.val);
                    // levelResult.add(top.val);
                    if(top!=null){
                        levelResult.add(top.val);
                    }
                    if(top.right!=null){
                        next.push(top.right);
                    }
                    if(top.left!=null){
                        next.push(top.left);
                    }
                }
            }
            result.add(levelResult);
            leftToRight=!leftToRight;
            Stack<TreeNode> temp = current;
            current=next;
            next=temp;

        }
        return result;
    }
}
public class ZigzagLevelOrder {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) {
        TreeNode root = stringToTreeNode("[5,2,8,0,4,6,10,null,1,3,null,null,7,9,null]");
        List<List<Integer>> ret = new Solution().zigzagLevelOrder(root);
        String out = int2dListToString(ret);
        System.out.print(out);
    }
}