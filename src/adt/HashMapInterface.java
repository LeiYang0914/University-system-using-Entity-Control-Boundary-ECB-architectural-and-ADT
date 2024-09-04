/**
*
* @author Chan Li Yang 
* 
*/

package adt;

public interface HashMapInterface<K, V> {

	//add new entry into hashmap
    public void put(K key, V value);
    
   //retrieve the unique key 
    public K getKey(int index);
	
  //retrieve the entry data using the corresponding unique key 
    public V getValue(K key);

   //remove entry from hashmap
    public V remove(K key);
    
    //check whether the unique key input exist in the hashmap or not
    public boolean containsKey(K key);

    public int size();
    
    public int capacity(); 

    //check whether the hashmap is empty or not
    public boolean isEmpty();

  //check whether the hashmap is full or not
    public boolean isFull();

    //clear all the entry in the hashmap
    public void clear();

}
