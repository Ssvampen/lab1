package src;

import java.util.*;

/**
 * Represents an storage solution for objects.
 * @param <T> Type that this storage will store.
 */
public class ObjectStorage<T> implements Iterable<T> {
    private final List<T> objects;
    private final int maxObjects;

    /**
     * Maximum amount of objects that this storage will have
     * @param maxObjects Maximum amount of objects
     */
    public ObjectStorage(int maxObjects) {
        this.maxObjects = maxObjects;
        this.objects = new ArrayList<>();
    }

    /**
     * Adds an object to this storage.
     * Fails if the storage has reached maximum capacity.
     * @param object Object to add.
     */
    public void addObject(T object){
        if(this.getSize()  >= maxObjects) // Don't allow adding more than maxObjects
            return;

        objects.add(object);
    }

    /**
     * Removes the last added object in this storage.
     * @return The last added object or null if storage is empty.
     */
    public T removeLastObject(){
        if(objects.isEmpty())
            return null;

        T object = objects.get(objects.size() - 1);
        objects.remove(object);
        return object;
    }

    /**
     * Removes an object from this storage.
     * @param object Object to remove.
     */
    public void removeObject(T object){
        objects.remove(object);
    }

    @Override
    public Iterator<T> iterator() {
        return objects.iterator();
    }

    /**
     * Returns the amount of objects stored in this storage.
     * @return Amount of objects in storage.
     */
    public int getSize(){
        return objects.size();
    }

}