// -----------------------------------------------------
// Assignment 4
// Question: I
// Written by: Najim Ghafourzadeh 40064350
// -----------------------------------------------------

/**
 * This interface has one method which will be able to determine if two Courses are directly related.
 * @author Najim Ghafourzadeh
 *
 */
public interface DirectlyRelatable {

	/**
	 * This method returns true if the passed Course parameter is a pre-requisite or co-requisite of the current course object, 
	 * or vise versa (hence the courses are directly related); otherwise it returns false.
	 * @param C Course object
	 * @return true or false
	 */
	public boolean isDirectlyRelated(Course C);
	
}
