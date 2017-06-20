package com.empired.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.empired.util.ChallengeUtil;

public class CodingChallenge {

	/**
	 * Main method for executing Challenge Program
	 * @param args
	 */
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try{
			int rows = Integer.parseInt(ChallengeUtil.getInputfromUser("Please specify the number of rows of your worksheet",scanner));
			int cells = Integer.parseInt(ChallengeUtil.getInputfromUser("Please specify the number of cells of your worksheet",scanner));
			ArrayList<String>[][] work_sheet = new ArrayList[rows][cells];
			char alphabet = 'A';
			int cell_count = 1;
			for(int row = 0; row < work_sheet.length; row ++){
				alphabet = 'A';
				for(int cell = 0; cell < work_sheet[row].length; cell ++){
					work_sheet[row][cell] = new ArrayList<String>();
					
					work_sheet[row][cell].add(ChallengeUtil.getInputfromUser("Please specify the value of cell "+ alphabet + cell_count,scanner));
					alphabet++;
				}
				cell_count ++;
			}
			ChallengeUtil.processInputs(work_sheet, rows, cells);
		}catch(Exception ex){
			
		}finally{
			scanner.close(); 
		}
		
	}

}
