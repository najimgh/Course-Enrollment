// -----------------------------------------------------
// Assignment 4
// Question: IV
// Written by: Najim Ghafourzadeh 40064350
// -----------------------------------------------------


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class EnrolmentResults {
	
	static int numberCourses = 0;

	public static void main(String[] args) {
		
		CourseList list1 = new CourseList();
		CourseList list2 = new CourseList();
		
		Scanner keyboard = new Scanner(System.in); //Scanner for keyboard entries.
		
		System.out.println("Welcome to the program!\n");

		
		try{
			
			Scanner sc = new Scanner(new FileInputStream("Syllabus.txt"));
			
			int linesRead = 0;

			/*
			 * The following reads the file once and determines
			 * how many course objects will be needed.
			 */
			while(sc.hasNextLine()) {
								
				String str = sc.nextLine();
				
				if(!str.equals(""))
					linesRead++;
				
				if(linesRead == 3) {
					numberCourses++;
					linesRead = 0;
				}
				
			}
	
			sc.close(); //closing the file.
			
		}catch(FileNotFoundException e) {
			System.out.println("Could not read file or could not find file.");
			System.exit(0);
		}
		
		
		
		
		try {
			
			Scanner sc = new Scanner(new FileInputStream("Syllabus.txt"));
			
			int linesRead = 0;
			
			String[] data = new String[5];
			
			Course[] courses = new Course[numberCourses];
			
			
			int index = 0; //index for courses array
			
			/*
			 * The following reads the Syllabus file
			 * and parses the information contained in it.
			 * Once a course has been read and parsed, the courses array 
			 * is initialized. This courses array will contain all the course objects
			 * that are defined in the Syllabus file.
			 */
			while(sc.hasNextLine()) {
			
				String str = sc.nextLine();
				
				String[] values = str.split("\\s+");
						
				int size = values.length;
				
				
				if(size == 3) {
					
					linesRead++;
					
					data[0] = values[0];
					data[1] = values[1];
					data[2] = values[2];
					
				}
				
				
				
				if(size == 1) {
					
					if(values[0].equals("")) {
						continue;
					}else {
						
						linesRead++;

						
						if(values[0].equals("P")) {
							
							data[3] = null;
						}
						
						if(values[0].equals("C")) {
							data[4] = null;
						}
					}	
						
				}
				
				
				if(size == 2) {
					
					linesRead++;
					
					if(values[0].equals("P")) {
						
							data[3] = values[1];

					}
					
					if(values[0].equals("C")) {
							data[4] = values[1];

					}	
					
				}
				
				if(linesRead == 3) {
					
					linesRead = 0;
					
					double credits = Double.parseDouble(data[2]);
					
					courses[index] = new Course(data[0], data[1], credits, data[3], data[4]);
					index++;
				}
					
			}
			
			sc.close(); //Closing the file.
			
			int length = courses.length;
			
			/*
			 * Removing any duplicates from the list.
			 */
			
			for(int i = 0; i < length; i++) {
				
				for(int j = i+ 1; j < length; j++) {
					
					
					if(courses[i].equals(courses[j])) {
						
						int left = j;
						
						for(int k = j+1; k < length; k++, left++) {
							
							courses[left] = courses[k];
						}
						
						length--;
						j--;
					}
				}
			}
			
			/*
			 * Initializing new array containing non-duplicate elements.
			 */
			Course[] modified = new Course[length];
			
			for(int i = 0; i < length; i++) {
				
				modified[i] = courses[i];
				
			}
		
			
			/*
			 * Adding Courses to list.
			 */
			
			for(int i = 0; i < length; i++) {
				
				list1.addToStart(modified[i]);
			}
			
			
		
		}catch(FileNotFoundException e) {
			System.out.println("Could not read file or could not find file.");
			System.exit(0);
			
		}
		
		
		
		try {
			
			
			System.out.println("Please input the file name:");
			
			String fileName = keyboard.nextLine();
			
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			String line = "";
			
			ArrayList<String> data = new ArrayList<String>();
			
			/*
			 *  Reading and Storing content of the 
			 *  Request file into data arrayList.
			 */
			while((line = br.readLine()) != null) {
				
				
				String[] ID = line.split("\\s+");
				
				int checkLength = ID.length;
				
				if(checkLength != 0)
					data.add(ID[0]);
			}
			
			br.close(); //closing file
			
			ArrayList<String> finished = new ArrayList<String>();
			
			ArrayList<Course> request = new ArrayList<Course>();

	
			
			int size = data.size();
			int indexRequested = data.indexOf("Requested");
			
			
			/*
			 * The following loop parses the data ArrayList and puts the finished course ID's
			 * inside the finished ArrayList.
			 */
			for(int i = 1; i < indexRequested; i++) {
				
				
				finished.add(data.get(i));
			}

				
			
			/*
			 * The following loop adds the Requested Courses to the request ArrayList.
			 */
			
			if(indexRequested + 1 == size) { //If no requested courses have been entered then display following message.
				System.out.println("No enrolment courses found.");
			}else {
			
				for(int j = indexRequested + 1; j < size; j++) {
								
					if(list1.contains(data.get(j))) {
						
						Course match = list1.get(list1.find(data.get(j)));

						request.add(match);				
					}else {
		
						System.out.println("You cannot enroll in " + data.get(j) + " since it is not in the Syllabus");
					}	
				}
			}
			
			
			/*
			 * The following loop contains several other loops inside.
			 * Overall the following code determines if a student can enroll for a particular course
			 * and prints out the result.
			 */
			
			size = request.size();
			
			for(int i = 0; i < size; i++) {
				
				/*
				 * These two variables keep track of whether the student
				 * has the required pre-requisite or co-requisite.
				 */
				boolean hasPreReq = false;
				boolean hasCoReq = false;
				
				
				//Getting the current pre-Requisite and co-Requisite.
				String preReq = request.get(i).getPreReqID();
				String coReq = request.get(i).getCoReqID();
								
				
	
				/*
				 * The following loop determines if the pre-Requisite
				 * is equal to one of the Course ID's in the finished ArrayList.
				 */
				int finishSize = finished.size();
				
				for(int k = 0; k < finishSize; k++) {
					
					if(preReq != null) {
						
						
						if(preReq.equals(finished.get(k))) {
							hasPreReq = true;
							break;
							
						}
						
					}else
						hasPreReq = true;
				}

				
				
				/*
				 * The following loop determines if the co-Requisite
				 * is equal to one of the Course ID's in the finished ArrayList.
				 */
				for(int k = 0; k < finishSize; k++) {
				
					if(coReq != null) {
						
						if(coReq.equals(finished.get(k))) {
							hasCoReq = true;
							break;
							
						}else
							hasCoReq = false;
					}else
						hasCoReq = true;
					
					
				}
				
				
				/*
				 * The following loop determines if the co-Requisite
				 * is equal to one of the Course ID's in the request ArrayList.
				 */
				for(int j = 0; j < size; j++) {
					
					if(coReq != null) {
					
						
						if(coReq.equals(request.get(j).getCourseID())) {
							hasCoReq = true;
							
							break;
						}
					}else
						hasCoReq = true;
				}
				
				
				
				/*
				 * The following prints the appropriate message based on the student's finished 
				 * and requested courses.
				 */
				if(hasCoReq && hasPreReq) {
					
					
					if(coReq != null && preReq != null) {
						
						
						System.out.println("Student can enrol in " + request.get(i).getCourseID() + " as he/she has completed the pre-requisite "
								+ preReq + " and has the co-requisite " + coReq + ".");
					}
					
					
					if(preReq != null && coReq == null) {
						
						System.out.println("Student can enrol in " + request.get(i).getCourseID() + " as he/she has completed the pre-requisite "
								+ preReq + ".");
					}
					
					if(preReq == null && coReq != null) {
						
						System.out.println("Student can enrol in " + request.get(i).getCourseID() + " as he/she is enrolling for the co-requisite "
								+ coReq + ".");
					}
					
	
				}else {
					
					if((preReq.equals(coReq)) && (hasCoReq || hasPreReq)) {
						
						if(hasCoReq)
						System.out.println("Student can enrol in " + request.get(i).getCourseID() + " as he/she is enrolling for co-requisite "
								+ coReq + ".");
						
						if(hasPreReq)
							System.out.println("Student can enrol in " + request.get(i).getCourseID() + " as he/she has completed the pre-Requisite for "
									+ preReq + ".");
							
					}else
						System.out.println("Student can't enrol in " + request.get(i).getCourseID() + " as he/she doesn't have sufficient background needed.");
				}
				

			
			}

			
		}catch(IOException e) {
			System.out.println("Could not read file or could not find file.");
			System.exit(0);
		}
		
		
		/*
		 * THE FOLLOWING CODE TESTS THE IMPLEMENTATION OF SOME FUNCTIONS.
		 */
		
		
		/*
		 * The Following tests the find() function.
		 */
		System.out.println("\nPlease enter some Course ID:");
		
		String[] someID = new String[3];
				
		for(int i = 0; i < 3; i++) {
			
			someID[i] = keyboard.next();
			
			if(list1.find(someID[i]) != null) {
				
					System.out.println("It took " + list1.getIterations() + " iterations to find " + someID[i]);
			}else {
					System.out.println(someID[i] + " could not be found.");
	
			}
		}
		
		
		/*
		 * Creating a deep copy of the list1 array.
		 */
				
		CourseList copyList = new CourseList(list1);
				
		System.out.println("\n\nTHE FOLLOWING IS A COPY OF list1\n");
		
		System.out.println(copyList.toString() + "\n\n");
		
		
		/*
		 * CHECKING EQUALITY OF THE COPIED LIST AND THE ORIGINAL LIST.
		 */
		
		System.out.println("CHECKING EQUALITY OF THE COPIED LIST AND THE ORIGINAL LIST");

		if(copyList.equals(list1)) {
			System.out.println("TRUE, both are EQUAL\n");
		}else
			System.out.println("FALSE, they are not equal\n");

		
		
		/*
		 * Adding a course to copyList
		 */
		System.out.println("ADDING ELEMENT AT FIRST INDEX USING addToStart() method\n");
		
		Course a = new Course("abc", "name", 3.5, "pre", "co");
		
		copyList.addToStart(a);
				
		System.out.println(copyList);
		
		
		/*
		 * Adding a course to a certain index
		 */
		
		System.out.println("ADDING ELEMENT AT INDEX 2\n");
		
		Course b = new Course("bbb", "another", 4.0, "apre", "aco");
		
		copyList.insertAtIndex(b, 2);
		
		System.out.println(copyList);
		
		
		/*
		 * Adding a course to the last index
		 */
		System.out.println("ADDING ELEMENT AT LAST INDEX\n");
		
		Course c = new Course("ccc", "cbcb", 2.0, "cpre", "cco");
		
		copyList.insertAtIndex(c, 10);
		
		System.out.println(copyList);
		
		
		/*
		 * ADDING ELEMENT AT FIRST INDEX
		 */
		System.out.println("ADDING ELEMENT AT FIRST INDEX\n");

		
		Course d = new Course("ddd", "dcdcdc", 1.5, "dpre", "dco");
		
		copyList.insertAtIndex(d, 0);
		
		System.out.println(copyList);
		
		
		/*
		 * DELETING ELEMENT AT INDEX 3
		 */
		
		System.out.println("DELETING ELEMENT AT INDEX 3\n");
		
		copyList.deleteFromIndex(3);
		
		System.out.println(copyList);

		
		/*
		 * DELETING ELEMENT AT LAST INDEX
		 */
		System.out.println("DELETING ELEMENT AT LAST INDEX\n");

		copyList.deleteFromIndex(11);
		
		System.out.println(copyList);
		
		/*
		 * DELETING ELEMENT AT FIRST INDEX
		 */
		System.out.println("DELETING ELEMENT AT FIRST INDEX\n");
		
		copyList.deleteFromIndex(0);
		
		System.out.println(copyList);
		
		
		/*
		 * DELETING ELEMENT AT FIRST INDEX USING deleteFromStart() method
		 */
		System.out.println("DELETING ELEMENT AT FIRST INDEX USING deleteFromStart() method\n");
		
		copyList.deleteFromStart();;
		
		System.out.println(copyList);
		
		
		/*
		 * REPLACING ELEMENT AT INDEX 6
		 */
		
		System.out.println("REPLACING ELEMENT AT INDEX 6\n");
		
		Course e = new Course("eee", "eded", 4.0, "epre", "eco");
		
		copyList.replaceAtIndex(e, 6);
		
		System.out.println(copyList);

		
		/*
		 * REPLACING ELEMENT AT LAST INDEX
		 */
		System.out.println("REPLACING ELEMENT AT LAST INDEX\n");

		Course f = new Course("fff", "fefef", 1.0, "fpre", "fco");

		copyList.replaceAtIndex(f, 9);

		System.out.println(copyList);
		
		
		/*
		 * REPLACING ELEMENT AT FIRT INDEX
		 */
		System.out.println("REPLACING ELEMENT AT FIRST INDEX\n");

		Course g = new Course("ggg", "fgfg", 1.5, "gpre", "gco");

		copyList.replaceAtIndex(g, 0);

		System.out.println(copyList);
		
		/*
		 * REPLACING ELEMENT AT UNDEFINED INDEX
		 */
		System.out.println("REPLACING ELEMENT AT UNDEFINED INDEX");
		
		copyList.replaceAtIndex(g, 14);

		System.out.println("This should not change the linked list as the method returns nothing\n");

		System.out.println(copyList);

		
		/*
		 * CHECKING EQUALITY OF THE COPIED LIST AND THE ORIGINAL LIST.
		 */
		
		System.out.println("CHECKING EQUALITY OF THE COPIED LIST AND THE ORIGINAL LIST");

		if(list1.equals(copyList)) {
			System.out.println("TRUE, both are EQUAL\n");
		}else
			System.out.println("FALSE, they are not equal\n");
		
		
		System.out.println(list1);
		
		
		/*
		 * TESTING THE CLONE METHOD FOR COURSE OBJECTS
		 */
		System.out.println("TESTING THE CLONE METHOD FOR COURSE OBJECTS\n");
		
		Course h = new Course("hhh", "testing", 3.0, "preReq", "coReq");
		
		Course copy = h.clone();
		
		System.out.println(copy);
		
		/*
		 * TESTING THE EQUALS METHOD FOR COURSE OBJECTS
		 */
		System.out.println("\nTESTING THE EQUALS METHOD FOR COURSE OBJECTS\n");
		
		if(copy.equals(h)) {
			System.out.println("TRUE, both Course objects are equal\n");
		}else {
			System.out.println("FALSE, they are not equal\n");
		}

		
		/*
		 * TESTING THE isDirectlyRelated() method.
		 */
		
		System.out.println("TESTING THE isDirectlyRelated() method\n");

		
		Course first = list1.get(list1.find("COMP371"));
		
		Course second = list1.get(list1.find("COMP248"));
		
		if(first.isDirectlyRelated(second)) {
			System.out.println(second.getCourseID() + " is directly related to " + first.getCourseID() + "\n");		
			
		}else
			System.out.println(second.getCourseID() + " and " + first.getCourseID() + " are not directly related\n");
		
		
		
		if(first.isDirectlyRelated(h)) {
			System.out.println(h.getCourseID() + " is directly related to " + first.getCourseID());		
			
		}else
			System.out.println(h.getCourseID() + " and " + first.getCourseID() + " are not directly related");
		
	
	}

}
