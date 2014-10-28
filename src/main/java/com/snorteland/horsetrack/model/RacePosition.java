package com.snorteland.horsetrack.model;

/**
 * RacePosition is simple grouping of a horse name
 * and that horse's odds of winning.
 */
public class RacePosition {

	private String horseName;
	private int odds;
	
	/**
	 * Parameterized constructor.
	 * 
	 * @param horseName the name of the horse.
	 * @param odds the odds for the horse
	 */
	public RacePosition(String horseName, int odds) {
		super();
		this.horseName = horseName;
		this.odds = odds;
	}
	
	/**
	 * Accessor for horseName
	 * 
	 * @return the name of the horse.
	 */
	public String getHorseName() {
		return horseName;
	}
	
	/**
	 * Mutator for horseName
	 * @param horseName
	 */
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}
	
	/**
	 * Accessor for odds
	 * 
	 * @return odds
	 */
	public int getOdds() {
		return odds;
	}
	
	/**
	 * Mutator for odds
	 * @param odds
	 */
	public void setOdds(int odds) {
		this.odds = odds;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder(horseName);
		builder.append(",").append(odds);
		return builder.toString();
	}
}
