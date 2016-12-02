import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.*;

////////////////////////////////////////////////////////////////////////
//                    class CurrentFileHandler                    		 //
//   This class contains an ArrayList, and the methods for modifying  //
//  the ArrayList as required, as well as the methods for serializing //
//               and deserializing the ArrayList                      //
////////////////////////////////////////////////////////////////////////
public class CurrentFileHandler{
	// Constant FILENAME for the file to be created
	private final String FILENAME = "currents.bin";

	// An ArrayList called currents - for storing a list of currents
	private ArrayList<Current> currents;

	// Default Constructor
	public CurrentFileHandler(){
		currents = new ArrayList<Current>();
	}
	
	//////////////////////////////////////////////////////
	// Method Name : add()				    		    //
	// Return Type : void								//
	// Parameters : None								//
	// Purpose : Reads one Current record from the user //
	//           and adds it to the ArrayList currents  //
	//////////////////////////////////////////////////////	
	public void add(){
	   // Create a Current object
		Current tmpCurrent = new Current();
		
		// Read its details
		tmpCurrent.read();
		
		// And add it to the currents
		currents.add(tmpCurrent);
	}

	//////////////////////////////////////////////////////////
	// Method Name : list()								    //
	// Return Type : void								    //
	// Parameters : None								    //
	// Purpose : Lists all Current records in the ArrayList //
	//////////////////////////////////////////////////////////	
	public void list(){
		// for every Current object in currents
      for(Current tmpCurrent:currents)
			// display it
			System.out.println(tmpCurrent);
	}
	
	//////////////////////////////////////////////////////////////
	// Method Name : view()										//
	// Return Type : void										//
	// Parameters : None									 	//
	// Purpose : Displays the required Current record on screen //
	//////////////////////////////////////////////////////////////	
	public Current view(){
		Scanner keyboard = new Scanner(System.in);
		boolean goodInput = false;
		int currentToView = 0;

		do{		
			try{
				// read the number of the video to be viewed from the user
				System.out.print("ENTER NUMBER OF CURRENT ACCOUNT TO VIEW : ");
				currentToView=keyboard.nextInt();
				goodInput = true;
			}
			catch(InputMismatchException e){
			   System.out.println("INVALID number. RE_ENTER.");
				// clear the buffer - Java bug!
				char c = keyboard.next().charAt(0);
			}
		}while(!goodInput);
				
		// for every Current object in currents
        for(Current tmpCurrent:currents){
			// if it's number equals the number of the Current to View
			if(tmpCurrent.getNumber() == currentToView){
			   // display it
			   System.out.print(tmpCurrent);
			   // and return it
			   return tmpCurrent;
			}
		}
	    // if we reach this code the Current account was not found so return null
        System.out.println("Current Account not found...");
	    return null;		
	}

	/////////////////////////////////////////////////////////////////
	// Method Name : delete()									   //
	// Return Type : void										   //
	// Parameters : None									 	   //
	// Purpose : Deletes the required Current record from currents //
	/////////////////////////////////////////////////////////////////	
	public void delete(){
		Current tempCurrent = view();
		
		// If the current != null, i.e. it was found then...
	    if(tempCurrent != null)
			// remove it from videoList
			currents.remove(tempCurrent);
	}

	//////////////////////////////////////////////////////////////
	// Method Name : edit()									    //
	// Return Type : void								        //
	// Parameters : None								        //
	// Purpose : Edits the required Current record in videoList //
	//////////////////////////////////////////////////////////////	
	public void edit(){
		// Call view() to find, display, & return the current to edit
		Current tempCurrent = view();
		
		// If tempCurrent != null, i.e. it was found then...
	    if(tempCurrent != null){
			// get it's index
			int index=currents.indexOf(tempCurrent);
			// read in a new video and...
			tempCurrent.read();
			// reset the object in currents
			currents.set(index, tempCurrent);
		}
	}

	/////////////////////////////////////////////////////////////
	// Method Name : writeRecordsToFile()    				   //
	// Return Type : void									   //
	// Parameters : None									   //
	// Purpose : Writes the ArrayList currents to the          //
	//				 File Currents.bin before closing the File //
	/////////////////////////////////////////////////////////////	
	public void writeRecordsToFile(){
		ObjectOutputStream os=null;
		try{
			// Serialize the ArrayList...
			FileOutputStream fileStream = new FileOutputStream(FILENAME);

			os = new ObjectOutputStream(fileStream);
			
			os.writeObject(currents);
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot create file to store joint accounts.");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			try{
			   os.close();
			}
			catch(IOException ioe){
				System.out.println("Cannot close file.");
			}
		}		
	}
	
	/////////////////////////////////////////////////////////////
	// Method Name : readRecordsFromFile()    			       //
	// Return Type : void									   //
	// Parameters : None									   //
	// Purpose : Reads the ArrayList currents from the         //
	//				 File Currents.bin before closing the File //
	/////////////////////////////////////////////////////////////	
	// currents = (ArrayList<Current>is.readObject()
	// issues an unchecked or unsafe operations warning, 
	// to warn that we may not be reading an ArrayList from the file.
	// We know we are, so we know it is safe, so we SupressWarnings
	@SuppressWarnings("unchecked") 
	public void readRecordsFromFile(){
		ObjectInputStream is=null;
		try{
			// Deserialize the ArrayList...
			FileInputStream fileStream = new FileInputStream(FILENAME);

			is = new ObjectInputStream(fileStream);
		
			currents = (ArrayList<Current>)is.readObject();

			// COULD use code below to ensure it is an ArrayList
			// BUT no need-we are confident file contains an ArrayList
			// Object o = is.readObject(); 	// READ an object from the file
			// if(o instanceof ArrayList)  	// IF object is an ArrayList
			//    currents=(ArrayList<Current>)o;//    ASSIGN object to currents
			
			// After we have deserialized the ArrayList, set nextUniqueNumber
			// in Account to last number read from file,
			// using Accounts static method setNextUniqueNumber()
			// Make sure there are elements in currents first
			if(currents.size() != 0)
				Account.setNextUniqueNumber((currents.get(currents.size()-1).getNumber())+1);
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot find joint accounts file.");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}	
		finally{
			try{
			   if(is != null)
				   is.close();
			}
			catch(IOException ioe){
				System.out.println("Cannot close file.");
			}
		}
	}
}

