package adt;
/**
 *
 * @author Ng Wei Khang 
 */
public interface SortedArrayListInterface <T extends Comparable<T>>{
    
    public boolean add(T newEntry);

    public boolean remove(T anEntry);

    public T getEntry(int givenPosition);
  
    public int contains(T anEntry);
  
    public void replace(T oldEntry,T newEntry);

    public void clear();

    public int getNumberOfEntries();

    public boolean isEmpty();

}
