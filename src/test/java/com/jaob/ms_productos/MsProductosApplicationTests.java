package com.jaob.ms_productos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class MsProductosApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void mainMethodRunsWithoutException() {
		assertDoesNotThrow(() -> MsProductosApplication.main(new String[]{}));
	}

}
