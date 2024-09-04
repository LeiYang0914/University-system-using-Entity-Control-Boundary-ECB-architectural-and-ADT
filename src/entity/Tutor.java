/**
*
* @author Chan Li Yang 
* 
*/


package entity;
import adt.SortedArrayList;

public class Tutor implements Comparable<Tutor>{
	
	    private int id; // Unique identifier for each tutor
	    private String name;
	    private String email;
	    private String phoneNumber;
	    private SortedArrayList<String> coursesTaught;
	    private int semester;
	    private String degreeTitle;

	    public Tutor(int id, String name, String email, String phoneNumber, SortedArrayList<String> coursesTaught, int semester, String degreeTitle) {
	        this.id = id;
	        this.name = name;
	        this.email = email;
	        this.phoneNumber = phoneNumber;
	        this.coursesTaught = coursesTaught;
	        this.semester = semester;
	        this.degreeTitle = degreeTitle;
	    }

	    // Getters and setters for all attributes

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    public SortedArrayList<String> getcoursesTaught() {
	        return coursesTaught;
	    }

	    public void setcoursesTaught(SortedArrayList<String> coursesTaught) {
	        this.coursesTaught = coursesTaught;
	    }

	    public int getSemester() {
	        return semester;
	    }

	    public void setSemester(int semester) {
	        this.semester = semester;
	    }

	    public String getDegreeTitle() {
	        return degreeTitle;
	    }

	    public void setDegreeTitle(String degreeTitle) {
	        this.degreeTitle = degreeTitle;
	    }
	    
	    // Add a subject to the list of subjects taught
	    public void addCourse(String course) {
	    	coursesTaught.add(course);
	    }

	    // Remove a subject from the list of subjects taught
	    public void removeCourse(String course) {
	    	coursesTaught.remove(course);
	    }

	    // Check if a subject is taught by the tutor
	    public boolean teachesCourse(String course) {
	        return coursesTaught.contains(course) != -1;
	    }

	    @Override
	    public String toString() {
	        return  "============================================="+"\n" +
	        		"Tutor ID: " + getId() + "\n" +
	               "Name: " + getName() + "\n" +
	               "Email: " + getEmail() + "\n" +
	               "Phone Number: " + getPhoneNumber() + "\n" +
	               "Courses Taught: " + getcoursesTaught() + "\n" +
	               "Semester: " + getSemester() + "\n" +
	               "Degree Title: " + getDegreeTitle() + "\n"+
	               "============================================="+"\n" ;
	       
	    }

	    @Override
	    public int compareTo(Tutor other) {
	        // Compare tutors based on their ID
	        return Integer.compare(this.id, other.id);
	    }
}


