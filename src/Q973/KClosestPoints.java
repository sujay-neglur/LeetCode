package Q973;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

class AnotherSolution{
    public int [] [] getKClosest(int [][] points, int k){
        int [] distances = new int[points.length];
        for (int i=0;i<points.length;i++){
            distances[i] = Solution.distance(points[i]);
        }
        Arrays.stream(distances).forEach(System.out::println);
        int random = new Random().nextInt(points.length);

        return null;
    }
}

class Solution {

    public static int distance(int [] coordinates){
        return coordinates[0]*coordinates[0]
                + coordinates[1]*coordinates[1];
    }

    public int[][] kClosest(int[][] points, int K) {
        int [] distances = new int[points.length];
        int [][] closestPoints = new int[K][2];
        for(int i=0;i<points.length;i++){
            distances[i] = distance(points[i]);
        }
        Arrays.sort(distances);
        int pos = 0;
        int kthDistance = distances[K-1];
        for(int i=0;i<points.length;i++){
            if(distance(points[i])<=kthDistance){
                closestPoints[pos] = points[i];
                pos++;
                if(pos ==K) break;
            }
        }
        return closestPoints;
    }
}

public class KClosestPoints {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

//    public static int[][] stringToInt2dArray(String input) {
//        JsonArray jsonArray = JsonArray.readFrom(input);
//        if (jsonArray.size() == 0) {
//            return new int[0][0];
//        }
//
//        int[][] arr = new int[jsonArray.size()][];
//        for (int i = 0; i < arr.length; i++) {
//            JsonArray cols = jsonArray.get(i).asArray();
//            arr[i] = stringToIntegerArray(cols.toString());
//        }
//        return arr;
//    }

    public static String int2dArrayToString(int[][] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int[] item : array) {
            sb.append(Arrays.toString(item));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int [] [] points  = new int[][]{{1,2}, {2,3},{-1,0},{-2,-1},{-2,0},{-3,4}};
        int K = 2;
//        int[][] ret = new Solution().kClosest(points, K);
        int [] [] ret = new AnotherSolution().getKClosest(points,K);
        String out = int2dArrayToString(ret);
        System.out.print(out);
    }
}