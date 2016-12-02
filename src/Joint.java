/**
* Class: B.Sc. in Computing
* Description: A Joint class - inherited from the abstract class Account
* Date: 29/9/2016
* @author Maria Boyle
* @version 1.0
*/


import java.io.Serializable;
import java.util.Scanner;

public class Joint extends Account implements Serializable{
	private Name nameB;			// COMPOSITION - nameB is an object of class Name
                
  	// Default Constructor for Joint objects
 	// - Java will automatically call Account() constructor
	public Joint(){
		// super(); // Can call super() but it will do it automatically
		nameB = new Name();
	}
	
  	// Initialisation Constructor for Joint objects
	// Provides a nameA, nameB, address, balance, date of opening & overdraft amount for Joint Account
	// Called as follows:
	// Joint j1 = new Joint("Mr","Joe","Bloggs","Mrs","Ann","Bloggs",
	//                      "123 High Road",1000.00,12,6,2012);
	public Joint(String t1, String fN1, String sn1,  
				 String t2, String fN2, String sn2,     
				 String address, double balance, 
	             int day, int month, int year){
		super(t1, fN1, sn1, address, balance, day, month, year);		
     	nameB = new Name(t2,fN2,sn2);
   }	
	
	// toString() method for displaying a Joint object
	// Display Joint object as 
	//         "ACCOUNT==>100001:Mr Joe Bloggs & Mrs Ann Bloggs,123 High Road,Letterkenny.	�100.00" 
	@Override
	public String toString(){
 		return("ACCOUNT==>"+ accountNo + ":" + name + " & " + nameB + "," + address + ".\t�" + balance);		
  	}
	
	// Don't need to override equals()
	// Account equals is sufficient
	
	// get() and set() methods for each Instance Variable
  	public Name getNameB(){
		return nameB;
	}
  	public void setNameB(Name nameBIn){
		nameB = nameBIn;
	}

	public void read(){
		System.out.println("JOINT ACCOUNT DETAILS ==>");

		// Read name, address, balance, date opened, nameB
		nameB.read();
		super.read();
	}
}