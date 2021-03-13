/*
	Student Name: Yuriko Uchida
	Student Number: #200448500
	
	Course Name: Intro Obj Oriented Prog-Java
	Due Date: Feb 11th
 */


import java.time.LocalDate; //Include LocalDate library
import java.util.Scanner; //Include scanner library
public class AccountTester {

	public static void main(String[] args) {

		//Create instances
		Customer cust1 = new Customer("Yuriko", "Uchida", "3196 ave", "604-1234-1234", LocalDate.of(1989, 4,3));
		CheckingAccount custChecking1 = new CheckingAccount(cust1, 5000.00);
		SavingAccount custSaving1 = new SavingAccount(cust1, 5000.00);

		Scanner scan = new Scanner(System.in);


		//Operation to the checking account
		System.out.print("Select the operation number to your checking account. \n1: Withdrawal \n2: Deposit \n3: Both \n");
		switch (scan.nextInt())
		{
		case 1:
			System.out.print("Enter the withdraw amount: $ ");
			custChecking1.withdraw(scan.nextDouble());
			break;
		case 2:
			System.out.print("Enter the deposit amount: $ ");
			custChecking1.deposit(scan.nextDouble());
			break;
		case 3:
			System.out.print("Enter the deposit amount: $ ");
			custChecking1.deposit(scan.nextDouble());
			System.out.print("Enter the withdraw amount: $ ");
			custChecking1.withdraw(scan.nextDouble());
			break;
		default:
			System.out.print("Please select the operation number from 1 to 3.");
			break;
		}

			custChecking1.chargeInterest();
			custChecking1.chargeMonthlyFee();			

		
		//Operation to the saving account
		System.out.print("Select the operation number to your saving account. \n1: Withdrawal \n2: Deposit \n3: Both \n");
		switch (scan.nextInt())
		{
		case 1:
			System.out.print("Enter the withdraw amount: $ ");
			custSaving1.withdraw(scan.nextDouble());
			break;
		case 2:
			System.out.print("Enter the deposit amount: $ ");
			custSaving1.deposit(scan.nextDouble());
			break;
		case 3:
			System.out.print("Enter the deposit amount: $ ");
			custSaving1.deposit(scan.nextDouble());
			System.out.print("Enter the withdraw amount: $ ");
			custSaving1.withdraw(scan.nextDouble());
			break;
		default:
			System.out.print("Please select the operation number from 1 to 3.");
			break;
		}
		
		custSaving1.addTotalInterest();
		
		
	}//End of the main class
}//End of the class
