package com.artcode.training.week1.hash;

import java.util.*;

/**
 * Created by dmitriyg on 9/29/2015.
 */
public class MyMap<K, V> implements Map<K, V>, Iterator {

    public static final int DEFAULT_TABLE_SIZE = 16;

    private Node[] table;
    private int size;
    private double loadFactor = 0.75;

    public MyMap(int capacity) {
        table = new Node[capacity];
    }

    public MyMap(int capacity, double loadFactor) {
        table = new Node[capacity];
        this.loadFactor = loadFactor;
    }

    public MyMap() {
        table = new Node[DEFAULT_TABLE_SIZE];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return keySet().contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public V get(Object key) {
        int hash = key.hashCode();
        int position = Math.abs(hash % table.length);

        if (table[position] == null) {
            return null;
        } else {
            Node iter = table[position];
            while (iter.next != null) {
                if (iter.key.equals(key)) {
                    return (V) iter.value;
                }
                iter = iter.next;
            }
            return iter.key.equals(key) ? (V) iter.value : null;
        }
    }

    @Override
    public V put(K key, V value) {
        if (size == size * loadFactor) rehash();
        int hash = key.hashCode();
        int position = Math.abs(hash % table.length);

        if (table[position] == null) {
            table[position] = new Node(key, value, null);
        } else {
            Node iter = table[position];
            while (iter.next != null) {
                if (iter.key.equals(key)) {
                    return (V) iter.setValue(value);
                }
                iter = iter.next;
            }
            if (iter.key.equals(key)) {
                return (V) iter.setValue(value);
            }
            iter.next = new Node(key, value, null);
        }
        size++;
        return null;
    }

    private void rehash() {
        Node[] oldTable = table;
        table = new Node[table.length * 2];
        size = 0;
        for (Node node : oldTable) {
            if (node != null) {
                Node iter = node;
                while (iter.next != null) {
                    put((K) iter.key, (V) iter.value);
                    iter = iter.next;
                }
                put((K) iter.key, (V) iter.value);
            }
        }
    }

    @Override
    public V remove(Object key) {
        int hash = key.hashCode();
        int position = Math.abs(hash % table.length);

        if (table[position] == null) {
            return null;
        } else {
            Node iter = table[position];
            while (iter.next != null) {
                if (iter.next.key.equals(key)) {
                    size--;
                    V old = (V) iter.next.value;
                    iter.next = iter.next.next;
                    return old;
                }
                iter = iter.next;
            }
            if (iter.key.equals(key)) {
                table[position] = null;
                size--;
                return (V) iter.value;
            }
            return null;
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        table = new Node[table.length];
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Node node : table) {
            if (node != null) {
                Node iter = node;
                while (iter.next != null) {
                    keys.add((K) iter.key);
                    iter = iter.next;
                }
                keys.add((K) iter.key);
            }
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (Node node : table) {
            if (node != null) {
                Node iter = node;
                while (iter.next != null) {
                    values.add((V) iter.value);
                    iter = iter.next;
                }
                values.add((V) iter.value);
            }
        }
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> keys = new HashSet<>();
        for (Node node : table) {
            if (node != null) {
                Node iter = node;
                while (iter.next != null) {
                    keys.add(new Node(iter.key, iter.value, null));
                    iter = iter.next;
                }
                keys.add(new Node(iter.key, iter.value, null));
            }
        }
        return keys;
    }

    @Override
    public String toString() {
        return "MyMap{" +
                "table=" + Arrays.toString(table) +
                '}';
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    private static class Node implements Entry {
        Object key;
        Object value;
        Node next;

        public Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public Object setValue(Object value) {
            Object old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
}
