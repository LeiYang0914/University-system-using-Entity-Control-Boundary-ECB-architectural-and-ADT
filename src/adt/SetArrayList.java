/**
 *
 * @author Soh 
 */

package adt;

public class SetArrayList <T extends Comparable<T>> implements SetArrayListInterface<T>{
    T[] setArray;
    int numberOfElements;
    private static final int DEFAULT_CAPACITY = 50;
    
    public SetArrayList(){
        setArray = (T[]) new Comparable[DEFAULT_CAPACITY];
        numberOfElements = 0;
    }
    
    @Override
    public boolean add(T newElement){
        for (int i = 0; i < numberOfElements; i++) {
            if (this.setArray[i].equals(newElement)) {
                return false;
            }
        }
        if(isFull()){
            doubleArray();
        }
        setArray[numberOfElements] = newElement;
        numberOfElements++;
        return true;
    }
    
    public void doubleArray(){
        T[] oldArray = setArray;
        setArray = (T[])new Object[2 * oldArray.length];
        System.arraycopy(oldArray, 0, setArray, 0, oldArray.length);
    }
    @Override
    public boolean remove(T anElement) {
        for (int i = 0; i < numberOfElements; i++) {
            if (setArray[i].equals(anElement)) {
                // perform the remove
                removeGap(i);
                numberOfElements--;
                return true;
            }
        }
        return false;
    }
    
    public void removeGap(int index) {
        for (int i = index; i < numberOfElements - 1; i++) {
            setArray[i] = setArray[i + 1];
        }
    }
    
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfElements - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
          setArray[index + 1] = setArray[index];
        }
      }
   
    
    @Override
    public void clear(){
        setArray = null;
        numberOfElements = 0;
    }
    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfElements)) {
          result = setArray[givenPosition - 1];
        }

        return result;
    }
    @Override
    public boolean contains(T anEntry) {
    boolean found = false;
    for (int index = 0; !found && (index < numberOfElements); index++) {
      if (anEntry.equals(setArray[index])) {
        found = true;
      }
    }
    return found;
  }
    @Override
    public int getNumberOfElements(){
        return numberOfElements;
    }
    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }
    @Override
    public boolean isFull(){
        return numberOfElements == setArray.length;
    }
}
