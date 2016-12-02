import java.util.Scanner;

public class Menu{
   private Scanner keyboard = new Scanner(System.in);
   private int option;

	private static Menu menuObj = new Menu();
	private Menu(){

	}
	
	public void display(){
	   System.out.println("\n\t1. Add");
	   System.out.println("\t2. List");
	   System.out.println("\t3. View");
	   System.out.println("\t4. Edit");
	   System.out.println("\t5. Delete");
	   System.out.println("\t6. Quit");		
	}						
	
	public void readOption(){
  	   System.out.print("\n\tEnter Option [1|2|3|4|5|6] : ");
		option=keyboard.nextInt();
	}
	
	public int getOption(){
	   return option;
	}

	public static Menu getInstance(){
		return menuObj;
	}

	public void showMessage(){
		System.out.println("single instance created");
	}

}
