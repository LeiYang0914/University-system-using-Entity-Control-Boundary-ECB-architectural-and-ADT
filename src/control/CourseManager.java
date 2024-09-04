package control;

import java.util.Scanner;
import adt.*;
import entity.*;
import boundary.CourseUI;

/**
 *
 * @author Ng Wei Khang
 */

public class CourseManager {

    private SortedArrayListInterface<Course> courseList = new SortedArrayList<>();
    private CourseUI courseUi = new CourseUI();
    
    public void predefinedProgramme(){
    	//courseCode, courseName, courseCreditHours, courseSession
        Course newCourse1 = new Course("BAMS2614","MATH AND GAME TECHNOLOGY", 3 , 1);
        Course newCourse2 = new Course("BACS2063","DATA STRUCTURE AND ALGORITHM", 4 , 1);
        Course newCourse3 = new Course("BACS3074","ARTIFICIAL INTELLIGENCE", 3 , 2);
        Course newCourse4 = new Course("BMMS2074","STATISTIC FOR DATA SCIENCE", 4 , 2);
        Course newCourse5 = new Course("BACS3183","ADVANCED DATABASE MANAGEMENT", 4 , 3);
        courseList.add(newCourse1);
        courseList.add(newCourse2);
        courseList.add(newCourse3);
        courseList.add(newCourse4);
        courseList.add(newCourse5);

    }
    
    public void addNewCourse() {
        Course newCourse = ADDnewCourseDetails();
        courseList.add(newCourse);
    }
    
    
    public void deleteCourse() {
    	listCourse();
        String code = courseCode();
        Course del = new Course(code, null, 0, 0);
        if(courseList.contains(del) != -1){
        courseList.remove(del);
        courseUi.successfulDeleteMessage();
        courseUi.pressEnter();
        }else{
            courseUi.invalidCourseCode();
            courseUi.pressEnter();
            deleteCourse();
        }
    }

    public void findCourse() {
        String code = courseCode();
        Course find = new Course(code, null, 0, 0);
        if(courseList.contains(find) != -1){
        courseUi.successfulSearchMessage();
        courseUi.courseDetails(courseList.getEntry(courseList.contains(find)).getCourseCode(),
                courseList.getEntry(courseList.contains(find)).getCourseName(),
                courseList.getEntry(courseList.contains(find)).getCourseCreditHours(),
                courseList.getEntry(courseList.contains(find)).getCourseSession());
        courseUi.pressEnter();
        }else{
            courseUi.invalidCourseCode();
            findCourse();
        }
    }

    public void listCourse() {  
        courseUi.listHeader();
        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            System.out.println(courseList.getEntry(i));
        }
    }

    public void amendCourse() {
        String code = courseCode();
        Course oldAmend = new Course(code, null, 0, 0);
        if(courseList.contains(oldAmend) != -1){
        // Get the old course from the course list
        Course oldCourse = courseList.getEntry(courseList.contains(oldAmend));
        courseUi.courseDetails(oldCourse.getCourseCode(),
                oldCourse.getCourseName(),
                oldCourse.getCourseCreditHours(),
                oldCourse.getCourseSession());
        
        courseUi.newAmendNewCourse(code);
        String name = courseName();
        int creditHours = courseCreditHours();
        int session = courseSession();
        Course newAmend = new Course(code, name, creditHours, session);
        
        // Update the course in the course list
        courseList.replace(oldAmend, newAmend);

        courseUi.successfulAmendMessage();
        }else{
            courseUi.invalidCourseCode(); 
            courseUi.pressEnter();
            amendCourse();
        }
    }

	public SortedArrayList<Course> getCourseList() {
		return (SortedArrayList<Course>) courseList;
	}
	
	public String courseCode() {
        String code;
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);
        
        do {
            courseUi.enterCourseCode();
            code = scanner.nextLine().toUpperCase();

            if (code.length() <= 8) {
                // Check if the first 4 characters are letters and the last 4 are digits
                boolean validFormat = true;
                for (int i = 0; i < code.length(); i++) {
                    char ch = code.charAt(i);
                    if ((i < 4 && !Character.isLetter(ch)) || (i >= 4 && !Character.isDigit(ch))) {
                        validFormat = false;
                        break;
                    }
                }
                if (validFormat) {
                    validInput = true; // Input is valid, exit the loop
                } else {
                    courseUi.invalidFormat();
                }
            } else {
                courseUi.invalidLength();
            }
        } while (!validInput);

        return code;
    }

    public String courseName() {
        String name;
        Scanner scanner = new Scanner(System.in);
        do {
            courseUi.enterCourseName();
            name = scanner.nextLine().toUpperCase();
        } while (name.isEmpty()); // Validate that name is not empty
        return name;
    }

    public int courseCreditHours() {
        int creditHours;
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);
        
        do {
            courseUi.enterCourseCreditHour();
            // Check if the input is an integer
            if (scanner.hasNextInt()) {
                creditHours = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                
                if (creditHours >= 2 && creditHours <= 4) {
                    validInput = true; // Input is valid, exit the loop
                } else {
                    courseUi.invalidCourseCreditHour();
                }
            } else {
                scanner.nextLine(); // Consume the invalid input
                courseUi.invalidInput();
                creditHours = -1; // Set creditHours to an invalid value
            }
        } while (!validInput);

        return creditHours;
    }
    
    public int courseSession() {
        int session;
        boolean n = true;
        Scanner scanner = new Scanner(System.in);
        do{
            courseUi.enterCourseSession();
            session = scanner.nextInt();
            if(session<0){
                n=false;
                courseUi.invalidCourseSession();
            }else{
                n=true;
            }
        }while(!n);
        return session;
    }
    
    public Course ADDnewCourseDetails() {
        String courseCode = courseCode();
        Course newCode = new Course(courseCode, null, 0, 0);
        if(courseList.contains(newCode) != -1){
            courseUi.existCourseCode();
            ADDnewCourseDetails();
        }
        String courseName = courseName();
        int creditHours = courseCreditHours();
        int session = courseSession();
        return new Course(courseCode, courseName, creditHours,session);
    }
    
    public void generateReport() {
        courseUi.topReport();

        // Check if there are any courses to generate a report
        if (courseList.isEmpty()) {
            courseUi.emptyReport();
            return;
        }
        courseUi.headerReport();
        
        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            Course course = courseList.getEntry(i);
            courseUi.listReport(course.getCourseCode(), course.getCourseName(),
                    course.getCourseCreditHours(), course.getCourseSession());
        }
        courseUi.footerReport();
    }
}