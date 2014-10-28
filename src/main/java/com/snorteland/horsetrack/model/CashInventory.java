package com.snorteland.horsetrack.model;

import java.util.ArrayList;
import java.util.List;

public class CashInventory {

	public static int DEFAULT_BILL_COUNT = 10;
	
	private List<Denomination> denominations = new ArrayList<Denomination>();
	
	/**
	 * Default Constructor.
	 */
	public CashInventory () {
		denominations.add(new Denomination(100, DEFAULT_BILL_COUNT, "$100"));
		denominations.add(new Denomination(20, DEFAULT_BILL_COUNT, "$20"));
		denominations.add(new Denomination(10, DEFAULT_BILL_COUNT, "$10"));
		denominations.add(new Denomination(5, DEFAULT_BILL_COUNT, "$5"));
		denominations.add(new Denomination(1, DEFAULT_BILL_COUNT, "$1"));
	}
	
	public CashInventory (List<Denomination> denominations) {
		this.denominations.clear();
		this.denominations.addAll(denominations);
	}
	
	/**
	 * Restock all the denominations to the default bill count.
	 */
	public void restock() {
		for (Denomination denomination : denominations) {
			denomination.setCount(DEFAULT_BILL_COUNT);
		}
	}
	
	/**
	 * ToString to output the inventory in the proscribed format.
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder("Inventory:\n");
		for (int count = denominations.size() - 1; count >= 0; count--) {
			builder.append(denominations.get(count).getLabel()).append(",");
			builder.append(denominations.get(count).getCount()).append("\n");
		}
		
		return builder.toString();
	}
	
	/**
	 * Determine the funds currently available
	 * 
	 * @return currently available funds
	 */
	public int funds() {
		int sum = 0;
		for (Denomination denomination : denominations) {
			sum += denomination.getCount() * denomination.getValue();
		}
		return sum;
	}
	
	/**
	 * Determine if there are sufficient funds to pay out a bet.
	 * 
	 * @param value the bet to see if there are sufficient funds
	 * 
	 * @return whether there are sufficient funds to pay out the bet
	 */
	public boolean sufficientFunds(int value) {
		return value <= funds();
	}
	
	/**
	 * Dispense the requested amount of money using the largest
	 * denominations first.
	 * 
	 * @param value the amount of money to dispense
	 */
	public void dispenseFunds(int value) {
		int[] values = new int[denominations.size()];

		// initialize the payout array
		for (int count = 0; count < denominations.size(); count++) {
			values[count] = 0;
		}
		
		// recursively try to dispense cash using the largest
		// denominations possible. Collect the bills dispensed
		// in the values array.
		useSmallerBills(0, value, values);

		// Print out the dispensed cash amounts 1 per line from 
		// smallest denomination to largest denomination
		System.out.println("Dispensing:");
		for (int count = denominations.size() - 1; count >= 0; count--) {
			System.out.println(denominations.get(count).getLabel() + "," + values[count]);
		}
	}
	
	/**
	 * Try and use smaller bills to give the correct payout. (i.e. there
	 * are no $100 bills, but there are 5 $20 bills)
	 * 
	 * @param position the current denomination to attempt to pay with
	 * @param change the ammount to try and generate with smaller bills
	 * @param values array of values for the payouts
	 */
	public void useSmallerBills(int position, int change, int[] values) {
		
		if (change == 0) {
			return;
		}
		
		if (position < denominations.size()) {
			Denomination current = denominations.get(position);
			int amount = current.getCount() * current.getValue();

			if (amount >= change) {
				if (change >= current.getValue()) {
					int temp = change / current.getValue();
					change = change % current.getValue();
					values[position] += temp;
					current.setCount(current.getCount() - temp);
					useSmallerBills(position + 1, change, values);
				} else {
					useSmallerBills(position + 1, change, values);
				}
			} else {
				change -= amount;
				values[position] = current.getCount();
				current.setCount(0);
				useSmallerBills(position + 1, change, values);
			}
		} else {
			//TODO Get clarification for this case.
			if (change > 0) {
				System.out.println("Unable to dispense exact change.");
			}
		}
			
	}
	
	/**
	 * Return the number of bills for a denomination.
	 * 
	 * @param position the position of the denomination
	 * @return the number of bills for that denomination
	 */
	public int getBills(int position) {
		return denominations.get(position).getCount();
	}
}


