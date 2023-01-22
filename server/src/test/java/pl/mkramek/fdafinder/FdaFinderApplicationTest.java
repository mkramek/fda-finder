package pl.mkramek.fdafinder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mkramek.fdafinder.api.controller.FinderController;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class FdaFinderApplicationTest {

	@Autowired
	FinderController controller;

	@Test
	void contextLoads() {
		then(controller).isNotNull();
	}

}
