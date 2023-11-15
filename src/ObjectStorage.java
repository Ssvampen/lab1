package src;

import java.util.*;

public class ObjectStorage<T> implements Iterable<T> {
    private List<T> objects;
    private int maxObjects;

    public ObjectStorage(int maxObjects) {
        this.maxObjects = maxObjects;
    }

    public void addObject(T object){
        objects.add(object);
    }

    public T removeLastObject(){
        T object = objects.get(-1);
        objects.remove(object);
        return object;
    }

    public void removeObject(T object){
        objects.remove(object);
    }

    @Override
    public Iterator<T> iterator() {
        return objects.iterator();
    }

    public int getSize(){
        return objects.size();
    }

}