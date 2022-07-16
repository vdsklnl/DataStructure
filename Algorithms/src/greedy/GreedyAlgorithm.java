package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author vdsklnl
 * @create 2022-06-15 10:35
 * @Description
 */

public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建集合以键值对形式存放
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //创建每个HashSet
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //放入集合
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas，存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //ArrayList，存放选择的电台集合
        ArrayList<String> list = new ArrayList<>();

        //定义临时集合，存放未覆盖地区和电台地区交集
        HashSet<String> tempSet = new HashSet<>();

        //定义maxKey，指向交集最大电台key，若不为null，加入list
        String maxKey;
        while (allAreas.size() != 0) { //不为0表示还有未覆盖区域
            //每次循环后，需要清空maxKey
            maxKey = null;
            //遍历broadcasts，取出对应key
            for (String key:broadcasts.keySet()) {
                //将tempSet清空
                tempSet.clear();
                //当前key对应覆盖地区集合
                HashSet<String> set = broadcasts.get(key);
                tempSet.addAll(set);
                //求出覆盖地区交集，并赋给tempSet
                tempSet.retainAll(allAreas);
                //当前集合交集大于maxKey指向集合交集，则重置maxKey
                if (tempSet.size() > 0&&(maxKey == null||tempSet.size() > broadcasts.get(maxKey).size()))
                    maxKey = key;
            }
            //maxKey != null，加入集合中
            if (maxKey != null) {
                list.add(maxKey);
                //将maxKey指向地区从总地区中去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到选择结果：" + list);

    }
}
