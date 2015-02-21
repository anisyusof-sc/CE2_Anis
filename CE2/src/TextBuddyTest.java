
import static org.junit.Assert.*;

import org.junit.Test;


public class TextBuddyTest {

	private String result = null;
	private String newLine = "\n";
	private String[] fileNameArr;
	
	public TextBuddyTest() {
		
		String fileName = "mytextfile.txt";
		fileNameArr = new String[1];
		fileNameArr[0] = fileName;
	}
	
	private void executeUnitTestEquals(String[] fileNameArr, String testCaseInput, String expectedOutput) {
		
		result = TextBuddy.executeJunitTest(fileNameArr, testCaseInput);
		assertEquals(expectedOutput, result);
	}
	
	@Test
	public void testAddItem1() {
		
		String testCaseInput = "add gorilla";
		String expectedOutput = "added to mytextfile.txt: \"gorilla\"";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}

	@Test
	public void testSortCommand1() {
		
		String testCaseInput = "sort";
		String expectedOutput = "records sorted.";
			
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testDisplaySortedList1() {
		
		String testCaseInput = "display";
		String expectedOutput = "1. gorilla" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
}
