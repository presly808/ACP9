package ua.artcode.week1.hashstructure;

import java.util.Map;
import java.util.Set;

/**
 * Created by serhii on 01.10.15.
 */
public class MyHashMapTest {


    public static void main(String[] args) {
        Map<String,Integer> map = new MyHashMap<>();

        map.put("Andriy",35);
        map.put("Oleg",28);
        map.put("Olia", 24);


        map.size();

        Set<String> key = map.keySet();
    }
}
