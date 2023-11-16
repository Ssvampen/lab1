package src;

import java.util.*;

public class ObjectStorage<T> implements Iterable<T> {
    private final List<T> objects;
    private final int maxObjects;

    public ObjectStorage(int maxObjects) {
        this.maxObjects = maxObjects;
        this.objects = new ArrayList<>();
    }

    public void addObject(T object){
        if(this.getSize()  >= maxObjects) // Don't allow adding more than maxObjects
            return;

        objects.add(object);
    }

    public T removeLastObject(){
        if(objects.isEmpty())
            return null;

        T object = objects.get(objects.size() - 1);
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