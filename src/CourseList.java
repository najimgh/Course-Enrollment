// -----------------------------------------------------
// Assignment 4
// Question: III
// Written by: Najim Ghafourzadeh 40064350
// -----------------------------------------------------

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The CourseList class contains a private CourseNode Class. This class creates a Linked list and has the proper methods 
 * to manipulate the Linked List.
 * @author Najim Ghafourzadeh
 *
 */

public class CourseList {

	private CourseNode head;
	private int size;
	
	private int iterations = 0;
	
	//Default Constructor
	
	/**
	 * This is the default constructor which sets the head of the linked List to null and its size to 0.
	 */
	public CourseList() {
		
		this.head = null;
		this.size = 0;
	}
	
	//Copy Constructor
	/**
	 * This is the copy Constructor. It creates a deep copy of a list.
	 * @param aList A linked list
	 */
	public CourseList(CourseList aList) {
		
		if(aList.head == null) {
			head = null;
		}else {
			
			this.size = aList.size;
			this.head = new CourseNode(aList.head);
	
			CourseNode copy = this.head;
			
			CourseNode current = aList.head;
			
			while(current.getNext() != null) {
				
				copy.next = new CourseNode(current.next);
				
				copy = copy.getNext();
				current = current.getNext();
			}
		}	
	}
	
	
	
	/**
	 * This method returns the number of iterations for the find() method.
	 * @return The number of iterations
	 */
	public int getIterations() {
		return this.iterations;
	}
	
	/**
	 * This method adds a Course to the head of the Linked list
	 * @param aCourse A course object
	 */
	public void addToStart(Course aCourse) {
		
		head = new CourseNode(aCourse, head);
		size++;
	}
	
	/**
	 * This method inserts a Course object at the specified index.
	 * If the index specified is out of bounds, the method will throw a NoSuchElementException.
	 * @param aCourse A Course object
	 * @param index An integer
	 */
	public void insertAtIndex(Course aCourse, int index) {
		
		try {
			
			if(index >= 0 && index <= (this.size-1)) {
				
				if(index == 0) {
					
					this.addToStart(aCourse);
				}else {
				
					CourseNode current = head;
					
					CourseNode temp = new CourseNode(aCourse, null);
					
					if(current != null) {
						
						//iterate to the indicated index or the last element in the list. Whichever comes first.
						for(int i = 0; i < index-1 && current.getNext() != null; i++) {
							
							current = current.getNext();
						}
						
					}
				
					//Setting the new node's next node to the node at the indicated index.
					temp.setNext(current.getNext());
					
					//Setting the current node's next node to the new node.
					current.setNext(temp);
					
					size++;
				}
				
			}else{
				throw new NoSuchElementException();
			}
		
		}catch(NoSuchElementException e) {
			System.exit(0);
		}
	}
	
	
	/**
	 * This method deletes a Node specified at the index.
	 * If the index specified is out of bounds, the method will throw a NoSuchElementException.
	 * @param index An integer
	 */
	public void deleteFromIndex(int index) {
		
		try {
		
		if(index >= 0 && index <= (this.size-1)) {
			
			if(index == 0) {
				this.deleteFromStart();
			}else {
			
				CourseNode current = head;
				
				for(int i = 0; i < index-1; i++) {
					
					//If the node at the index is null, then break out of the loop.
					if(current.getNext() == null)
						return; //Returning void.
					
					current = current.getNext();	
				}
				
				//Setting the current node to the node after the one we want to delete.
				current.setNext(current.getNext().getNext());
				
				size--;
				
			}
		}else
			throw new NoSuchElementException();
		
		}catch(NoSuchElementException e) {
			System.exit(0);
		}
	}
	
	
	/**
	 * This method deletes a the head Node.
	 */
	public void deleteFromStart() {
		
		CourseNode current = head;
		
		if(current == null)
			return;
		
		//Setting the head to the second element.
		head = current.getNext();
		
		//Setting the pointer of the head to null.
		current.setNext(null);
		
		size--;
	}
	
