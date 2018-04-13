package ensf_project.src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * Contains method to test search user id sql query
 * 
 * @author Wafa Anam, Sean Mai, Matt Kadatz
 * @version 1.0
 * @since April 9, 2018
 */
class DBJUnitTest {

	@Test
	void test() {
		DBManager db = new DBManager();
		User mdamon = new User(0, "mattdamon", "them_apples@gmail.com", "Matt", "Damon", "P");
		User output = db.searchUserByID(0);
		assertEquals(mdamon, output);
	}

}
