

import java.io.Serializable;
import java.util.Scanner;

public class Current extends Account implements Serializable{
	private double overdraftAmount;
                
 	// Default Constructor for Current objects
 	// - Java will automatically call Account() constructor	
	public Current(){
   		super(); // Can call super() but it will do it automatically
		overdraftAmount = 0.0;
	}
	
  	// Initialisation Constructor for Current objects
	// Provides a name, address, balance, date of opening & overdraft amount for Current Account
	// Called as follows:
	// Current c1 = new Current("Mr","Joe","Bloggs",
	//                          "123 High Road",1000.00,
	//							12,6,2012, 100.00);
	public Current(String t, String fN, String sn, 
			       String addressIn, double balanceIn, 
			       int dayIn, int monthIn, int yearIn, 
			       double overdraftAmountIn){
		super(t, fN, sn, addressIn, balanceIn, dayIn, monthIn, yearIn);		
     	overdraftAmount = overdraftAmountIn;
   }	

	// Display Current object as 
	//         "ACCOUNT==>100001:Mr Joe Bloggs,123 High Road,Letterkenny.	�1000.00,Overdraft Available is 100" 
	@Override
 	public String toString(){
		return(super.toString() + ",Overdraft Available is " + overdraftAmount);
  	}
	
	// Don't need to override equals()
	// Account equals is sufficient

	// get() and set() methods for each Instance Variable
  	public double getOverdraftAmount(){
		return overdraftAmount;
	}
  	public void setOverdraftAmount(double setOverdraftAmountTo){
		overdraftAmount = setOverdraftAmountTo;
	}
  	
	// overdrawn() method to determine if balance is less than 0
	public boolean overdrawn(){
	   return(balance < 0.0);
  	}	

	public void read(){
		Scanner kbInt = new Scanner(System.in);
		boolean goodInput = false;
		
	   System.out.println("CURRENT ACCOUNT DETAILS ==>");

		// Read name, address, balance, date opened, overdraftAmount
		super.read();

		do{		
			try{
			 	// try read a valid overdraftAmount.
			   System.out.print("OVERDRAFT AMOUNT : ");overdraftAmount=kbInt.nextDouble();
				goodInput = true;
			}
			catch(Exception e){
			    System.out.println("INVALID overdraft amount. RE_ENTER.");
				// clear the buffer - Java bug!
				char c = kbInt.next().charAt(0);
			}
		}while(!goodInput);
	}
}