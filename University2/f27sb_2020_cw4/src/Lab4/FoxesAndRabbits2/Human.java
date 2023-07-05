package Lab4.FoxesAndRabbits2;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Human extends Animal{
	
	
// Characteristics shared by all humans (class variables).
    
    // The age at which a human can start to breed.
    private static final int BREEDING_AGE = 16;
    // The age to which a human can live.
    private static final int MAX_AGE = 110;
    // The likelihood of a human breeding.
    private static final double BREEDING_PROBABILITY = 0.15;
    // The maximum number of births.
    private static final int MAX_CHILDREN_BIRTHS = 4;
    // The food value of a single foxes. In effect, this is the
    // number of steps a human can go before it has to eat again.
    private static final int FOX_FOOD_VALUE = 30;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The human's age.
    private int age;
    // The human's food level, which is increased by eating foxes.
    private int foodLevel;

    /**
     * Create a human. A human can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the human will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */

	public Human(boolean randomAge,Field field, Location location) {
		super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(FOX_FOOD_VALUE);
        }
        else {
            age = 0;
            foodLevel = FOX_FOOD_VALUE;
        }
		// TODO Auto-generated constructor stub
	}

	@Override
	  
	    public void act(List<Animal> newHumans)
	    {
	        incrementAge();
	        incrementHunger();
	        if(isAlive()) {
	            giveBirth(newHumans);            
	            // Move towards a source of food if found.
	            Location newLocation = findFood();
	            if(newLocation == null) { 
	                // No food found - try to move to a free location.
	                newLocation = getField().freeAdjacentLocation(getLocation());
	            }
	            // See if it was possible to move.
	            if(newLocation != null) {
	                setLocation(newLocation);
	            }
	            else {
	                // Overcrowding.
	                setDead();
	            }
	        }
	    }

	    /**
	     * Increase the age. This could result in the fox's death.
	     */
	    private void incrementAge()
	    {
	        age++;
	        if(age > MAX_AGE) {
	            setDead();
	        }
	    }
	    
	    /**
	     * Make this fox more hungry. This could result in the fox's death.
	     */
	    private void incrementHunger()
	    {
	        foodLevel--;
	        if(foodLevel <= 0) {
	            setDead();
	        }
	    }
	    
	    /**
	     * Look for rabbits adjacent to the current location.
	     * Only the first live rabbit is eaten.
	     * @return Where food was found, or null if it wasn't.
	     */
	    private Location findFood()
	    {
	        Field field = getField();
	        List<Location> adjacent = field.adjacentLocations(getLocation());
	        Iterator<Location> it = adjacent.iterator();
	        while(it.hasNext()) {
	            Location where = it.next();
	            Object animal = field.getObjectAt(where);
	            if(animal instanceof Fox) {
	                Fox fox = (Fox) animal;
	                if(fox.isAlive()) { 
	                    fox.setDead();
	                    foodLevel = FOX_FOOD_VALUE;
	                    return where;
	                }
	            }
	        }
	        return null;
	    }
	    
	    /**
	     * Check whether or not this fox is to give birth at this step.
	     * New births will be made into free adjacent locations.
	     * @param newHumans A list to return newly born foxes.
	     */
	    private void giveBirth(List<Animal> newHumans)
	    {
	        // New foxes are born into adjacent locations.
	        // Get a list of adjacent free locations.
	        Field field = getField();
	        List<Location> free = field.getFreeAdjacentLocations(getLocation());
	        int births = breed();
	        for(int b = 0; b < births && free.size() > 0; b++) {
	            Location loc = free.remove(0);
	            Human young = new Human(false, field, loc);
	            newHumans.add(young);
	        }
	    }
	        
	    /**
	     * Generate a number representing the number of births,
	     * if it can breed.
	     * @return The number of births (may be zero).
	     */
	    private int breed()
	    {
	        int births = 0;
	        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
	            births = rand.nextInt(MAX_CHILDREN_BIRTHS) + 1;
	        }
	        return births;
	    }

	    /**
	     * A fox can breed if it has reached the breeding age.
	     */
	    private boolean canBreed()
	    {
	        return age >= BREEDING_AGE;
	    }

	

}
