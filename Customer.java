import java.time.LocalDate;

public class Customer {

	//Declare variables with String type or LocalDate type
	private String firstName, lastName, address, phNum;
	private LocalDate dateOfBirth;
	
	
	public Customer(String firstName, String lastName, String address, String phNum, LocalDate dateOfBirth)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phNum = phNum;
		this.dateOfBirth = dateOfBirth;		
	}
}