

import java.io.Serializable;
import java.util.Scanner;

public class Date implements Serializable{
	private int day;
	private int month;
	private int year;

	// Default Constructor 
	//	==> Called when a Date object is created as follows - Date d1 = new Date();
	public Date(){
	   day=month=year=0;
	}

	// Initialization Constructor
	// ==> Called when a Date object is created as follows - Date d2 = new Date(14,9,2010);
	// Initialisation Constructor
  	public Date(int day, int month, int year)throws IllegalArgumentException{
	   if(day <=0 || day > 31 || month < 0 || month > 12 || year < 1900)
		   throw new IllegalArgumentException("DATE IS INVALID.");		

       this.day = day;
       this.month = month;
	   this.year = year;
   }

	// toString() method
	// ==> Called when a String of the class is used, e.g. - System.out.print(d1);
	//																  or System.out.print(d1.toString());
	@Override
   public String toString(){
		return (day + "/" + month + "/" + year);
	}
	
	// equals() method
	// ==> Called when comparing an object with another object, e.g. - if(d1.equals(d2))
	@Override
   public boolean equals(Object o){
		// First check if o is a null reference to prevent run-time exceptions
		if(o == null) 
         return false;
	
		// Next check if o references the current object, if it does it must be equal
		if (this == o)
			return true;
         
		// Next check if o is in fact a Date object
		if(!(o instanceof Date))
			return false;
		else
		{
			// If none of the above are true, cast o to a Date object so that
         // we can compare the object's instance variables and methods
			Date other = (Date)o;
			
			return (this.day == other.day) &&
					 (this.month == other.month) &&
					 (this.year == other.year);
		}
	}
/**NOTE:This is an incorrect equals --> It's NOT overriding equals from Object class!
**     :It is important to use @Override to check you are overriding the correct method
**     :The method belo is, in fact, a totally new method.
**     :You SHOULD be overriding the equals method from the Object class  
** public boolean equals(Date dateIn){
**		return (this.day == dateIn.day &&
              this.month == dateIn.month &&
              this.year == dateIn.year);
**	}
**/
	
	// get methods
	// ==> Called when retrieving part of an object, e.g. - if (d1.getYear() == d2.getYear())
	public int getDay(){
		return day;
	}
	public int getMonth(){
		return month;
	}
	public int getYear(){
		return year;
	}

	// set methods
	// ==> Called when setting part of an object, e.g. - d1.setDay(14);
	//													 d1.setMonth(9);
	//								    				 d1.setYear(2010);
	public void setDay(int setDayTo)throws IllegalArgumentException{
       if(setDayTo <=0 || setDayTo > 31)
		  throw new IllegalArgumentException("DAY IS NOT IN RANGE 1 TO 31.");		
       day=setDayTo;
	}
 	public void setMonth(int setMonthTo)throws IllegalArgumentException{
 	   if(setMonthTo <=0 || setMonthTo > 12)
 		   throw new IllegalArgumentException("MONTH IS NOT IN RANGE 1 TO 12.");		

 		// NO EXCEPTION THROWN
 		month = setMonthTo;
 	}
 	public void setYear(int setYearTo)throws IllegalArgumentException{
 	   if(setYearTo <=1900)
 		   throw new IllegalArgumentException("YEAR CANNOT BE NEGATIVE.");		

 		// NO EXCEPTION THROWN
 		year = setYearTo;
 	}

    public void read(){
  	    Scanner keyboard = new Scanner(System.in);		
 		
 		boolean goodInput = false;
 		int dayIn=0, monthIn=0, yearIn=0;

 		do{		
 			try{
 			 	// try read a valid day, month, and year. 	
 				System.out.println("DATE OPENED: ");	
 	 		  	System.out.print("DAY : ");dayIn=keyboard.nextInt();
 				System.out.print("MONTH : ");monthIn=keyboard.nextInt();
 	   		    System.out.print("YEAR : ");yearIn=keyboard.nextInt();				
				goodInput = true;
			}
 			catch(Exception e){
 			    System.out.println("INVALID day or month or year. RE_ENTER.");
 				// clear the buffer - Java bug!
 				char c = keyboard.next().charAt(0);
 			}
 		}while(!goodInput);

 		goodInput = false;
 		do{		
 			try{
 				// try set the day, month, and year.  
 				// The set methods ensure day, month and year are in the correct range
 				setDay(dayIn);
 				setMonth(monthIn);
 				setYear(yearIn);								
 				goodInput = true;
 			}
 			catch(IllegalArgumentException e){
 			   System.out.println("INVALID day or month or year. RE_ENTER.");
 				System.out.print("DAY : ");dayIn=keyboard.nextInt();
 				System.out.print("MONTH : ");monthIn=keyboard.nextInt();
 				System.out.print("YEAR : ");yearIn=keyboard.nextInt();								
 			}
 		}while(!goodInput);
 	}	
 }