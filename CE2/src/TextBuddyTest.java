
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
	public void testA1AddItem() {
		
		String testCaseInput = "add gorilla";
		String expectedOutput = "added to mytextfile.txt: \"gorilla\"";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}

	@Test
	public void testA2SortItem() {
		
		String testCaseInput = "sort";
		String expectedOutput = "records sorted.";
			
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testA3DisplayListOfItem() {
		
		String testCaseInput = "display";
		String expectedOutput = "1. gorilla" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testA4AddItem() {
		
		String testCaseInput = "add anaconda";
		String expectedOutput = "added to mytextfile.txt: \"anaconda\"";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testA5AddItem() {
				
		String testCaseInput = "add zebra";
		String expectedOutput = "added to mytextfile.txt: \"zebra\"";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testA6SortItem() {
						
		String testCaseInput = "sort";
		String expectedOutput = "records sorted.";
			
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testA7DisplayListOfItem() {
		
		String testCaseInput = "display";
		String expectedOutput = "1. anaconda" + newLine;
		expectedOutput += "2. gorilla" + newLine;
		expectedOutput += "3. zebra" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testA8DeleteItem() {	
		String testCaseInput = "delete 1";
		String expectedOutput = "deleted from mytextfile.txt: \"anaconda\"";
							
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testA9AddItem() {
						
		String testCaseInput = "add cobra";
		String expectedOutput = "added to mytextfile.txt: \"cobra\"";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testB1DisplayListOfItem() {
										
		String testCaseInput = "display";
		String expectedOutput = "1. gorilla" + newLine;
		expectedOutput += "2. zebra" + newLine;
		expectedOutput += "3. cobra" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testB2SortItem() {
								
		String testCaseInput = "sort";
		String expectedOutput = "records sorted.";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
}
