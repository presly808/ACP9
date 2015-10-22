package ua.artcode.homeWork_1.iMap;

import java.util.Map;

public class TestMyMap {
    public static void main(String[] args) {

        Map<Integer, Integer> map = new MyMap<>();

        System.out.println(map.put(1, 38));
        System.out.println(map.put(2, 55));
        System.out.println(map.put(2, 20));
        System.out.println(map.put(4, 40));

        System.out.println(map);
        System.out.println(map.size());
        System.out.println(map.remove(40));

        System.out.println(map.containsKey(4));
        System.out.println(map.containsValue(15));
        System.out.println(map.containsValue(20));

        map.clear();
        System.out.println(map.size());
        System.out.println(map);
    }
}
