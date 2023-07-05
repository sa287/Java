package Lab4.FoxesAndRabbits2;

import java.util.List;
import java.util.Random;

public class PopulationGenerator {
	private Field field;
	private List<Animal> animals;
	private static final double FOX_CREATION_PROBABILITY = 0.04;
	private static final double RABBIT_CREATION_PROBABILITY = 0.08;
	private static final double HUMAN_CREATION_PROBABILITY = 0.01;

	
	
	public PopulationGenerator(Field simulatorField, List<Animal> simulatorAnimals) {
		// TODO Auto-generated constructor stub
		field = simulatorField;
		animals = simulatorAnimals;
		
	}


	   void populate()
	    {
	        Random rand = Randomizer.getRandom();
	        field.clear();
	        for(int row = 0; row < field.getDepth(); row++) {
	            for(int col = 0; col < field.getWidth(); col++) {
	                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
	                    Location location = new Location(row, col);
	                    Fox fox = new Fox(true, field, location);
	                    animals.add(fox);
	                }
	                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
	                    Location location = new Location(row, col);
	                    Rabbit rabbit = new Rabbit(true, field, location);
	                    animals.add(rabbit);
	                }
	                else if(rand.nextDouble() <= HUMAN_CREATION_PROBABILITY) {
	                    Location location = new Location(row, col);
	                    Human human = new Human(true, field, location);
	                    animals.add(human);
	                }
	                // else leave the location empty.
	            }
	        }
	    }















}
