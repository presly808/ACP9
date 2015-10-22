package ua.artcode.week1.hashstructure;

import java.util.*;

/**
 *
 */
public class MyHashMap<K,V> implements Map<K,V> {

    public static final int DEFAULT_TABLE_SIZE = 16;

    private Node[] table;
    private int size;
    private double loadFactor = 0.75;


    public MyHashMap() {
        table = new Node[DEFAULT_TABLE_SIZE];
    }

    public MyHashMap(int capasity){
        table = new Node[capasity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int hash = key.hashCode();
        int position = Math.abs(hash % table.length);

        Node iter = table[position];
        while(iter != null){
            if(iter.key.equals(key)){
                return true;
            }
            iter = iter.next;
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {

        if(!containsKey(key)){
            return null;
        }

        int hash = key.hashCode();
        int position = Math.abs(hash % table.length);

        Node iter = table[position];
        while(iter != null){
            if(iter.key.equals(key)){
                return (V) iter.val;
            }
            iter = iter.next;
        }

        return null;
    }

    @Override// K must override hashCode and equals
    public V put(K key, V value) {


        if(containsKey(key)){
            // can not add
            // replace
        }


        int hash = key.hashCode();
        int position = Math.abs(hash % table.length);

        if(table[position] == null){
            table[position] = new Node(key,value, null);
        } else {
            Node iter = table[position];
            while(iter.next != null){
                iter = iter.next;
            }
            iter.next = new Node(key,value, null);
        }

        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Set<? extends K> keys = m.keySet();
        for (K key : keys) {
            V value = m.get(key);
            put(key,value);
        }

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        Set<K> vakues = new HashSet<>();
        Iterator<Node> nodeIterator = new MyHashMapTableIterator();
        while(nodeIterator.hasNext()){
            vakues.add((K) nodeIterator.next().key);
        }
        return vakues;

    }

    @Override
    public Collection<V> values() {

        Collection<V> vakues = new LinkedList<>();
        Iterator<Node> nodeIterator = new MyHashMapTableIterator();
        while(nodeIterator.hasNext()){
            vakues.add((V) nodeIterator.next().val);
        }

        return vakues;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K,V>> entries = new HashSet<>();
        Iterator<Node> nodeIterator = new MyHashMapTableIterator();
        while(nodeIterator.hasNext()){
            entries.add(nodeIterator.next());
        }

        return entries;
    }

    private class MyHashMapTableIterator implements Iterator<Node>{

        private Node curr;
        private int index;

        public MyHashMapTableIterator() {
            findFirstNotNull();
        }

        private void findFirstNotNull() {
            for (int i = index; i < table.length ; i++) {
                if(table[i] != null){
                    index = i;
                    curr = table[i];
                    return;
                }
            }

            // if no first not null
            curr = null;
        }


        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Node next() {
            Node forRet = curr;

            if(curr.next != null){
                curr = curr.next;
            } else {
                findFirstNotNull();
            }


            return forRet;
        }
    }



    private static class Node implements Entry {

        Object key;
        Object val;
        Node next;

        public Node(Object key, Object val, Node next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return val;
        }

        @Override
        public Object setValue(Object value) {
            Object old = val;
            val = value;
            return old;
        }
    }
}
