package ensf_project.src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseJUnitTest {

	@Test
	void test() {
		Course c = new Course(1, 1, "ENSF409", true);
		String output = "ENSF409   (active)";
		assertEquals(c.toString(), output);
	}

}
