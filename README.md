# Fixed_Income_Calculator-in-Java
A fixed income calculator written in Java.

This program is a calculator of the Market Value (price) and yield to maturity (YTM) of bonds.
 To start the program, please run below command on your terminal or Windows PowerShell:
 	java -jar FixedIncomeCalculator.jar
 or
    java RongGuan

 To perform unit-test of this program, you may run:
 	java -jar FixedIncomeCalculator.jar -test
or
    java FixedIncomeCalculator -test 

*** ***  *** ***  *** ***  *** ***  *** ***  *** ***  *** ***  *** ***  *** ***  *** ***  *** 

Below list some output for the program:

$ java -jar FixedIncomeCalculator.jar

	This program can calculcate the Market Value and the Yield to 
	Maturity (YTM) for a bond.
	To calculcate the Market Value of a bond, you need to enter the Annual 
	Rate, Maturity in Years, Par Value and Yield to Maturity of the bond.
	To calculcate the Yield to Maturity of a bond, you need to enter the
	Annual Rate, Maturity in Years, Par Value and Market Value of the bond.

	If you want to calculcate the Market Value of a bond, please enter 1;
	If you want to calculcate the YTM of a bond, please enter 2;
	If you want to exit the program, please enter 0;
	For the program developer, to test the program's integrity, 
	please enter 9.

Enter your choice now: 1
We start to calculate the Market Value of your bond.
Enter Annual Rate (coupon): 0.1
Enter Maturity in Years: 5
Enter Par Value (face): 1000
Enter Yield to Maturity (rate): 0.15
The Market Value (price) of the bond is 832.3922451

Enter your next choice now: ( 0 for exit; 1 for price; 2 for yield; 9 for testing) 2
We start to calculate the Yield to Maturity (YTM) of your bond.
Enter Annual Rate (coupon): 0.1
Enter Maturity in Years: 5
Enter Par Value (face): 1000
Enter Market Value (price): 832.4
The YTM of the bond is 0.1499974

Enter your next choice now: ( 0 for exit; 1 for price; 2 for yield; 9 for testing) 0


$ java FixedIncomeCalculator

	This program can calculcate the Market Value and the Yield to 
	Maturity (YTM) for a bond.
	To calculcate the Market Value of a bond, you need to enter the Annual 
	Rate, Maturity in Years, Par Value and Yield to Maturity of the bond.
	To calculcate the Yield to Maturity of a bond, you need to enter the
	Annual Rate, Maturity in Years, Par Value and Market Value of the bond.

	If you want to calculcate the Market Value of a bond, please enter 1;
	If you want to calculcate the YTM of a bond, please enter 2;
	If you want to exit the program, please enter 0;
	For the program developer, to test the program's integrity, 
	please enter 9.

Enter your choice now: 1
We start to calculate the Market Value of your bond.
Enter Annual Rate (coupon): 0.1
Enter Maturity in Years: 30
Enter Par Value (face): 1000
Enter Yield to Maturity (rate): 0.19
The Market Value (price) of the bond is 528.8807463

Enter your next choice now: ( 0 for exit; 1 for price; 2 for yield; 9 for testing) 2
We start to calculate the Yield to Maturity (YTM) of your bond.
Enter Annual Rate (coupon): 0.1
Enter Maturity in Years: 30
Enter Par Value (face): 1000
Enter Market Value (price): 528.8807463
The YTM of the bond is 0.1900000

Enter your next choice now: ( 0 for exit; 1 for price; 2 for yield; 9 for testing) 9
Case 3 calculated yield is 0.0800010. The provided yield is 0.0800001. 
Enter your next choice now: ( 0 for exit; 1 for price; 2 for yield; 9 for testing) 0


Below is the result for timing the program:

$ time java FixedIncomeCalculator -test
Case 3 calculated yield is 0.0800010. The provided yield is 0.0800001. 

real	0m0.152s
user	0m0.118s
sys	0m0.032s

$ time java -jar FixedIncomeCalculator.jar -test
Case 3 calculated yield is 0.0800010. The provided yield is 0.0800001. 

real	0m0.158s
user	0m0.120s
sys	0m0.032s
