package search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-06-06 14:45
 * @Description
 */

public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 8, 3, 5, 3, 7, 3};
        List<Integer> list = search(arr, 2);
        list.forEach(System.out::println);
    }

    public static List<Integer> search(int[] arr, int value) {
         List<Integer> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value)
                res.add(i);
        }
        if (res.isEmpty())
            res.add(-1);
        return res;
    }
}
