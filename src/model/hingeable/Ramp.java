package src.model.hingeable;

import src.model.Entity;
import src.model.ObjectStorage;
import src.model.util.Vector;
import src.model.util.WeightClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Ramp that can have entities on it.
 */
public class Ramp implements Hingeable, Rampable, Entity {

    private boolean isRaised;

    // private final Stack<Entity> entities;
    private Vector position;
    private Vector direction;
    private final ObjectStorage<Entity> entities;
    private List<Entity> unloadedEntitys = new ArrayList<>();
    /** The list unloadedEntitys is used to prevent entities being placed on the
    * same spot when unloaded.
     * */


    /**
     * Constructs a ramp.
     * @param maxEntitys Maximum amount of entities that this ramp can hold.
     */
    public Ramp(int maxEntitys){
        entities = new ObjectStorage<Entity>(maxEntitys);
        this.position = Vector.zero();
        this.direction = Vector.NORTH;
    }

    /**
     * Adds a entity to the ramp.
     * The ramp must be down and the entity to add cannot be humongous or have a ramp.
     *
     * @param entity Entity to add
     */
    public void addEntity(Entity entity){
       if(!isEntityCloseEnough(entity) || !attacheableIsDown())
            return;

        // Can't add HUMONGOUS entities or ramped entities
        if(entity.getWeightClass() == WeightClass.HUMONGOUS || entity instanceof Rampable)
            return;

        entities.addObject(entity);
    }

    /**
     * Removes a entity from the ramp. Moves all entities in unloadedEntitys to prevent overlapping positions.
     */
    public void removeEntity(){
        if(!attacheableIsDown())
            return;

        Entity entity = entities.removeLastObject();
        unloadedEntitys.add(entity);

        for(Entity unloadedEntity : unloadedEntitys) {
            final Vector position = unloadedEntity.getPosition();
            Vector twoMetresBehind = new Vector(2, 2);

            Vector inverted = getDirection().invert();
            unloadedEntity.setPosition(position.add(inverted.multiply(twoMetresBehind)));
        }
    }

    private void deleteUnloadedEntitysList(){
        unloadedEntitys = new ArrayList<>();
    }

    /**
     * Moves all entities (independent of every individual entities enginepower...) to the same position of the parent entity
     */

    @Override
    public void raiseRamp(){
        isRaised = true;
        deleteUnloadedEntitysList();
    }

    private boolean isEntityCloseEnough(Entity entity){
        return entity.getPosition().calculateDistanceTo(this.getPosition()) < 1.0;
    }

    @Override
    public void lowerRamp(){
        isRaised = false;
    }

    @Override
    public int getEntityCount(){
        return entities.getSize();
    }

    @Override
    public boolean attacheableIsDown() {
        return !isRaised;
    }

    @Override
    public Vector getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector position) {
        this.position = position;
        for(Entity entity: entities){
            entity.setPosition(position);
        }
    }

    @Override
    public Vector getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Vector direction) {
        this.direction = direction;
        for(Entity entity: entities){
            entity.setDirection(direction);
        }
    }

    @Override
    public WeightClass getWeightClass() {
        return WeightClass.HUMONGOUS;
    }

}