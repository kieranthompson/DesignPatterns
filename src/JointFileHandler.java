
import java.util.*;
import java.io.*;

////////////////////////////////////////////////////////////////////////
//                    class JointFileHandler                    		 //
//   This class contains an ArrayList, and the methods for modifying  //
//  the ArrayList as required, as well as the methods for serializing //
//               and deserializing the ArrayList                      //
////////////////////////////////////////////////////////////////////////
public class JointFileHandler{
	// Constant FILENAME for the file to be created
	private final String FILENAME = "joints.bin";

	// An ArrayList called joints - for storing a list of joints
	private ArrayList<Joint> joints;

	private static JointFileHandler handlerObject = new JointFileHandler();


	// Default Constructor
	private JointFileHandler(){
		joints = new ArrayList<Joint>();
	}
	
	//////////////////////////////////////////////////////
	// Method Name : add()								//
	// Return Type : void								//
	// Parameters : None								//
	// Purpose : Reads one Joint record from the user   //
	//           and adds it to the ArrayList joints    //
	//////////////////////////////////////////////////////	
	public void add(){
	   // Create a Joint object
		Joint tmpJoint = new Joint();
		
		// Read its details
		tmpJoint.read();
		
		// And add it to the joints
		joints.add(tmpJoint);
	}

	////////////////////////////////////////////////////////
	// Method Name : list()			  				      //
	// Return Type : void								  //
	// Parameters : None								  //
	// Purpose : Lists all Joint records in the ArrayList //
	////////////////////////////////////////////////////////	
	public void list(){
		// for every Joint object in joints
      for(Joint tmpJoint:joints)
			// display it
			System.out.println(tmpJoint);
	}
	
	////////////////////////////////////////////////////////////
	// Method Name : view()			     			          //
	// Return Type : void									  //
	// Parameters : None									  //
	// Purpose : Displays the required Joint record on screen //
	////////////////////////////////////////////////////////////	
	public Joint view(){
		Scanner keyboard = new Scanner(System.in);
		boolean goodInput = false;
		int jointToView = 0;

		do{		
			try{
				// try read the number of the video to be viewed from the user
				System.out.print("ENTER NUMBER OF JOINT ACCOUNT : ");
				jointToView=keyboard.nextInt();
				goodInput = true;
			}
			catch(InputMismatchException e){
			   System.out.println("INVALID number. RE_ENTER.");
				// clear the buffer - Java bug!
				char c = keyboard.next().charAt(0);
			}
		}while(!goodInput);

		// for every Joint object in joints
        for(Joint tmpJoint:joints){
			// if it's number equals the number of the jointToView
			if(tmpJoint.getNumber() == jointToView){
			   // display it
			   System.out.print(tmpJoint);
			   // and return it
			   return tmpJoint;
			}
		}
	    // if we reach this code the Joint account was not found so return null
        System.out.println("Joint Account not found...");
	    return null;		
	}

	/////////////////////////////////////////////////////////////
	// Method Name : delete()								   //
	// Return Type : void									   //
	// Parameters : None									   //
	// Purpose : Deletes the required Joint record from joints //
	/////////////////////////////////////////////////////////////	
	public void delete(){
		Joint tempJoint = view();

		// If the joint != null, i.e. it was found then...
	    if(tempJoint != null)
			// remove it from joints
			joints.remove(tempJoint);
	}

	/////////////////////////////////////////////////////////
	// Method Name : edit()								   //
	// Return Type : void								   //
	// Parameters : None								   //
	// Purpose : Edits the required Joint record in joints //
	/////////////////////////////////////////////////////////	
	public void edit(){
		// Call view() to find, display, & return the joint to edit
		Joint tempJoint = view();
		
		// If tempJoint != null, i.e. it was found then...
	    if(tempJoint != null){
			// get it's index
			int index=joints.indexOf(tempJoint);
			// read in a new video and...
			tempJoint.read();
			// reset the object in joints
			joints.set(index, tempJoint);
		}
	}

	///////////////////////////////////////////////////////
	// Method Name : writeRecordsToFile()    			 //
	// Return Type : void								 //
	// Parameters : None								 //
	// Purpose : Writes the ArrayList joints to the      //
	//			 File Joints.bin before closing the File //
	///////////////////////////////////////////////////////	
	public void writeRecordsToFile(){
		ObjectOutputStream os=null;
		try{
			// Serialize the ArrayList...
			FileOutputStream fileStream = new FileOutputStream(FILENAME);
	
			os = new ObjectOutputStream(fileStream);
			
			os.writeObject(joints);
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
	
	///////////////////////////////////////////////////////
	// Method Name : readRecordsFromFile()    			 //
	// Return Type : void								 //
	// Parameters : None								 //
	// Purpose : Reads the ArrayList joints from the     //
	//			 File Joints.bin before closing the File //
	///////////////////////////////////////////////////////	
	// joints = (ArrayList<Joint>is.readObject()
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
		
			joints = (ArrayList<Joint>)is.readObject();

			// COULD use code below to ensure it is an ArrayList
			// BUT no need-we are confident file contains an ArrayList
			// Object o = is.readObject(); 	// READ an object from the file
			// if(o instanceof ArrayList)  	// IF object is an ArrayList
			//    joints=(ArrayList<Joint>)o;//    ASSIGN object to joints
		
			// After we have deserialized the ArrayList, set nextUniqueNumber
			// in Account to last number read from file,
			// using Accounts static method setNextUniqueNumber()
			// Make sure there are elements in joints first
			if(joints.size() != 0)
				Account.setNextUniqueNumber((joints.get(joints.size()-1).getNumber())+1);
			
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

	public static JointFileHandler getInstance(){
		return handlerObject;
	}

	public void showMessage(){
		System.out.println("Single instance created");
	}
}
