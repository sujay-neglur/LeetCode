package Q3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
       int maxLength = 0;
       HashSet<Character> visited = new HashSet<>();
       for(int i=0;i<s.length();i++){
           int length = 1;
//           System.out.println(i);
           visited.add(s.charAt(i));
           for(int j=i+1;j<s.length();j++){
               if(!visited.contains(s.charAt(j))){
                   visited.add(s.charAt(j));
                   length++;
               }
               else{
                   visited.clear();
                   if(length>maxLength){
                       maxLength = length;
                   }
                       break;
                   }
               if(length>maxLength){
                   maxLength = length;
               }
           }
       }
       return maxLength;
    }
}

public class LengthOfLongestSubstring {
//    public static String stringToString(String input) {
//        return JsonArray.readFrom("[" + input + "]").get(0).asString();
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String s = "dvdf";
        int ret = new Solution().lengthOfLongestSubstring(s);

        String out = String.valueOf(ret);

        System.out.print(out);
    }
}