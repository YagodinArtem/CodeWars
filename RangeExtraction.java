import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RangeExtraction {

    public static String rangeExtraction(int[] arr) {
        Integer[] sortedArr = IntStream.of(arr).sorted().boxed().toArray(Integer[]::new);
        System.out.println(getUpperArrays(sortedArr));
        return getUpperArrays(sortedArr);
    }

    public static String getUpperArrays(Integer[] arr){
        StringBuilder result = new StringBuilder();
        List<Integer> temp = new ArrayList<>();

        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] - arr[i+1] < -1) {
                temp.add(arr[i]);
                if (temp.size() == 1) result.append(temp.get(0)).append(",");
                if (temp.size() == 2) result.append(temp.get(0)).append(",").append(temp.get(1)).append(",");
                if (temp.size() > 2) result.append(temp.get(0)).append("-").append(temp.get(temp.size()-1)).append(",");
                temp.clear();
            }
            else
                temp.add(arr[i]);
        }
        temp.add(arr[arr.length-1]);
        if (temp.size() == 1) result.append(temp.get(0));
        if (temp.size() == 2) result.append(temp.get(0)).append(",").append(temp.get(1));
        if (temp.size() > 2) result.append(temp.get(0)).append("-").append(temp.get(temp.size()-1));
        temp.clear();
        return result.toString();
    }


    public static void main(String[] args) {
        //"-6,-3-1,3-5,7-11,14,15,17-20"
        rangeExtraction(new int[] {-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20});
        rangeExtraction(new int[] {-3,-2,-1,2,10,15,20,19,18,16});
        rangeExtraction(new int[] {-40,-39,-37,-34,-32,-29,-28,-25,-22,-21,-19,-17,-15,-12,-9,-5
                -15,-14,-12,-9,-7,-5,-3,0,3,4,7,8,10-13,15,16,19,21,23,24,27,30,33,35,36,38,41,42});
    }
}
