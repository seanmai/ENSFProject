package ensf_project.src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * Contains method to test toString method of course class
 * 
 * @author Wafa Anam, Sean Mai, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
class CourseJUnitTest {

	@Test
	void test() {
		Course c = new Course(1, 1, "ENSF409", true);
		String output = "ENSF409   (active)";
		assertEquals(c.toString(), output);
	}

}
