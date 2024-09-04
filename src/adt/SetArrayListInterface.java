package adt;
/**
 *
 * @author Soh
 */
public interface SetArrayListInterface<T> {
    public boolean add(T newElement);
    public boolean remove(T anElement);
    public void clear(); 
    public T getEntry(int givenPosition);
    public boolean contains(T anEntry);
    public int getNumberOfElements();
    public boolean isEmpty();
    public boolean isFull();
}