package fr.junit.exemple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.junit.exemple.Calculator;

public class CalculatorTest {

	// On fait appel à l'objet
	Calculator calculator;

	// Initialisation de l'objet
	@BeforeEach
	public void setUp() {
		calculator = new Calculator();
	}

	@Test
	public void testMultiply() {
		assertEquals(20, calculator.multiply(5, 4));
	}

	@Test
	public void testMultiply_DifferentParameters() {
		assertEquals(25, calculator.multiply(5, 5));
	}

	@Test
	public void testMultiply_DifferentParameters_InTheSame() {
		assertEquals(32, calculator.multiply(4, 8));
		assertEquals(48, calculator.multiply(6, 8));
	}

	@Test
	public void testDivide() {
		assertEquals(2, calculator.divide(4, 2));
	}

	@Test
	public void testDivide_Zero() {
		assertEquals(0, calculator.divide(0, 4));
	}

	@Test
	public void testDivide_Zero_NoDivide() {
		assertEquals(0, calculator.divide(4, 0));
	}

}
