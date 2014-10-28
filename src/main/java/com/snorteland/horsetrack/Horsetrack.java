package com.snorteland.horsetrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.snorteland.horsetrack.model.CashInventory;
import com.snorteland.horsetrack.model.Race;
import com.snorteland.horsetrack.model.RacePosition;

/**
 * The main executable class for the horsetrack example program.
 */
public class Horsetrack {
	
	private CashInventory ci = new CashInventory();
	private Race race = new Race();
	
	/**
	 * Default Constructor.
	 */
	public Horsetrack() {
	}
	
	/**
	 * Restock the cash inventory.
	 */
	public void restock() {
		ci.restock();
	}
	
	/**
	 * Display the cash inventory.
	 */
	public void printCashInventory() {
		System.out.println(ci.toString());
	}
	
	/**
	 * Display the race lineup.
	 */
	public void printLineup() {
		System.out.println(race.toString());
	}
	
	/**
	 * Display an error string for invalid input.
	 * 
	 * @param inputString the invalid input
	 */
	public void invalidInput(String inputString) {
		System.out.println("Invalid Command: " + inputString);
	}
	
	/**
	 * Determine the correct output for a placed wager.
	 * 
	 * @param inputString the command line input string
	 */
	public void evaluateWinner(String inputString) {
		String[] inputs = inputString.split(" ");
		int position = Integer.valueOf(inputs[0]);
		
		int bet = 0;
		try {
			bet = Integer.valueOf(inputs[1]);
		} catch (NumberFormatException e) {
			System.out.println("Invalid Bet: " + inputs[1]);
			return;
		}
		
		if (!race.validNumber(position)) {
			System.out.println("Invalid Horse Number: " + position);
		} else if (race.getWinner() != position) {
			System.out.println("No Payout: " + race.getRacePosition(position).getHorseName());
		} else {
			RacePosition rp = race.getRacePosition(position);
		    int payout = rp.getOdds() * bet;
		    if (ci.sufficientFunds(payout)) {
		    	System.out.println("Payout: " + rp.getHorseName() + ",$" + payout);
		    	ci.dispenseFunds(payout);
		    } else {
		    	System.out.println("Insufficient Funds: " + payout);
		    }
		}
	}
	
	/**
	 * Set a new winner for the race
	 * 
	 * @param inputString the command line input string
	 */
	public void setWinner(String inputString) {
		String[] inputs = inputString.split(" ");
	    int winner = Integer.valueOf(inputs[1]);
	    race.setWinner(winner);
	}
	
    public static void main(String[] args) {
    	
    	Horsetrack track = new Horsetrack();
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean done = false;
        try {
			do {
				// print the cash and race after every input
		    	track.printCashInventory();
		    	track.printLineup();
				String input = br.readLine();
				if (input.equalsIgnoreCase("q")) {
					done = true;
				} else if (input.equalsIgnoreCase("r")) {
					track.restock();
				} else if (input.matches("[w,W] [1-7]")) {
					track.setWinner(input);
				} else if (input.matches("[0-9]+ [0-9]+.?[0-9]*")) {
					track.evaluateWinner(input);
				} else {
					track.invalidInput(input);
				}
				
			} while (!done);
		} catch (IOException e) {
			// Do nothing, exit
		}
    }

}
