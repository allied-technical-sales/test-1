package com.sample.allied;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LinuxTailCommandFactory {
	private static final String COMMAND ="Command";
	private static final String OPTION ="Option";
	private static final String FLAG ="Flag";
	private static final String NUMBEROFLINE ="Numberofline";
	
	public static void main(String[] args) {
		String input = "";
		do {
			Scanner reader = new Scanner(System.in);
			System.out.println("Please enter a command : ");
			String inputCommand = reader.nextLine();
			String[] inputs = inputCommand.split(" ");
			if(inputs.length<2){
				System.out.println("Command is not valid");
			}
			else{
				Map<String, Object> inputmap = new HashMap<String, Object>();
				inputmap.put(NUMBEROFLINE, 10);
				inputmap.put(FLAG, false);
				List<String> fileNames = new ArrayList<String>();
				for (int i = 0; i < inputs.length; i++) {
					if (i == 0) {
						inputmap.put(COMMAND, inputs[i]);
					} else {
						if (inputs[i].startsWith("-")) {
							inputmap.put(OPTION, inputs[i]);
						} else {
							if (inputs[i].startsWith("+")) {

								inputmap.put(FLAG, true);
							}
							try {
								int noofline = Integer.parseInt(inputs[i]);
								inputmap.put(NUMBEROFLINE, noofline);
							} catch (NumberFormatException nfe) {
								fileNames.add(inputs[i]);
							}
						}

					}
				}
				if(fileNames.size()>0)
					performeOperation(inputmap, fileNames);
			}
			System.out.println("Want to conti...Y/N");
			input = reader.next();

		} while ("y".equalsIgnoreCase(input));
	}

	static void performeOperation(Map<String, Object> inputmap, List<String> fileNames) {

		String sCommand = (String) inputmap.get(COMMAND);
		String sOption = (String) inputmap.get(OPTION);
		if (sOption == null)
			sOption = "-n";
		boolean bFlag = (boolean) inputmap.get(FLAG);
		int numberOfLines = (int) inputmap.get(NUMBEROFLINE);

		switch (sCommand) {
		case "tail":
			switch (sOption) {
			case "-n":
				for (String file : fileNames) {
					System.out.println("-----------------< "+  file +" >------------------");
					functionn(file, numberOfLines, bFlag);
				}
				break;
			default:
				System.out.println("Not a Valid aOption");
				break;
			}
			break;
		default:
			System.out.println("Not a Valid Command");
			break;
		}

	}

	public static StringBuilder functionn(String fileName, long numberOfLines, boolean flag) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		FileReader fr = null;
		try {
			long lineno = 0;
			String str;
			String outstr;
			Map<Long, String> strmap = new HashMap<Long, String>();
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			while ((str = br.readLine()) != null) {
				strmap.put(lineno + 1, str);
				lineno++;
			}
			long startPosition = 0;
			if (flag) {
				startPosition = numberOfLines;
			} else {
				startPosition = lineno - numberOfLines + 1;
			}

			while (startPosition <= lineno) {
				if (strmap.containsKey(startPosition)) {
					outstr = (String) strmap.get(startPosition);
					sb.append(outstr);
					System.out.println(outstr);
				}
				startPosition++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sb;
	}
}