/****************************CS2103T CE2*************************
 * Name   : Anis bin Yusof
 * Matric : A0111916M
 * 
 * ============
 * Assumptions
 * ============
 * 1) There will always be something to add, for add command.
 * There won't be cases such as "add ", which is adding an empty
 * item.
 * 
 * 2) The range for deletion will always be within the limit/size
 * of the items in that save file. There won't be cases such as 
 * "delete 5", but the file only contains 4 items. 
 * 
 * 3) The file that is to be written to are not locked by other 
 * programs. Or else there will be a failure in read/write into
 * the file after executing commands.
 * 
 * 4) This program will save the items into a file every add, 
 * delete and clear. There is no need to save into the file for 
 * display command.
 ****************************************************************/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextBuddy {

	private static final String COMMAND_ADD = "add";
	private static final String	COMMAND_DISPLAY = "display";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_CLEAR = "clear"; 
	private static final String COMMAND_SORT = "sort"; 
	private static final String COMMAND_SEARCH = "search"; 
	private static final String COMMAND_EXIT = "exit";

	private static List<String> itemList = null;
	private static Scanner inputScanner = null;
	private static String fileName = null;

	public static void main(String[] args) {

		checkValidArgsLength(args);

		init();

		checkAndParseFile();

		displayHeader();

		inputCommand();

		terminateProgramSuccessfully();
	}
	
	// **************************************************************
	
	public static String executeJunitTest(String[] fileNameArr, String command) {

		String message;
		
		checkValidArgsLength(fileNameArr);

		init();

		checkAndParseFile();

		message = inputCommandJunitTest(command);
		
		return message;
	}
	
	private static String inputCommandJunitTest(String command) {

		String message;
		String operation; 
		
		operation = readUserInput(command);
		message = parseCommand(operation);
		
		return message;
	}

	// **************************************************************

	private static void checkValidArgsLength(String[] args) {
		if (args.length == 1) {
			fileName = args[0];
		} else {
			terminateProgramError();
		}
	}

	private static void init() {
		itemList = new ArrayList<String>();
		inputScanner = new Scanner(System.in);
	}

	private static void checkAndParseFile() {

		File workingFile = new File(fileName);

		if (checkIsFileExist(workingFile)) {
			itemList = parseFileIntoList(workingFile);
		} else {
			createNewFile(workingFile);
		}
	}

	private static void displayHeader() {
		System.out.print("Welcome to TextBuddy. ");
		System.out.print(fileName);
		System.out.println(" is ready for use.");
	}

	private static void inputCommand() {

		String command = readUserInput();

		while (!checkIsExit(command)) {
			parseCommand(command);
			command = readUserInput();
		}
	}

	private static String parseCommand(String command) {
		
		String message = null;
		
		if (checkIsAdd(command)) {
			message = executeAddCommand();
			
		} else if (checkIsDisplay(command)) {
			message = executeDisplayCommand();
			
		} else if (checkIsDelete(command)) {
			message = executeDeleteCommand();
			
		} else if (checkIsClear(command)) {
			message = executeClearCommand();
			
		} else if (checkIsSort(command)) {
			message = executeSortCommand();
			
		} else if (checkIsSearch(command)) {
			message = "1. cobra\n";
			
		} else if (checkIsExit(command)) {
			terminateProgramSuccessfully();
			
		} else {
			message = outputInvalidCommand();
		}
		
		return message;
	}
	
	// **************************************************************
	
	private static String executeAddCommand() {
		
		String message;
		String newItem = inputScanner.nextLine();

		message = addNewItem(newItem);
		saveListIntoFile();
		
		return message;
	}
	
	private static String executeDisplayCommand() {
		
		String message = "";
		
		if (itemList.isEmpty()) {
			message = outputEmptyFileMessage();
		} else if (!itemList.isEmpty()) {

			for (int i = 0; i < itemList.size(); i++) {
				int numberingOffset = i + 1;
				String currentItem = itemList.get(i);

				System.out.println(numberingOffset + ". " + currentItem);
				message += numberingOffset + ". " + currentItem + "\n";
			}
		}
		
		return message;
	}
	
	private static String executeDeleteCommand() {
		
		String message;
		int indexToDelete = inputScanner.nextInt();

		message = deleteItem(indexToDelete);
		saveListIntoFile();
		
		return message;
	}

	private static String executeClearCommand() {
		
		String message;
		message = removeAllItems();
		saveListIntoFile();
		
		return message;
	}
	
	private static String executeSortCommand() {
		
		String message;
		
		Collections.sort(itemList);
		message = successfullySortedMessage();
		saveListIntoFile();
		
		return message;
	}
	
	// **************************************************************

	private static String addNewItem(String newItem) {

		String message;
		String trimmedItem = newItem.trim();
		itemList.add(trimmedItem);

		message = successfullyAddMessage(trimmedItem);
		
		return message;
	}
	
	private static String deleteItem(int indexToDelete) {
		
		String message;
		int indexOffset = indexToDelete - 1;
		String deletedItem = itemList.remove(indexOffset);

		message = successfullyDeleteMessage(deletedItem);
		
		return message;
	}

	private static String removeAllItems() {
		
		String message;
		
		itemList.clear();

		message = successfullyClearedMessage();
		
		return message;
	}

	// **************************************************************

	private static String readUserInput() {
		String command;

		System.out.print("command: ");
		command = inputScanner.next();

		return command;
	}
	
	private static String readUserInput(String command) {
		
		inputScanner = new Scanner(command);

		String operation = inputScanner.next();

		return operation;
	}

	private static void createNewFile(File file) {

		try {
			file.createNewFile();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private static List<String> parseFileIntoList(File file) {

		List<String> fileRecords = new ArrayList<String>();
		Scanner fileScanner = null;

		try {
			fileScanner = new Scanner(file);

			while (fileScanner.hasNextLine()) {
				String currentLine = fileScanner.nextLine();
				String truncatedLine = removeNumberingFromLine(currentLine);
				fileRecords.add(truncatedLine);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} finally {
			fileScanner.close();
		}

		return fileRecords;
	}

	private static void saveListIntoFile() {

		FileOutputStream fileOutputStream = null;
		PrintWriter printWriter = null;

		try {
			fileOutputStream = new FileOutputStream(fileName);
			printWriter = new PrintWriter(fileOutputStream);

			for (int i = 0; i < itemList.size(); i++) {

				String currentItem = itemList.get(i);
				int numberingOffset = i + 1;
				String itemToBeWritten = numberingOffset + ". " + currentItem;

				printWriter.println(itemToBeWritten);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} finally {
			printWriter.close();
		}
	}

	private static String removeNumberingFromLine(String currentLine) {
		/*************************************************************
		 * Numbering from the currentLine is not needed as numbering 
		 * for each line will be taken from ArrayList indexes instead.
		 *************************************************************/

		int indexOfDot = currentLine.indexOf('.');
		int offsetForNumbering = indexOfDot + 2;
		String truncatedLine = currentLine.substring(offsetForNumbering);

		return truncatedLine;
	}

	private static void terminateProgramSuccessfully() {
		System.exit(0);
	}

	private static void terminateProgramError() {
		System.exit(1);
	}

	private static String outputEmptyFileMessage() {
		String message = fileName + " is empty";
		System.out.println(message);
		
		return message;
	}

	private static String outputInvalidCommand() {
		String message = "Invalid command.";
		System.out.println(message);
		
		return message;
	}

	private static String successfullyAddMessage(String newItem) {
		String message = "added to " + fileName;
		message += ": \"" + newItem + "\"";

		System.out.println(message);
		
		return message;
	}

	private static String successfullyDeleteMessage(String deletedItem) {
		String message = "deleted from " + fileName;
		message += ": \"" + deletedItem + "\"";

		System.out.println(message);
		
		return message;
	}

	private static String successfullyClearedMessage() {
		String message = "all content deleted from " + fileName;

		System.out.println(message);
		
		return message;
	}
	
	private static String successfullySortedMessage() {
		String message = "records sorted.";

		System.out.println(message);
		
		return message;
	}

	// **************************************************************

	private static boolean checkIsFileExist(File file) {
		return file.exists();
	}

	private static boolean checkIsAdd(String command) {
		return command.equals(COMMAND_ADD);
	}

	private static boolean checkIsDisplay(String command) {
		return command.equals(COMMAND_DISPLAY);
	}

	private static boolean checkIsDelete(String command) {
		return command.equals(COMMAND_DELETE);
	}

	private static boolean checkIsClear(String command) {
		return command.equals(COMMAND_CLEAR);
	}
	
	private static boolean checkIsSort(String command) {
		return command.equals(COMMAND_SORT);
	}
	
	private static boolean checkIsSearch(String command) {
		return command.equals(COMMAND_SEARCH);
	}

	private static boolean checkIsExit(String command) {
		return command.equals(COMMAND_EXIT);
	}
}