	/**
	 * This method replaces a Node at the specified index.
	 * If the index specified is out of bounds, the method will simply return nothing.
	 * @param aCourse A Course object
	 * @param index An integer
	 */
	public void replaceAtIndex(Course aCourse, int index) {
		
		if(index >= 0 && index <= (this.size-1)) {
			
			if(index == 0) {
				this.deleteFromStart();
				this.addToStart(aCourse);
			}else {
			
				CourseNode current = head;
				
				CourseNode temp = new CourseNode(aCourse, null);
				
				for(int i = 0; i < index-1; i++) {
					
					current = current.getNext();
				}
				
				//If the node at the index is null, then current node's next node points to the new node.
				if(current.getNext() == null) {
					
					current.setNext(temp);
				}else {
				
				//Setting the new node's next node to the node after the index.
				temp.setNext(current.getNext().getNext());
				
				//Setting the current node's next node to the new node.
				current.setNext(temp);
				}
			
		}
			
		}else
			return;
		
	}
	
	
	/**
	 * This method finds a Node specified by the ID of a course.
	 * @param ID A String which specifies a course ID.
	 * @return A Node specified by a course ID.
	 */
	public CourseNode find(String ID) {
		/*
		 * This method may result in a privacy leak.
		 * We initialize current to head (which is a private variable)
		 * and then return a pointer which is equal to current.
		 * This returns the address corresponding to the Node in which 
		 * we are interested.
		 */
		
		CourseNode current = head;
		
		CourseNode pointer = null;
		
		iterations = 0;
		
		while(current != null) {
			
			if(current.getCourse().getCourseID().equals(ID)) {
								
				pointer = current;
				return pointer; //returning a pointer to the Node where we found a matching courseID.
			}else {
				
				
				++iterations; //incrementing number of iterations.
				
				current = current.getNext();
			}
			
		}
		
		return null; //Returning null if no matching courseID was found.
	}
	
	
	
	/*
	 * Creating a get method to be able to use it 
	 * in conjunction with the find() method.
	 */
	/**
	 * This method returns the course found at a Node from the find() method.
	 * @param node A node found by the find() method
	 * @return A Course object 
	 */
	public Course get(CourseNode node) {
		/*
		 * The get() method also can result in a privacy leak.
		 * Since we are passing the returned value of the find()
		 * method (a reference to the node we are interested in) and then 
		 * returning the course associated with the reference passed in the parameter.
		 */
		return node.getCourse();
		
	}
	
	
	
	/**
	 * This method returns true if a Course specified by ID is found in a Linked List.
	 * @param ID A String which specifies a CourseID
	 * @return true or false
	 */
	public boolean contains(String ID) {
		
		CourseNode current = head;
		
		while(current != null) {
			
			if(current.getCourse().getCourseID().equals(ID)) {
				
				return true; //returning true if we found a matching courseID.
			}else {
				
				current = current.getNext();
			}	
		}
		
		return false;	
	}
	
	
	/**
	 * This method return true or false if two Linked Lists are equal.
	 * @param aList A Linked list
	 * @return true or false
	 */
	public boolean equals(CourseList aList) {
		
		 //null checking
        if(this == null && aList == null)
            return true;
     
        if((this == null && aList != null) || (this != null && aList == null))
            return false;
        //Checking whether they have the same size.
        if(this.size!=aList.size)
            return false;
        
        CourseNode current1 = this.head;
        CourseNode current2 = aList.head;
	
		while(current1.getNext() != null && current2.getNext() != null) {
			
			if(!current1.getCourse().equals(current2.getCourse())) {
				return false;
			}else {
				current1 = current1.getNext();
				current2 = current2.getNext();
			}
		}
		
		return true;
	}
	
	
	/*
	 * A toString method to display the list
	 */
	
	public String toString() {
		
		String output = "";
		
		if(head != null) {
			
			CourseNode current = head;
			
			while(current != null) {
				output += "[" + current.getCourse().toString() + "]\n\n";
				current = current.getNext();
			}
		}
		
		return output;
	}
	
	
		/**
		 * This class creates a Node.
		 * @author Najim Ghafourzadeh
		 *
		 */
		private class CourseNode{
		
		private Course C;
		private CourseNode next;
		
		/**
		 * Default Constructor which sets the Course to null and the next Node to null.
		 */
		public CourseNode() {
			C = null;
			next = null;
		}
		
		/**
		 * Parameterized constructor
		 * @param data A Course
		 * @param next A Node
		 */
		public CourseNode(Course data, CourseNode next) {
			
			this.C = data;
			this.next = next;
		}
		
		
		/**
		 * Copy Constructor.
		 * @param aNode A Node
		 */
		public CourseNode(CourseNode aNode) {
			
			if(aNode != null) {
				
				this.C = new Course(aNode.C.getCourseID(), aNode.C.getCourseName(), aNode.C.getCredit(), aNode.C.getPreReqID(), aNode.C.getCoReqID());
				this.next = aNode.next;
			}
			
		}
		
		/**
		 * Clone method which makes a deep copy of the Node object.
		 */
		public CourseNode clone() {
			
			CourseNode aNode = new CourseNode(this);
			
			return aNode;
			
		}
		
		/*
		 * Getters and Setters
		 */
		
		/**
		 * Returns the Next node
		 * @return Next Node
		 */
		public CourseNode getNext() {
			return this.next;
		}
		/**
		 * Sets the next Node
		 * @param nextNode
		 */
		public void setNext(CourseNode nextNode) {
			this.next = nextNode;
		}
		
		/**
		 * Returns the Course of a Node
		 * @return Course of a Node
		 */
		public Course getCourse() {
			return this.C;
		}
		/**
		 * Sets the Course of a Node
		 * @param aCourse
		 */
		public void setCourse(Course aCourse) {
			this.C = aCourse;
		}
		
		
	}
}
