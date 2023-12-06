package Test;

import src.ObjectStorage;
import src.vehicle.Volvo240;

import org.junit.Test;
import org.junit.Before;

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
    public void testMaxVehicles(){
        for(int i = 0; i < maxObjects+1; i++){
            storage.addObject(new Volvo240());
        }
        assertEquals(maxObjects, storage.getSize());
    }
}
