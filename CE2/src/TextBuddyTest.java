
import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TextBuddyTest {
	
	private String newLine = "\n";
	private String[] fileNameArr;
	
	public TextBuddyTest() {
		
		String fileName = "mytextfile.txt";
		fileNameArr = new String[1];
		fileNameArr[0] = fileName;
	}
	
	private void executeUnitTestEquals(String[] fileNameArr, String testCaseInput, String expectedOutput) {
		
		String result = TextBuddy.executeJunitTest(fileNameArr, testCaseInput);
		assertEquals(expectedOutput, result);
	}
	
	@Test
	public void test1AddItem() {
		
		String testCaseInput = "add gorilla";
		String expectedOutput = "added to mytextfile.txt: \"gorilla\"";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}

	@Test
	public void test2SortItem() {
		
		String testCaseInput = "sort";
		String expectedOutput = "records sorted.";
			
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void test3DisplayListOfItem() {
		
		String testCaseInput = "display";
		String expectedOutput = "1. gorilla" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void test4AddItem() {
		
		String testCaseInput = "add anaconda";
		String expectedOutput = "added to mytextfile.txt: \"anaconda\"";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void test5AddItem() {
				
		String testCaseInput = "add zebra";
		String expectedOutput = "added to mytextfile.txt: \"zebra\"";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void test6SortItem() {
						
		String testCaseInput = "sort";
		String expectedOutput = "records sorted.";
			
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void test7DisplayListOfItem() {
		
		String testCaseInput = "display";
		String expectedOutput = "1. anaconda" + newLine;
		expectedOutput += "2. gorilla" + newLine;
		expectedOutput += "3. zebra" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
}
