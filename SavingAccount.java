//Include libraries
import java.time.LocalDate;
import java.time.Period;

public class SavingAccount extends Account{
	
	//Declare instance variables
	private int numOfTransaction;
	private double transactionFee, interest;
	private String accountType;
	private LocalDate interestStart;
	
	
	//Create a constructor and initialize arguments
	public SavingAccount(Customer cust, double balance)
	{
		super(cust, balance);
		setNumOfTransaction(3);
		setTransactionFee(5.00);
		setInterest(0.02);
		this.accountType = "Saving";
		this.interestStart = LocalDate.now();
	}
	

	//Create getter methods to get each values from private variables
	public double getNumOfTransaction() {return numOfTransaction;}
	public double getTransactionFee() {return transactionFee;}
	public double getInterest() {return interest;}
	public String getAccountType() {return accountType;}
	public LocalDate getInterestStart() {return interestStart;}

	
	//Create setter methods to set each values from private variables
	public void setNumOfTransaction(int numOfTransaction) {this.numOfTransaction = numOfTransaction;}
	public void setTransactionFee(double transactionFee) {this.transactionFee = transactionFee;}
	public void setInterest(double interest) {this.interest = interest;	}
	public void setAccountType(String accountType) {this.accountType = accountType;}
	public void setInterestStart(LocalDate interestStart) {this.interestStart = interestStart;}

	
	@Override    //Override deposit() method from Account class
	public void deposit(double deposit)
	{
		if (deposit <= 0)
		{
			throw new IllegalArgumentException("Please enter a positive amount.");			
		} else {
			super.setBalance((deposit + super.getBalance()));
			System.out.println("Deposit amount: $ " + deposit
								+ "\nYour new balance: $ " + super.getBalance());
		}
	}//End of deposit() method

	
	@Override      //Override withdraw() method from Account class
	public void withdraw(double withdraw)
	{

		if (0 <= getNumOfTransaction()) //If free withdrawal is available
		{
			if (super.getBalance() < withdraw) // 0 < balance < withdraw
			{
				throw new IllegalArgumentException("Not Sufficient Funds to withdraw");
			} else if (withdraw <= 0) // withdraw <= 0
			{
				throw new IllegalArgumentException("Please enter a positive amount.");
			} else { // 0 < withdraw <= balance
				super.setBalance(super.getBalance() - withdraw);
				System.out.println("Withdrawal amount: $ " + withdraw
									+ "\nYour new balance: $ " + super.getBalance());			
				this.numOfTransaction = numOfTransaction - 1;
			}

			
		} else { //If free withdrawal is NOT available, and the transaction fee will be charged
			if (super.getBalance() < withdraw) // 0 < balance < withdraw
			{
				throw new IllegalArgumentException("Not Sufficient Funds to withdraw");
			} else if (withdraw <= 0)  // withdraw <= 0
			{
				throw new IllegalArgumentException("Please enter a positive amount.");
			} else { // 0 < withdraw <= balance
				super.setBalance(super.getBalance() - (getTransactionFee() + withdraw));
				System.out.println("Withdrawal amount: $ " + withdraw
									+ "\nTransaction fee: $ " + getTransactionFee()
									+ "\nYour new balance: $ " + super.getBalance());			
			}
		}
			
	}//End of withdraw() method


	
	public void addTotalInterest() {
		LocalDate whatMonthNow = LocalDate.now();
		Period interestPeriod = Period.between(getInterestStart(), whatMonthNow);
		int numInterestPeriod = interestPeriod.getMonths();
		double interestThisMonth = super.getBalance() * getInterest();

		if (1 <= numInterestPeriod)
		{
			for (int i = 1; i < numInterestPeriod; i++)  //Charge the total shortage amount added to the interest
			{
				interestThisMonth = interestThisMonth * interest;
			}
			System.out.println("Your new amount : $ " + interestThisMonth
								+ "\nYour new balance: $ " + super.getBalance());
			
		} else {
			System.out.print("\nThere is no new amount."
							+ "\nYour current balance: " + super.getBalance());
		}

	}//End of addTotalInterest() method
	
	
}//End of class