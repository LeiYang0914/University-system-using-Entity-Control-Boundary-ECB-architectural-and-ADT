/**
*
* @author Chan Li Yang 
* 
*/

package adt;

public class HashMapImplementer<K, V> implements HashMapInterface<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry<K, V>[] buckets;
    private int size;

    public HashMapImplementer() {
        this(DEFAULT_CAPACITY);
    }

    public HashMapImplementer(int capacity) {
        buckets = new Entry[capacity];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key);

        if (buckets[index] == null) {
            buckets[index] = new Entry<>(key, value);
            size++;
        } else {
            Entry<K, V> current = buckets[index];
            while (current != null) {
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                    return;
                }
                current = current.getNext();
            }

            // Add new entry at the end of the chain
            Entry<K, V> newEntry = new Entry<>(key, value);
            newEntry.setNext(buckets[index]);
            buckets[index] = newEntry;
            size++;
        }

        if ((double) size / buckets.length > LOAD_FACTOR) {
            resize();
        }
    }
    
    @Override
    public K getKey(int index) {
        if (index < 0 || index >= buckets.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }

        for (Entry<K, V> entry : buckets) {
            if (entry != null && hash(entry.getKey()) == index) {
                return entry.getKey();
            }
        }

        return null;
    }


    @Override
    public V getValue(K key) {
        int index = hash(key);

        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }

        return null;
    }

    @Override
    public V remove(K key) {
        int index = hash(key);

        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = null;
        while (current != null) {
            if (current.getKey().equals(key)) {
                if (prev != null) {
                    prev.setNext(current.getNext());
                } else {
                    buckets[index] = current.getNext();
                }
                size--;
                return current.getValue();
            }
            prev = current;
            current = current.getNext();
        }

        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key);

        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }

        return false;
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
    public boolean isFull() {
        return (double) size / buckets.length >= LOAD_FACTOR;
    }

    @Override
    public void clear() {
        buckets = new Entry[buckets.length];
        size = 0;
    }
    
    @Override
    public int capacity() {
        return buckets.length;
    }

    //utility function
    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = new Entry[oldBuckets.length * 2];

        size = 0; // Reset size, we'll re-add all entries

        for (Entry<K, V> entry : oldBuckets) {
            while (entry != null) {
                put(entry.getKey(), entry.getValue());
                entry = entry.getNext();
            }
        }
    }

    private static class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }
}
