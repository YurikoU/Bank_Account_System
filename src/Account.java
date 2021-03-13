import java.time.LocalDate; //Include LocalDate library
public class Account {

	//Declare fields with access modifiers "private" to secure information
	private String firstName, lastName, address, phNum;
	private double balance;
	private LocalDate dateOfBirth;
	private Customer customer;
	
	//Declare a constructor with parameters
	public Account(Customer cust, double balance) {
		customer = cust;
		this.balance = balance;
	}//End of the constructor
	
	
	//Declare another constructor with three parameters
	public Account(String firstName, String lastName, double balance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}//End of the constructor

	
	
	//Create getter methods to get each values from private variables
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public double getBalance() {return balance;}
	
	//Create methods to express deposit
	public void deposit(double deposit) {
		if (deposit <= 0) { //If the deposit is not a positive amount
			throw new IllegalArgumentException("Cannot deposit a negative number"); //The error will be printed
		}else {
			this.balance = balance + deposit;
		}
	}

	//Create methods to express withdrawal
	public void withdraw(double withdraw) {
		if (this.balance < withdraw) { //If a user try to withdraw more than the balance
			throw new IllegalArgumentException("Not Sufficient Funds to withdraw"); //The error will be printed
		} else if (withdraw <= 0) { //If the withdrawal is a not a positive amount
			throw new IllegalArgumentException("Cannot withdraw a negative amount"); //The error will be printed
		} else {
			this.balance = balance - withdraw;
		}
	}
	
	//Create setter methods including the input validations to set proper variables to each private field
	public void setFirstName(String firstName) {
		if(firstName.matches("[a-z A-Z_]+")) //If the input is composed by only alphabets
		{
			this.firstName = firstName; //Set the variable to the private field
		} else {
			throw new IllegalArgumentException("Invalid character in the first name"); //Otherwise, the error will be printed
		}
	}
	
	public void setLastName(String lastName) {
		if(lastName.matches("[a-z A-Z_]+")) //If the input is composed by only alphabets
		{
			this.lastName = lastName; //Set the variable to the private field
		} else {
			throw new IllegalArgumentException("Invalid character in the last name"); //Otherwise, the error will be printed
		}
	}

	public void setBalance(double balance) {
		this.balance = balance; //Set the variable to the private field
	}
		

	
	//Create a toString method so that the text to display will be printed as String data type
	@Override
	public String toString() {
		return "First Name:" + getFirstName() + " Last Name:" + getLastName() + " Balance:" + getBalance();
	}


}//End of the class
