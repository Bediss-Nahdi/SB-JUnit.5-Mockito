package fr.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JunitApiRestApplicationTests {

	// Juste pour vérifier que ça fonctionne
	public void demoTestMethod() {
		assertTrue(true);
	}

}
