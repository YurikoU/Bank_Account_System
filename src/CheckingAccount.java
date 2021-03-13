//Include libraries
import java.time.LocalDate;
import java.time.Period;


public class CheckingAccount extends Account{
	
	//Declare instance variables
	private double monthlyFee, overdraftFee, overdraftLimit, interest;
	private boolean overdraftActivity;
	private String accountType;
	private LocalDate interestStart;
	private LocalDate monthlyFeeStart;
	private double shortage;
	
	//Create a constructor and initialize arguments
	public CheckingAccount(Customer cust, double balance)
	{
		super(cust, balance);
		setMonthlyFee(10.00);
		setOverdraftFee(5.00);
		setOverdraftLimit(1000.00);
		setOverdraftActivity(true);
		this.interest = 0.15;
		this.accountType = "Checking";
		this.interestStart = null;
		this.monthlyFeeStart = LocalDate.now();
		this.shortage = 0.00;
	}

	//Create getter methods to get each values from private variables
	public double getMonthlyFee() {return monthlyFee;}
	public double getOverdraftFee() {return overdraftFee;}
	public double getOverdraftLimit() {return overdraftLimit;}
	public double getInterest() {return interest;}
	public boolean getOverdraftActivity() {return overdraftActivity;}
	public String getAccountType() {return accountType;}
	public LocalDate getInterestStart() {return interestStart;}
	public LocalDate getMonthlyFeeStart() {return monthlyFeeStart;}
	public double getShortage() {return shortage;}
	
	//Create setter methods to set each values from private variables
	public void setMonthlyFee(double monthlyFee) {this.monthlyFee = monthlyFee;}
	public void setOverdraftFee(double overdraftFee) {this.overdraftFee = overdraftFee;}
	public void setOverdraftLimit(double overdraftLimit) {this.overdraftLimit = overdraftLimit;}
	public void setInterest(double interest) {this.interest = interest;}
	public void setOverdraftActivity(boolean overdraftActivity) {this.overdraftActivity = overdraftActivity;}
	public void setAccountType(String accountType) {this.accountType = accountType;}
	public void setInterestStart(LocalDate interestStart) {this.interestStart = interestStart;}
	public void setMonthlyFeeStart(LocalDate monthlyFeeStart) {this.monthlyFeeStart = monthlyFeeStart;}
	public void setShortage(double shortage) {this.shortage = shortage;}
	

	@Override    //Override deposit() method from Account class
	public void deposit(double deposit){
		if (deposit <= 0)
		{
			throw new IllegalArgumentException("Please enter a positive amount.");			
		} else {
			super.setBalance((deposit + super.getBalance()));
			System.out.println("Deposit amount: $ " + deposit
								+ "\nYour new balance: $ " + super.getBalance());
		}
	}

	
	@Override     //Override withdraw() method from Account class
	public void withdraw(double withdraw)
	{
		
		shortage = withdraw - super.getBalance();
		if (getOverdraftActivity() == true) //If the overdraft is available
		{

			if (0 < withdraw) {
				if (super.getBalance() < withdraw) // 0 < balance < withdraw
				{
					if (getOverdraftLimit() < shortage) // 0 < (balance + overdraft limit) < withdraw
					{
						System.out.println("The overdraft limit is $ " + getOverdraftLimit()
											+ "\nYou cannot withdraw more than $ " + (getOverdraftLimit() + super.getBalance()));
						
					} else if (getOverdraftLimit() <= 0) {// overdraft limit = 0
						setOverdraftActivity(false);
						throw new IllegalArgumentException("The overdraft limit for this month is already exceeded. "
															+ "You cannot withdraw until next month.");
					} else { // 0 < balance < withdrawal <= (balance + overdraft limit)
						super.setBalance(-(getOverdraftFee()  + shortage));
						this.overdraftLimit = getOverdraftLimit() - shortage;
						this.shortage = shortage;
						System.out.println("\nThe shortage amount will be coverd by the overdraft. "
								+ "\nOverdraft amount: $ " + shortage
								+ "\nOverdraft fee: $ " + getOverdraftFee() 
								+ "\nInterest rate: " + (this.interest * 100) + " %"
								+ "\nYour new Balance: $ " + -(getOverdraftFee()  + shortage));
						this.interestStart = LocalDate.now();
					}
				} else { // 0 < withdrawal <= balance
					super.withdraw(withdraw);
					System.out.println("\nWithdrawal amount: $ " + withdraw
							+ "\nYour new balance: $ " + super.getBalance());
				}
			
			} else { // withdrawal <= 0
				throw new IllegalArgumentException("Please enter a positive amount");
			}
			
			
		} else { //If the overdraft is NOT available
			if (0 < withdraw) {
				if (super.getBalance() < withdraw) // 0 < balance < withdraw
				{
					throw new IllegalArgumentException("The overdraft limit for this month is already exceeded. "
														+ "You cannot withdraw until next month.");
				} else { // 0 < withdrawal <= balance
					super.withdraw(withdraw);
					System.out.println("Withdrawal amount: $ " + withdraw
							+ "\nYour new balance: $ " + super.getBalance());
				}
			
			} else { // withdrawal <= 0
				throw new IllegalArgumentException("Please enter a positive amount");
			}
		}
		
	}//End of withdraw() method
	
	
	public void chargeInterest() {
		LocalDate whatMonthNow = LocalDate.now();
		
		if (0 < getShortage())
		{
			Period interestPeriod = Period.between(getInterestStart(), whatMonthNow);
			int numInterestPeriod = interestPeriod.getMonths();
			double interestThisMonth = getShortage() * getInterest();

			if (1 <= numInterestPeriod)
			{
				for (int i = 1; i < numInterestPeriod; i++)  //Charge the total shortage amount added to the interest
				{
					interestThisMonth = interestThisMonth * interest;
				}
				System.out.print("The interest charge for this month: " + interestThisMonth
						+ "Your current balance: " + super.getBalance());
				
			} else {
				System.out.print("\nThe intereste charge for this month: $ " + interestThisMonth
									+ "\nYour current balance: " + super.getBalance());
			}
		}
	}//End of chargeInterest() method
	
	
	public void chargeMonthlyFee() {  //Create methods to charge monthly fee
		LocalDate whatMonthNow = LocalDate.now();
		Period monthlyFeePeriod = Period.between(getMonthlyFeeStart(), whatMonthNow);
		int numMonthlyFeePeriod = monthlyFeePeriod.getMonths();

		if (1 == numMonthlyFeePeriod)
		{
			super.setBalance(super.getBalance() - monthlyFee);
			System.out.print("\nMonthly fee: " + getMonthlyFee()
								+ "\nYour new balance: " + super.getBalance());
		} else {
			System.out.print("\n\nThere is no monthly fee charge for you as of " + LocalDate.now());
		}
		
		numMonthlyFeePeriod = 0;
	}//End of chargeMonthlyFee() method
	
}//End of class
