package com.apress.ravi.chapter6.SpringTesting;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleCalculatorTests {

	private SimpleCalculator simpleCalculator;

	@Before
	public void setup() {
		simpleCalculator = new SimpleCalculator();
	}

	@Test
	public void verifyAdd() {
		long sum = simpleCalculator.addOperation(2, 1);
		assertEquals(2, sum);
	}

	@After
	public void teardown() {
		simpleCalculator = null;
	}
}
