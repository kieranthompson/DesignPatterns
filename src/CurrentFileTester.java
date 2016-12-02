

public class CurrentFileTester{
   public static void main(String args[])
   {
	   // Create an object of currentFileHandler
	  CurrentFileHandler currentFileHandler = new CurrentFileHandler();

	  // Deserialize the ArrayList from the File,
	  // i.e. read the currents ArrayList from the file back into the ArrayList
	  currentFileHandler.readRecordsFromFile();
   
	  // Create a Menu Object
	   Menu menuObj = Menu.getInstance();

	   menuObj.showMessage();

	   do{
		 // Call it's display() method
		 menuObj.display();
		 // Call it's readOption() method
		 menuObj.readOption();
		 // switch on the option and call the appropriate method
		 switch(menuObj.getOption()){
		    case 1:currentFileHandler.add();break;
			case 2:currentFileHandler.list();break;
			case 3:currentFileHandler.view();break;
			case 4:currentFileHandler.edit();break;
			case 5:currentFileHandler.delete();break;
			case 6:// Write the ArrayList to the File
			       currentFileHandler.writeRecordsToFile();break;
			default:System.out.println("INVALID OPTION...");
		 }
	  }while(menuObj.getOption() != 6);
   }
}