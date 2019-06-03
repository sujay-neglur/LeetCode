package Q973;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;


class Point{
    int xCo;
    int yCo;
    double distance;

    public Point(int xCo, int yCo, double distance) {
        this.xCo = xCo;
        this.yCo = yCo;
        this.distance = distance;
    }
}
class AnotherSolution{
    public void swap(Point [] points, int i, int j){
        Point temp = new Point(points[i].xCo,points[i].yCo,points[i].distance);
        points[i].xCo = points[j].xCo;
        points[i].yCo = points[j].yCo;
        points[i].distance = points[j].distance;
        points[j].xCo = temp.xCo;
        points[j].yCo = temp.yCo;
        points[j].distance = temp.distance;
    }

    public void print(Point [] points){
        for(int i=0; i<points.length;i++){
            System.out.println(points[i].xCo+" "+points[i].yCo+" "+points[i].distance);
        }
    }

    public int partition(Point [] points, int start, int end){
        int random = new Random().nextInt(end-start+1)+start;
        swap(points,random,start);
        int i=start;
        int j=end;
        Point pivot = points[start];
        while (true){
            while(points[i].distance<pivot.distance && i<j) i++;
            while (points[j].distance>pivot.distance && i<=j) j--;
            if(i>=j) break;
            swap(points,i,j);
        }
        swap(points,start,j);
        return j;
    }

    public void getKClosest(Point [] points,int k,int start,int end){
        if(end<=start) return;
        int partitionPoint = partition(points,start,end);
//        System.out.println(partitionPoint);
//        int leftLength = partitionPoint-start+1;
        if(k<partitionPoint-start+1){
            getKClosest(points,k,start,partitionPoint-1);
        }
        else{
            if(k>partitionPoint-start+1){
                getKClosest(points,k-(partitionPoint-start+1),partitionPoint+1,end);
            }

        }
    }


    public int [] [] getKClosest(int [][] points, int k){
        Point [] pointObjects  = new Point[points.length];
        int [][] results = new int[k][];
        for(int i=0;i<points.length;i++){
            pointObjects[i] = new Point(points[i][0],points[i][1], Solution.distance(points[i]));
        }
        getKClosest(pointObjects,k,0,pointObjects.length-1);

        for(int i=0;i<k;i++){
            results[i]= new int[]{pointObjects[i].xCo,pointObjects[i].yCo};
        }
        print(pointObjects);
        return results;
    }
}

class Solution {

    public static double distance(int [] coordinates){
        return Math.sqrt(coordinates[0]*coordinates[0]
                + coordinates[1]*coordinates[1]);
    }

    public int[][] kClosest(int[][] points, int K) {
        double [] distances = new double[points.length];
        int [][] closestPoints = new int[K][2];
        for(int i=0;i<points.length;i++){
            distances[i] = distance(points[i]);
        }
        Arrays.sort(distances);
        int pos = 0;
        double kthDistance = distances[K-1];
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
//        int [] [] points  = new int[][]{{1,2}, {2,3},{-1,0},{-2,-1},{-2,0},{-3,4},{1,3},{2,5},{4,6}};
        int [][] points = new int[][] {{100,-16},{31,45},{80,47},{41,59},{-59,-34},{-34,-76},{-5,-77},
                {35,40},{58,-30},{31,-96},{40,14},{-25,50},{37,-38},{-54,-31}};
        int K = 8;
//        int[][] ret = new Solution().kClosest(points, K);
        int [] [] ret = new AnotherSolution().getKClosest(points,K);
        String out = int2dArrayToString(ret);
        System.out.print(out);
    }
}