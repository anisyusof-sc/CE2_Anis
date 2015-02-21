/****************************CS2103T CE1*************************
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
import java.util.Scanner;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TextBuddy {

	private static final String COMMAND_ADD = "add",
								COMMAND_DISPLAY = "display", 
								COMMAND_DELETE = "delete",
								COMMAND_CLEAR = "clear", 
								COMMAND_EXIT = "exit";

	private static ArrayList<String> itemList = null;
	private static Scanner inputScanner = null;
	private static String fileName = null;

	public static void main(String[] args) {

		checkValidArgsLength(args);

		init();

		checkFileExist();

		displayHeader();

		inputCommand();

		terminateProgramSuccessfully();
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

	private static void checkFileExist() {

		File workingFile = new File(fileName);

		if (checkIsFileExist(workingFile)) {
			itemList = parseFileIntoList(workingFile);
		} else if (!checkIsFileExist(workingFile)) {
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
			identifyCommand(command);
			command = readUserInput();
		}
	}

	private static void identifyCommand(String command) {

		if (checkIsAdd(command)) {
			String newItem = inputScanner.nextLine();

			addNewItem(newItem);
			saveListIntoFile();
		} else if (checkIsDisplay(command)) {
			displayContent();
		} else if (checkIsDelete(command)) {
			int indexToDelete = inputScanner.nextInt();

			deleteItem(indexToDelete);
			saveListIntoFile();
		} else if (checkIsClear(command)) {
			removeAllItems();
			saveListIntoFile();
		} else if (checkIsExit(command)) {
			terminateProgramSuccessfully();
		} else {
			outputInvalidCommand();
		}
	}

	// **************************************************************

	private static void addNewItem(String newItem) {

		String trimmedItem = newItem.trim();
		itemList.add(trimmedItem);

		successfullyAddMessage(trimmedItem);
	}

	private static void displayContent() {

		if (itemList.isEmpty()) {
			outputEmptyFileMessage();
		} else if (!itemList.isEmpty()) {

			for (int i = 0; i < itemList.size(); i++) {
				int numberingOffset = i + 1;
				String currentItem = itemList.get(i);

				System.out.println(numberingOffset + ". " + currentItem);
			}
		}
	}

	private static void deleteItem(int indexToDelete) {
		int indexOffset = indexToDelete - 1;
		String deletedItem = itemList.remove(indexOffset);

		successfullyDeleteMessage(deletedItem);
	}

	private static void removeAllItems() {
		itemList.clear();

		successfullyClearedMessage();
	}

	// **************************************************************

	private static String readUserInput() {
		String command;

		System.out.print("command: ");
		command = inputScanner.next();

		return command;
	}

	private static void createNewFile(File file) {

		try {
			file.createNewFile();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private static ArrayList<String> parseFileIntoList(File file) {

		ArrayList<String> listOfStrings = new ArrayList<String>();
		Scanner fileScanner = null;

		try {
			fileScanner = new Scanner(file);

			while (fileScanner.hasNextLine()) {
				String currentLine = fileScanner.nextLine();
				String truncatedLine = removeNumberingFromLine(currentLine);
				listOfStrings.add(truncatedLine);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} finally {
			fileScanner.close();
		}

		return listOfStrings;
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
		 * This method removeNumberingFromLine(String) is to remove any
		 * preceding numbers from a line. Numbering from the currentLine is not
		 * needed as numbering for each line will be taken from ArrayList
		 * indexes instead. e.g. input : "1. little brown fox" output:
		 * "little brown fox"
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

	private static void outputEmptyFileMessage() {
		String message = fileName + " is empty";
		System.out.println(message);
	}

	private static void outputInvalidCommand() {
		String message = "Invalid command.";
		System.out.println(message);
	}

	private static void successfullyAddMessage(String newItem) {
		String message = "added to " + fileName;
		message += ": \"" + newItem + "\"";

		System.out.println(message);
	}

	private static void successfullyDeleteMessage(String deletedItem) {
		String message = "deleted from " + fileName;
		message += ": \"" + deletedItem + "\"";

		System.out.println(message);
	}

	private static void successfullyClearedMessage() {
		String message = "all content deleted from " + fileName;

		System.out.println(message);
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

	private static boolean checkIsExit(String command) {
		return command.equals(COMMAND_EXIT);
	}
}