/*
 * Author: Rong Guan
 * Email: rongguan2018@gmail.com
 * Date: Nov. 28, 2019
 * Other: check readme.md
 */
import java.util.*;
import java.text.DecimalFormat;
//import java.math.BigDecimal;
//import java.math.RoundingMode;

public class FixedIncomeCalculator {

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("-test")) {
			test();
			return;
		}

		prompt();

		int years;
		double coupon, face, rate, price;
		System.out.print("Enter your choice now: ");
		Scanner console = new Scanner (System.in);
		int choice = console.nextInt();

		while (choice != 0) {
			if (choice == 1) {
				System.out.println("We start to calculate the Market Value of your bond.");

				System.out.print("Enter Annual Rate (coupon): ");
				coupon = console.nextDouble();

				System.out.print("Enter Maturity in Years: ");
				years = console.nextInt();

				System.out.print("Enter Par Value (face): ");
				face = console.nextDouble();

				System.out.print("Enter Yield to Maturity (rate): ");
				rate = console.nextDouble();

				System.out.println("The Market Value (price) of the bond is " + output(
					CalcPrice(coupon, years, face, rate)) + "\n");
			} else if (choice == 2) {
				System.out.println("We start to calculate the Yield to Maturity (YTM) of your bond.");
				// double coupon, int years, double face, double price
				System.out.print("Enter Annual Rate (coupon): ");
				coupon = console.nextDouble();

				System.out.print("Enter Maturity in Years: ");
				years = console.nextInt();

				System.out.print("Enter Par Value (face): ");
				face = console.nextDouble();

				System.out.print("Enter Market Value (price): ");
				price = console.nextDouble();

				System.out.println("The YTM of the bond is " + output(
					CalcYield(coupon, years, face, price)) + "\n");

			} else if (choice == 9) {
				test();
			} else {
				System.out.println("The number is not acceptable. Please enter number 0, 1,"
					               + " 2, or 9.");
			}
			System.out.print("Enter your next choice now: ( 0 for exit; 1 for price; 2 for "
				             + "yield; 9 for testing) ");
			choice = console.nextInt();
		}

		return;
	}

	private static void prompt() {
		System.out.println("\n\tThis program can calculcate the Market Value and the Yield to \n"
			             + "\tMaturity (YTM) for a bond.\n"
			             + "\tTo calculcate the Market Value of a bond, you need to enter the Annual \n"
			             + "\tRate, Maturity in Years, Par Value and Yield to Maturity of the bond.\n"
			             + "\tTo calculcate the Yield to Maturity of a bond, you need to enter the\n"
			             + "\tAnnual Rate, Maturity in Years, Par Value and Market Value of the bond.\n\n" 
			             + "\tIf you want to calculcate the Market Value of a bond, please enter 1;\n"
			             + "\tIf you want to calculcate the YTM of a bond, please enter 2;\n"
			             + "\tIf you want to exit the program, please enter 0;\n"
			             + "\tFor the program developer, to test the program's integrity, \n"
			             + "\tplease enter 9.\n");
	}

	/*
	 *This method calculates the Market Value of a bond
	 */
	private static double CalcPrice(double coupon, int years, double face, double rate) {
		double price = face / Math.pow((1 + rate), years);

		for (int i = 1; i <= years; i++) {
			price += (coupon * face) / Math.pow((1 + rate), i);
		}
		//return round(price, 7);
		return price;
	} 
	
	/*
	 * This method calculates the Yield to Maturity of a bond
	 */
	private static double CalcYield(double coupon, int years, double face, double price) {
		// Firstly, we want to get the approximate YTM
		double yield = approximateYTM(coupon, years, face, price);
		double calcPrice = CalcPrice(coupon, years,face, yield);
		double diff = calcPrice - price;
		double lowYield;
		double highYield;
		
		if (diff == 0) {
			return yield;
		} else if (diff > 0) { // diff > 0 means calcPrice is too high, 
			                   // estimated YTM may be somewhat low
			lowYield = yield; 
			// below while loop is used to get the high bound of YTM
			while (diff > 0) { 
				yield += 0.1;
				calcPrice = CalcPrice(coupon, years,face, yield);
				diff = calcPrice - price;
			}
			highYield = yield;
		} else {
			highYield = yield;
			//below while loop is used to get the high bound of YTM
			while (diff < 0) { 
				yield -= 0.1;
				calcPrice = CalcPrice(coupon, years,face, yield);
				diff = calcPrice - price;
			}
			lowYield = yield;
		} 
		return binarySearch(lowYield, highYield, coupon, years, face, price);
		//return round(binarySearch(lowYield, highYield, coupon, years, face, price), 7);
	}

	/*
	 * This method calculates the approximate YTM
	 */
	private static double approximateYTM(double coupon, int years, double face, double price) {
		double yield = ((coupon * face) + (face - price) / years) / ((face + price) /2);
		return yield;
	}

	/*
	 * This method obtains the low bound of YTM and the up bound of YTM and uses the binary search 
	 * to the precise value
	 */
	private static double binarySearch(double low, double high, double coupon, int years, 
		double face, double price) {
		while (low < high) {
			double mid = low + (high - low) / 2;
			double calcPrice = CalcPrice(coupon, years,face, mid); 
			if (Math.abs(calcPrice - price) < 0.0000001) {
				return mid;
			} else if (calcPrice > price) {
				low = mid;
			} else {
				high = mid;
			}
		}
		return low;
	}

	/*
	 * This method formats the output to an accuracy of 10^(-7)
	 */
	private static String output(double number) {
		DecimalFormat df = new DecimalFormat("######0.0000000");
		return df.format(number);
	}

	/*
	 * This method provides unit-tests which include all the test cases required
	 */
	private static void test() {
		boolean passed = true;
		if (!output( CalcPrice(0.10, 5, 1000, 0.15) ).equals("832.3922451")) {
			passed = false;
			System.out.println("Case 1 calculated price is " + CalcPrice(0.10, 5, 1000, 0.15)
			 + ". The correct price should be 832.3922451. ");
		}
		if (!output( CalcYield(0.10, 5, 1000, 832.4) ).equals("0.1499974")) {
			passed = false;
			System.out.println("Case 1 calculated yield is " + CalcYield(0.10, 5, 1000, 832.4)
			 + ". The correct yield should be 0.1499974. ");
		}
		
		if (!output( CalcPrice(0.15, 5,1000, 0.15) ).equals("1000.0000000")) {  
			passed = false;
			System.out.println("Case 2 calculated price is " + output(CalcPrice(0.15, 5,1000, 0.15))
			 + ". The correct price should be 1000.0000000. ");
		}
		if (!output( CalcYield(0.10, 5,1000, 1000) ).equals("0.1000000")) { 
			passed = false;
			System.out.println("Case 2 calculated yield is " + output(CalcYield(0.10, 5,1000, 1000))
			 + ". The correct yield should be 0.1000000. ");
		}

		if (!output(CalcPrice(0.10, 5, 1000, 0.08)).equals("1079.8542007")) {  
			passed = false;
			System.out.println("Case 3 calculated price is " + output(CalcPrice(0.10, 5, 1000, 0.08))
			 + ". The correct price should be 1079.8542007. ");
		}
		if (!output(CalcYield(0.10, 5, 1000, 1079.85)).equals("0.0800010")) {  // need to be fixed
			passed = false;
			System.out.println("Case 3 calculated yield is " + output(CalcYield(0.10, 5, 1000, 1079.85))
			 + ". The provided yield is 0.0800001. ");
		}

		if (!output(CalcPrice(0.10, 30, 1000, 0.19)).equals("528.8807463")) {  // need to be fixed
			passed = false;
			System.out.println("Case 4 calculated price is " + output(CalcPrice(0.10, 30, 1000, 0.19))
			 + ". The correct price should be 528.8807463. ");
		}
		if (!output(CalcYield(0.10, 30, 1000, 528.8807463)).equals("0.1900000")) {  // need to be fixed
			passed = false;
			System.out.println("Case 4 calculated yield is " + output(CalcYield(0.10, 30, 1000, 528.8807463))
			 + ". The correct yield should be 0.1900000. ");
		}
		if (passed) {
			System.out.println("All tests passed!");
		}
	}

	// public static double round(double value, int significantDigits) {
	//     if (significantDigits < 0) {
	//     	throw new IllegalArgumentException();
	//     }
	//     BigDecimal bd = BigDecimal.valueOf(value);
	//     bd = bd.setScale(significantDigits, RoundingMode.HALF_UP);
	//     return bd.doubleValue();
	// }
}
