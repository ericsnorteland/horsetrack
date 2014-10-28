package com.snorteland.horsetrack.model;

/**
 * Contains information about a denomination in
 * the cash inventory.
 */
public class Denomination {
	private int value = 0;
	private int count = 0;
	private String label;
	
	/**
	 * Default Constructor.
	 */
	public Denomination() {
		
	}
	
	/**
	 * Parameterized Constructor.
	 * 
	 * @param value
	 * @param count
	 * @param label
	 */
	public Denomination(int value, int count, String label) {
		super();
		this.value = value;
		this.count = count;
		this.label = label;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
}
