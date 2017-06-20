package com.empired.util;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChallengeUtil {
	
	
	/**
	 * Method to prompt user and fetch inputs
	 * @param msg
	 * @param scanner
	 * @return
	 */
	public static String getInputfromUser(String msg, Scanner scanner){
		String userInput = "";
		System.out.println(msg + " : ");
		userInput = scanner.nextLine();
		
		return userInput;
	}
	
	/**
	 * Method to display the output worksheet
	 * @param cells
	 * @param work_sheet
	 */
	public static void displayOutput(int cells, ArrayList<String>[][] work_sheet){
		char alphabet = 'A';
		int cell_count = 1;
		for(int row = 0; row < work_sheet.length; row ++){
			if(row == 0 ){
				for(int alp_counter = 0; alp_counter<cells; alp_counter ++){
					System.out.print("\t" + alphabet);
					alphabet ++;
				}
				System.out.println("\n");
				
			}
			for(int cell = 0; cell < work_sheet[row].length; cell ++){
				if(cell == 0){
					System.out.print(cell_count + "\t");
					cell_count ++;
				}
				System.out.print(work_sheet[row][cell].get(0) + "\t");
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * Method to process data entered by the user into worksheet
	 * @param workSheet
	 * @param rows
	 * @param cells
	 */
	@SuppressWarnings("unchecked")
	public static void processInputs(ArrayList<String>[][] workSheet, int rows, int cells){
		Pattern specialChar = Pattern.compile ("[*+=\\-/%]");
		Pattern alphanumeric = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		ArrayList <String>[][] displaySheet = new ArrayList[rows][cells];
		for(int row = 0; row < workSheet.length; row ++){
			for(int cell = 0; cell < workSheet[row].length; cell ++){
				String data = workSheet[row][cell].get(0) + "";
				if(specialChar.matcher(data).find()){
					Matcher matcher = alphanumeric.matcher(data);
					int count = 0;
				     while (matcher.find()) {
				         count = count+1;
				         displaySheet[row][cell] = new ArrayList<String>();
						 displaySheet[row][cell].add(executeFormula(data, data.charAt(matcher.start()),displaySheet));
				         
				     }
					
				}else{
					displaySheet[row][cell] = new ArrayList<String>();
					displaySheet[row][cell].add(data);
				}
				
				
			}
		}
		displayOutput(cells, displaySheet);
	}
	
	/**
	 * Method to fetch cell data from worksheet and execute the formula 
	 * @param data
	 * @param operand
	 * @param workSheet
	 * @return
	 */
	public static String executeFormula(String data, char operand, ArrayList<String>[][] workSheet){
		String result = "";
		int firstVal = 0;
		int secondVal = 0;
		String[] dataSplit = null;
		
		switch(operand){
		case '+':
			data = data.replace("+", "#");
			dataSplit = data.split("#");
			firstVal = getCellValue(dataSplit[0], workSheet);
			secondVal= getCellValue(dataSplit[1], workSheet);
			result = (firstVal + secondVal) + "";
			break;
		case '-':
			data = data.replace("-", " -");
			dataSplit = data.split(Character.toString(operand));
			firstVal = getCellValue(dataSplit[0], workSheet);
			secondVal= getCellValue(dataSplit[1], workSheet);
			result = (firstVal - secondVal) + "";
			break;
		case '*':
			data = data.replace("*", "~");
			dataSplit = data.split("~");
			firstVal = getCellValue(dataSplit[0], workSheet);
			secondVal= getCellValue(dataSplit[1], workSheet);
			result = (firstVal * secondVal) + "";
			break;
		case '/':
			data = data.replace("/", " /");
			dataSplit = data.split(Character.toString(operand));
		    firstVal = getCellValue(dataSplit[0], workSheet);
			secondVal= getCellValue(dataSplit[1], workSheet);
			result = (firstVal / secondVal) + "";
			break;
		default:
			System.out.println("Unsupported Operation");
			break;
		}
		
		return result;
	}
	
	/**
	 * Method to fetch the value of a cell from the worksheet
	 * @param cellMetaData
	 * @param workSheet
	 * @return
	 */
	public static int getCellValue(String cellMetaData, ArrayList<String>[][] workSheet){
		int cellValue = 0;
		char[] metaData = cellMetaData.toCharArray();
		if(metaData.length > 0){
			if(metaData.length == 1){
				cellValue = Integer.parseInt(metaData[0] + "");
			}else{
				int row = Integer.parseInt(metaData[1] + "") - 1;
				int col = getCellNumber(metaData[0] +"");
				cellValue = Integer.parseInt(workSheet[row][col].get(0));
			}
		}
		return cellValue;
	}
	
	/**
	 * Method to convert Cell identifier Alphabet to corresponding cell number
	 * @param cellIndex
	 * @return
	 */
	public static int getCellNumber(String cellIndex){
		int cellNumber = 0;
		cellIndex = cellIndex.toUpperCase();
		switch (cellIndex){
		case "A":
			cellNumber = 0;
			break;
		case "B":
			cellNumber = 1;
			break;
		case "C":
			cellNumber = 2;
			break;
		case "D":
			cellNumber = 3;
			break;
		case "E":
			cellNumber = 4;
			break;
		case "F":
			cellNumber = 5;
			break;
		default:
			System.out.println("Invalid Cell Identifier");
			break;	
		}
		return cellNumber;
	}

}
