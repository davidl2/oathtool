/**
 * 
 */
package org.scouting.tools;

import java.util.Scanner;

/**
 * Simple program to help the user study the Scout Oath.
 * 
 * @author David Lee Lambert <davidl@lmert.com>
 */
public class OathTool {

	public static final String[] OATH_PARTS = {
			"On my honor, ",
			"I will do my best ",
			"to do my duty ",
			"to God and my country, ",
			"to help other people at all times, ",
			"to obey the Scout Law, ",
			"and to keep myself ",
			"physically strong, ",
			"mentally awake, ",
			"and morally straight."
	};
	
	public static String findNextPart(String currentPart) {
		String currentPartKey = currentPart.replaceAll(
				"\\s|,","").toUpperCase();
		
		for (int i=0; i<OATH_PARTS.length; i++) {
			String oathPartKey = OATH_PARTS[i].replaceAll(
					"\\s|,","").toUpperCase();
			
			if (oathPartKey.endsWith(currentPartKey)) {
				if (i+1==OATH_PARTS.length) {
					return "(that was the end)";
				}
				
				return OATH_PARTS[i+1];
			}
		}
		if (currentPartKey.equalsIgnoreCase("(start)")) {
			return OATH_PARTS[0];
		}
		return ("(that's not part of the Scout Oath)");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 1) {
			if (args[0].equals("-all")) {
				for (String part: OATH_PARTS) {
					System.out.print(part);
					if (part == OATH_PARTS[3] 
							|| part==OATH_PARTS[5]) {
						System.out.println();
					}
				}
				System.out.println();
			} else {
				System.out.println(findNextPart(args[0]));
			}
		} else {
			Scanner scanner = new Scanner(System.in);
			String lastPhrase = "(start)";
			String inputLine;
			System.out.println("Enter a phrase from the Scout Oath,\n"+
			  "or a blank line for next one, or EOF to quit.\n");
			while (null != (inputLine=scanner.nextLine())) {
				if (inputLine.trim().length()==0) {
					inputLine = lastPhrase;
				}
				String nextPhrase = findNextPart(inputLine);
				System.out.println(" -> "+nextPhrase);
				if (nextPhrase.charAt(0) != '(') {
					lastPhrase = nextPhrase;
				} else {
					System.out.println("(Enter '(start)' to return to beginning.)");
				}
			}
		}
	}

}
