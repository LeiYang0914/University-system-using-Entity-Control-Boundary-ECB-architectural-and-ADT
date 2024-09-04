package adt;

/**
*
* @author Ng Wei Khang
*/

public class SortedArrayList<T extends Comparable<T>> implements SortedArrayListInterface<T> {

  private T[] array;
  private int numberOfEntries;
  private static final int DEFAULT_CAPACITY = 25;

  public SortedArrayList() {
    this(DEFAULT_CAPACITY);
  }

  public SortedArrayList(int initialCapacity) {
    numberOfEntries = 0;
    array = (T[]) new Comparable[initialCapacity];
  }

  public boolean add(T newEntry) {
    int i = 0;
    while (i < numberOfEntries && newEntry.compareTo(array[i]) > 0) {
      i++;
    
    }
    makeRoom(i + 1);
    array[i] = newEntry;
    numberOfEntries++;
    return true;
  }

  public boolean remove(T anEntry) {
    int i = 0;
    while (i < numberOfEntries && anEntry.compareTo(array[i]) > 0) {
      i++;
    }
    
     if (i < numberOfEntries) {
        removeGap(i);
      }

      numberOfEntries--;//just ignore the behind one
      return true;
  }

  public void clear() {
    numberOfEntries = 0;
  }

  @Override
  public T getEntry(int givenPosition) {
    T result = null;

    if ((givenPosition >= 0) && (givenPosition <= numberOfEntries)) {
      result = array[givenPosition];
    }

    return result;
  }
  
  public void replace(T oldEntry,T newEntry){
      int i = 0;
      while (i < numberOfEntries && oldEntry.compareTo(array[i]) > 0) {
      i++;
        }
      array[i] = newEntry ;
  }
  
  public int contains(T anEntry) {
    
      if(isEmpty())
          return -1;
     
     return binarysearch(anEntry);
  }

  public int getNumberOfEntries() {
    return numberOfEntries;
  }

  public boolean isEmpty() {
    return numberOfEntries == 0;
  }

  public String toString() {
    String outputStr = "";
    for (int index = 0; index < numberOfEntries; ++index) {
      outputStr += array[index] + "\n";
    }

    return outputStr;
  }

  private boolean isArrayFull() {
    return numberOfEntries == array.length;
  }

  private void doubleArray() {
    T[] oldList = array;
    int oldSize = oldList.length;

    array = (T[]) new Object[2 * oldSize];

    for (int index = 0; index < oldSize; index++) {
      array[index] = oldList[index];
    }
  }

  private void makeRoom(int newPosition) {
    int newIndex = newPosition - 1;
    int lastIndex = numberOfEntries - 1;
    for (int index = lastIndex; index >= newIndex; --index) {
      array[index + 1] = array[index];
    }
  }

  private void removeGap(int givenPosition) {
    int removedIndex = givenPosition;
    int lastIndex = numberOfEntries - 1;

    
    for (int index = removedIndex; index < lastIndex; index++) {
      array[index] = array[index + 1];
    }
  }
  
  private int binarysearch(T search){
      int lower = 0;
      int higher = numberOfEntries - 1;
      
      while(lower<=higher){
          int middle=(lower+higher)/2;
          int campresult=array[middle].compareTo(search);
          
          if (campresult == 0) {
                return middle; // element found
            } else if (campresult < 0) {
                lower = middle + 1;
            } else {
                higher = middle - 1;
            }
        }

        return -1;//element not found
  }

}