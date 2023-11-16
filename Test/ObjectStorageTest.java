package Test;

import src.ObjectStorage;
import src.Volvo240;

import org.junit.Test;
import org.junit.Before;
import src.Volvo240;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ObjectStorageTest {
    private ObjectStorage<Volvo240> storage;
    private int maxObjects;

    @Before
    public void ObjectStorageInitializer(){
        maxObjects = 5;
        storage = new ObjectStorage<Volvo240>(maxObjects);
    }

    @Test
    public void testMaxCars(){
        for(int i = 0; i < maxObjects+1; i++){
            storage.addObject(new Volvo240());
        }
        assertEquals(maxObjects, storage.getSize());
    }
}
