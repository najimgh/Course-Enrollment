// -----------------------------------------------------
// Assignment 4
// Question: II
// Written by: Najim Ghafourzadeh 40064350
// -----------------------------------------------------

import java.util.Scanner;

/**
 * This class implements the DiretlyRelayable class and creates a Course.
 * @author Najim Ghafourzadeh
 *
 */
public class Course implements DirectlyRelatable{

	private String courseID;
	private String courseName;
	private double credit;
	private String preReqID;
	private String coReqID;
	
	/**
	 * Constructor for the Course Class
	 * @param courseID The ID of the Course
	 * @param courseName The name of the Course
	 * @param credit The number of credits
	 * @param preReqID The pre-requisite
	 * @param coReqID The co-requisite
	 */
	public Course(String courseID, String courseName, double credit, String preReqID, String coReqID) {
		
		this.courseID = courseID;
		this.courseName = courseName;
		this.credit = credit;
		this.preReqID = preReqID;
		this.coReqID = coReqID;
	}
	
	/**
	 * This is the copy constructor.
	 * @param C A course object
	 * @param value A string which will be the ID of the course.
	 */
	public Course(Course C, String value) {
		
		if(C != null) {
	
		this.courseID = value;
		this.courseName = C.courseName;
		this.credit = C.credit;
		this.preReqID = C.preReqID;
		this.coReqID = C.coReqID;
		}
	}
	
	//Creating Scanner to read input
	Scanner keyboard = new Scanner(System.in);
	
	/**
	 * This method returns a clone/copy of the Course object 
	 * @return A clone of the Course object.
	 */
	public Course clone() {
		
		System.out.println("Enter Course ID:");
		
		String value = keyboard.next();
		
		Course cloned = new Course(this, value);
		
		return cloned;
		
	}
	
	
	/*
	 * Getters and Setters
	 */
	
	/**
	 * This returns the courseID
	 * @return courseID
	 */
	public String getCourseID() {
		return courseID;
	}
	/**
	 * This sets the CourseID 
	 * @param courseID
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	/**
	 * This returns the CourseName
	 * @return courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * This sets the CourseName
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * This returns the credit
	 * @return credit
	 */
	public double getCredit() {
		return credit;
	}
	/**
	 * This sets the credit
	 * @param credit
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}
	/**
	 * This returns the Pre-Requisite
	 * @return preReqID
	 */
	public String getPreReqID() {
		return preReqID;
	}
	/**
	 * This sets the Pre-Requisite.
	 * @param preReqID
	 */
	public void setPreReqID(String preReqID) {
		this.preReqID = preReqID;
	}
	/**
	 * This returns the Co-Requisite
	 * @return coReqID
	 */
	public String getCoReqID() {
		return coReqID;
	}
	/**
	 * This sets the Co-Requisite
	 * @param coReqID
	 */
	public void setCoReqID(String coReqID) {
		this.coReqID = coReqID;
	}


	/*
	 * This method returns a String about the Course
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		String output = "Course ID: " + courseID + "\nCourse Name: " + courseName + "\nCredit: " + credit + "\nPre-Requisite ID: " + 
						preReqID + "\nCo-Requisite ID: " + coReqID;
		
		return output;
	}
	

	/**
	 * This equals method compares two Course objects. The method returns true if all the attributes are the same, but
	 * for the exception of the CourseID attribute.
	 * @param obj
	 * @return true or false
	 */
	public boolean equals(Course obj) {
			
			Course aCourse = obj;
			
			if(this.coReqID == null) {
			
			return (this.courseName.equals(aCourse.courseName) && this.credit == aCourse.credit && this.preReqID.equals(aCourse.preReqID)
					&& this.coReqID == aCourse.coReqID);
			}
			
			if(this.preReqID == null) {
				
				return (this.courseName.equals(aCourse.courseName) && this.credit == aCourse.credit && this.preReqID == (aCourse.preReqID)
						&& this.coReqID.equals(aCourse.coReqID));
			}
			
			if(this.coReqID == null && this.preReqID == null) {
				
				return (this.courseName.equals(aCourse.courseName) && this.credit == aCourse.credit && this.preReqID == (aCourse.preReqID)
						&& this.coReqID == (aCourse.coReqID));
			}
			
			if(this.coReqID != null && this.preReqID != null) {
				
				return (this.courseName.equals(aCourse.courseName) && this.credit == aCourse.credit && this.preReqID.equals(aCourse.preReqID)
						&& this.coReqID.equals(aCourse.coReqID));
			}
			
			return false;
			
		}
		


	//Implementing method from the DirectlyRelatable interface.
	/*
	 * (non-Javadoc)
	 * @see DirectlyRelatable#isDirectlyRelated(Course)
	 */
	public boolean isDirectlyRelated(Course C) {

		if(this.preReqID.equals(C.courseID) || this.coReqID.equals(C.courseID)) {
			return true;
		}
		
		return false;
	}
	
	
}
