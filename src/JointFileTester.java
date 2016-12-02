

public class JointFileTester extends CurrentFileHandler{
   public static void main(String args[])	// main method
   {
      JointFileHandler jointFileHandler = JointFileHandler.getInstance();

	   jointFileHandler.showMessage();

	  // Deserialize the ArrayList from the File,
	  // i.e. read the joints ArrayList from the file back into the ArrayList
      jointFileHandler.readRecordsFromFile();

//	     Create a Menu Object
		Menu menuObj = Menu.getInstance();

	   menuObj.showMessage();
		
		do{
			// Call it's display() method
			menuObj.display();
			// Call it's readOption() method
			menuObj.readOption();
			// switch on the option and call the appropriate method
			switch(menuObj.getOption()){
			   case 1:jointFileHandler.add();
                   jointFileHandler.writeRecordsToFile();
                   break;

			   case 2:jointFileHandler.list();break;
			   case 3:jointFileHandler.view();break;
			   case 4:jointFileHandler.edit();
                   jointFileHandler.writeRecordsToFile();
                   break;
			   case 5:jointFileHandler.delete();
                   jointFileHandler.writeRecordsToFile();
                   break;
			   case 6:// Write the ArrayList to the File
				   jointFileHandler.writeRecordsToFile();break;
			   default:System.out.println("INVALID OPTION...");
			}
		}while(menuObj.getOption() != 6);
   }
}