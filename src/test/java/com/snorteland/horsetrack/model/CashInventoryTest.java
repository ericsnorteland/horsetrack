package com.snorteland.horsetrack.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CashInventoryTest {
	
	CashInventory ci;
	
	@Before
	public void setup() {
		ci = new CashInventory();
	}

	/**
	 * Ensure calculation of total funds is correct.
	 */
	@Test
	public void testFunds() {
		assertEquals("Funds did not return the correct amount.", ci.funds(), 1360);
	}

	/**
	 * Ensure testing for sufficient funds is correct.
	 */
	@Test
	public void testSufficientFunds() {
		assertTrue("Should show sufficient funds and did not.", ci.sufficientFunds(0));
		assertTrue("Should show sufficient funds and did not.", ci.sufficientFunds(1360));
		assertFalse("Should show insufficient funds and did not.", ci.sufficientFunds(1370));
	}

	@Test
	public void testUseSmallerBills() {
		int[] values = new int[5];

		// initialize the payout array
		for (int count = 0; count < 5; count++) {
			values[count] = 0;
		}
		ci.useSmallerBills(0, 1100, values);
		assertEquals("Should have used 5 twenties.", 5, values[1]);
	}
	
	/**
	 * 30 dollars from no tens, 5 fives and 10 ones should
	 * come from 5 fives and 5 ones.
	 */
	@Test
	public void testUseSmallerBillNegative() {
		List<Denomination> denom = new ArrayList<Denomination>();
		denom.add(new Denomination(10, 0, "$10"));
		denom.add(new Denomination(5, 5, "$5"));
		denom.add(new Denomination(1, 10, "$1"));
		CashInventory cash = new CashInventory(denom);
		int[] values = new int[]{0,0,0};
		cash.useSmallerBills(0, 30, values);
		assertEquals(0, values[0]);
		assertEquals(5, values[1]);
		assertEquals(5, values[2]);
	}
	
	/** 
	 * 150 dollars from 10 tens, 10 fives, 10 ones.
	 */
	@Test
	public void testDispenseFunds150() {
		List<Denomination> denom = new ArrayList<Denomination>();
		denom.add(new Denomination(100, 0, "$100"));
		denom.add(new Denomination(20, 0, "$20"));
		denom.add(new Denomination(10, 10, "$10"));
		denom.add(new Denomination(5, 10, "$5"));
		denom.add(new Denomination(1, 10, "$1"));
		CashInventory cash = new CashInventory(denom);
		cash.dispenseFunds(150);
		assertEquals(0, cash.getBills(0));
		assertEquals(0, cash.getBills(1));
		assertEquals(0, cash.getBills(2));
		assertEquals(0, cash.getBills(3));
		assertEquals(10, cash.getBills(4));
	}
	
	/**
	 * 110 dollars from 10 tens, 10 fives, 10 ones.
	 */
	@Test
	public void testDispenseFunds110() {
		List<Denomination> denom = new ArrayList<Denomination>();
		denom.add(new Denomination(100, 0, "$100"));
		denom.add(new Denomination(20, 0, "$20"));
		denom.add(new Denomination(10, 10, "$10"));
		denom.add(new Denomination(5, 10, "$5"));
		denom.add(new Denomination(1, 10, "$1"));
		CashInventory cash = new CashInventory(denom);
		cash.dispenseFunds(110);
		assertEquals(0, cash.getBills(0));
		assertEquals(0, cash.getBills(1));
		assertEquals(0, cash.getBills(2));
		assertEquals(8, cash.getBills(3));
		assertEquals(10, cash.getBills(4));
	}

	/**
	 * 50 dollars from 10 tens, 10 fives, 10 ones.
	 */
	@Test
	public void testDispenseFunds50() {
		List<Denomination> denom = new ArrayList<Denomination>();
		denom.add(new Denomination(100, 0, "$100"));
		denom.add(new Denomination(20, 0, "$20"));
		denom.add(new Denomination(10, 10, "$10"));
		denom.add(new Denomination(5, 10, "$5"));
		denom.add(new Denomination(1, 10, "$1"));
		CashInventory cash = new CashInventory(denom);
		cash.dispenseFunds(50);
		assertEquals(0, cash.getBills(0));
		assertEquals(0, cash.getBills(1));
		assertEquals(5, cash.getBills(2));
		assertEquals(10, cash.getBills(3));
		assertEquals(10, cash.getBills(4));
	}

	/**
	 * Test sufficient funds non-exact change.
	 * 
	 * **Need additional direction for this case.**
	 */
	@Test
	public void testDispenseFundsSufficientFundsNonExactChange() {
		List<Denomination> denom = new ArrayList<Denomination>();
		denom.add(new Denomination(100, 0, "$100"));
		denom.add(new Denomination(20, 0, "$20"));
		denom.add(new Denomination(10, 0, "$10"));
		denom.add(new Denomination(5, 2, "$5"));
		denom.add(new Denomination(1, 0, "$1"));
		CashInventory cash = new CashInventory(denom);
		cash.dispenseFunds(3);
		assertEquals(0, cash.getBills(0));
		assertEquals(0, cash.getBills(1));
		assertEquals(0, cash.getBills(2));
		assertEquals(2, cash.getBills(3));
		assertEquals(0, cash.getBills(4));
	}

	/**
	 * Test dispensing $275 dollars.
	 */
	@Test
	public void testDispenseFunds275() {
		List<Denomination> denom = new ArrayList<Denomination>();
		denom.add(new Denomination(100, 10, "$100"));
		denom.add(new Denomination(20, 10, "$20"));
		denom.add(new Denomination(10, 10, "$10"));
		denom.add(new Denomination(5, 10, "$5"));
		denom.add(new Denomination(1, 10, "$1"));
		CashInventory cash = new CashInventory(denom);
		cash.dispenseFunds(275);
		assertEquals(8, cash.getBills(0));
		assertEquals(7, cash.getBills(1));
		assertEquals(9, cash.getBills(2));
		assertEquals(9, cash.getBills(3));
		assertEquals(10, cash.getBills(4));
	}
	
	/**
	 * Only three denominations. 
	 */
	@Test
	public void testUseSmallerFirstIteration() {
		List<Denomination> denom = new ArrayList<Denomination>();
		denom.add(new Denomination(10, 10, "$10"));
		denom.add(new Denomination(5, 10, "$5"));
		denom.add(new Denomination(1, 10, "$1"));
		CashInventory cash = new CashInventory(denom);
		int[] values = new int[]{0,0,0};
		cash.useSmallerBills(0, 150, values);
		assertEquals(10, values[0]);
		assertEquals(10, values[1]);
		assertEquals(0, values[2]);
	}
}
