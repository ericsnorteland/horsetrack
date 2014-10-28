package com.snorteland.horsetrack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Race is a representation of an individual race. It contains the winner of 
 * the race and a list of the horses and odds for the horses in the race.
 */
public class Race {
	
	private int winner;
	private List<RacePosition> racers = new ArrayList<RacePosition>();
	
	/**
	 * Default Constructor. Builds a default data set.
	 */
	public Race() {
		addHorse("That Darn Gray Cat", 5);
		addHorse("Fort Utopia", 10);
		addHorse("Count Sheep", 9);
		addHorse("Ms Traitour", 4);
		addHorse("Real Princess", 3);
		addHorse("Pa Kettle", 5);
		addHorse("Gin Stinger", 6);
		winner = 1;
	}
	
	/**
	 * Add a horse to the race.
	 * 
	 * @param name the name of the horse
	 * @param odds the odds for the horse
	 */
	public void addHorse(String name, int odds) {
		RacePosition newHorse = new RacePosition(name, odds);
		racers.add(newHorse);
	}
	
	/**
	 * Replace a horse at the specified position in the race.
	 * 
	 * @param position the position to place the horse in
	 * @param name the name of the horse
	 * @param odds the odds for the horse
	 */
	public void replaceHorse(int position, String name, int odds) {
		RacePosition newHorse = new RacePosition(name, odds);
		racers.add(position - 1, newHorse);
	}

	/**
	 * Accessor for the winner of the race.
	 * 
	 * @return the winner of the race
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * Mutator for the winner of the race
	 * 
	 * @param winner the winner to set
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	/**
	 * Accessor to get the horse and odds at a particular position.
	 * 
	 * @param position the position of interest
	 * @return the horse and odds at the position of interest
	 */
	public RacePosition getRacePosition(int position) {
		return racers.get(position - 1);
	}
	
	/**
	 * Determines whether the given race position is valid.
	 * 
	 * @param number the race position to determine validity
	 * @return whether the position is valid
	 */
	public boolean validNumber(int number) {
		if (number > 0 && number < (racers.size() + 1)) {
			return true;
		}
		return false;
	}
	
	/**
	 * ToString to output the race in the specified format.
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder("Horses:\n");
		for (int count = 0; count < racers.size(); count++) {
			builder.append(count + 1).append(",");
			builder.append(racers.get(count).toString());
			builder.append(winner == (count + 1) ? ",won\n" : ",lost\n");
		}
		return builder.toString();
	}
	
}
