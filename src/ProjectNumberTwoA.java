/**
 * After doing the project the traditional way, I came up with a novel 
 * solution to the problem. Instead of making one conversion (miles to inches, for example),
 * I would use a loop to break it down into multiple conversions (miles to yards, then yards to feet,
 * and finally feet to inches, for example). However, the only way I could see to do this in a
 * concise, efficient way would require an array of conversion rates.
 * For fun, I did a little online research, and this is the result.
 * Although it is a little more difficult to document the logic,
 * I like the fact that it is very easy to scale up to more units.
 * 
 * 
 * @author j.apon
 *
 */
import java.util.Scanner;

public class ProjectNumberTwoA {

	public static String [][] units = 
		{{"","Inch","Foot","Yard","Mile","League"},
		{"","Inches","Feet","Yards","Miles","Leagues"}};
	
	public static void main(String[] args){
		
		Scanner scan = new Scanner(System.in);
		
		int unitConvertFrom;
		int unitConvertTo;
		
		double amountToConvert;
		double result;
		
		String doAgain;
		
		do{
			
			// Use menu to determine what units user wants to convert
			unitConvertFrom = createUnitMenu(scan,"From");
			
			// Get amount to convert from user
			System.out.println("How many " + units[1][unitConvertFrom] + " would you like to convert?");
			amountToConvert = scan.nextDouble();
			
			// Use menu to determine what units user wants to convert to
			unitConvertTo = createUnitMenu(scan,"To");
			
			//Calculate the conversion
			result = useConversionLadder(unitConvertFrom,unitConvertTo,amountToConvert);
			
			//Check for single unit labels
			int a = 1;
			int b = 1;
			if (amountToConvert == 1){a = 0;}
			if (result == 1){b = 0;}
			
			System.out.println(amountToConvert + " " + units[a][unitConvertFrom] + " is equal to " + result + " " + units[b][unitConvertTo]);
			
			//If user enters 'y' or 'Y', do-while loop will run again
			System.out.println("\nWould you like to do another conversion?");
			System.out.println("Please press 'Y' or 'N' followed by 'ENTER'");
			doAgain = scan.next();
		
		}while(doAgain.equals("Y")||doAgain.equals("y"));
		
		scan.close();
	}
	
	public static int createUnitMenu(Scanner menu, String toFrom){
		
		int menuInput = 0;
		
		System.out.println(toFrom+" which unit would you like to convert?");
		
		for (int x = 1; x < 6; x++){
			System.out.println(x + ". " + units[1][x]);
			}
		
		System.out.println("\nPlease enter 1-5 followed by 'ENTER'");
		
		menuInput = menu.nextInt();
		
		//validation of menu selection
		while (menuInput < 1 || menuInput > 5) {
			System.out.println("\nInvalid Choice");
			System.out.println("Please press 1-5 followed by 'ENTER'");
			menuInput = menu.nextInt();
			}
		
		return menuInput;
	}
	
	/**
	 * This method moves back and forth in an array called a "Conversion Ladder" using a for loop.
	 * Each iteration of the loop converts to the next larger (or smaller) unit by dividing (or multiplying) 
	 * by the appropriate conversion factor.
	 * @param unitFromNumber The menu number of the unit the user wants to convert from
	 * @param unitToNumber The menu number of the unit the user wants to convert to
	 * @param amount The number of units the user wants to convert
	 * @return The converted result in the desired units
	 */
	public static double useConversionLadder(int unitFromNumber, int unitToNumber, double amount){
	
		final int INCHES_IN_ONE_FOOT = 12;
		final int FEET_IN_ONE_YARD = 3;
		final int YARDS_IN_ONE_MILE = 1760;
		final int MILES_IN_ONE_LEAGUE = 3;
		
		double convertedResult = amount;
		
		int[] conversionLadder = {1,INCHES_IN_ONE_FOOT,
									FEET_IN_ONE_YARD,
									YARDS_IN_ONE_MILE,
									MILES_IN_ONE_LEAGUE};
				
		if (unitToNumber>unitFromNumber){
			
			//going from smaller units to larger
			for (int x = unitFromNumber; x < unitToNumber ; x++){
				convertedResult = convertedResult / conversionLadder[x];
				}
			
			}else{ 
				
				//going from larger units to smaller
				for (int x = unitFromNumber; x > unitToNumber; x--){
					convertedResult = convertedResult * conversionLadder[x-1];
					}
				}
		
		return convertedResult;
	}
}

