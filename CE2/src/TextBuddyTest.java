
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
		
		String testCaseInput = "add gorilla son";
		String expectedOutput = "added to mytextfile.txt: \"gorilla son\"";
		
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
		String expectedOutput = "1. gorilla son" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testA4AddItem() {
		
		String testCaseInput = "add anaconda son";
		String expectedOutput = "added to mytextfile.txt: \"anaconda son\"";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testA5AddItem() {
				
		String testCaseInput = "add zebra daughter";
		String expectedOutput = "added to mytextfile.txt: \"zebra daughter\"";
		
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
		String expectedOutput = "1. anaconda son" + newLine;
		expectedOutput += "2. gorilla son" + newLine;
		expectedOutput += "3. zebra daughter" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testA8DeleteItem() {	
		String testCaseInput = "delete 1";
		String expectedOutput = "deleted from mytextfile.txt: \"anaconda son\"";
							
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testA9AddItem() {
						
		String testCaseInput = "add cobra son";
		String expectedOutput = "added to mytextfile.txt: \"cobra son\"";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testB1DisplayListOfItem() {
										
		String testCaseInput = "display";
		String expectedOutput = "1. gorilla son" + newLine;
		expectedOutput += "2. zebra daughter" + newLine;
		expectedOutput += "3. cobra son" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testB2SortItem() {
								
		String testCaseInput = "sort";
		String expectedOutput = "records sorted.";
		
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testB3DisplayListOfItem() {
										
		String testCaseInput = "display";
		String expectedOutput = "1. cobra son" + newLine;
		expectedOutput += "2. gorilla son" + newLine;
		expectedOutput += "3. zebra daughter" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testB4SearchItem() {
										
		String testCaseInput = "search cobra";
		String expectedOutput = "1. cobra son" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testB5SearchItem() {
										
		String testCaseInput = "search son";
		String expectedOutput = "1. cobra son" + newLine;
		expectedOutput += "2. gorilla son" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testB6SearchItem() {
										
		String testCaseInput = "search cobra son";
		String expectedOutput = "1. cobra son" + newLine;
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
	
	@Test
	public void testB7SearchItem() {
										
		String testCaseInput = "search anaconda";
		String expectedOutput = "No record(s) found.";
					
		executeUnitTestEquals(fileNameArr, testCaseInput, expectedOutput);
	}
}
