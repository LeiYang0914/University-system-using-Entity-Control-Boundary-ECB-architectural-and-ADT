package boundary;

import java.util.Scanner;
import control.CourseManager;
import control.TutorManager;
/**
 *
 * @author Ng Wei Khang
 */

public class CourseUI {
	
    private CourseManager CourseManager;
    private TutorManager tutorManager;
    
    public CourseUI(){
    }

    public CourseUI(CourseManager CourseManager, TutorManager tutorManager) {
        this.CourseManager = CourseManager;
        this.tutorManager = tutorManager;
        CourseManager.predefinedProgramme();
    }

    public void courseMaintenance() {
        int choice;
        boolean validInput;
        
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("");
            System.out.println("==============================");
            System.out.println("+         MAIN MENU          +");
            System.out.println("==============================");
            System.out.println("+     1. Add New Course      +");
            System.out.println("+     2. Delete Course       +");
            System.out.println("+     3. Search Course       +");
            System.out.println("+     4. Amend Course        +");    
            System.out.println("+     5. List All Course     +");
            System.out.println("+     6. Report              +");
            System.out.println("+     0. Quit                +");
            System.out.println("==============================");
            System.out.print("\n Enter choice: ");

            validInput = false;

            // Check if the input is an integer
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (choice >= 0 && choice <= 6) {
                    validInput = true; // Input is valid
                }
            } else {
                scanner.nextLine(); // Consume the invalid input
                choice = -1; // Set choice to an invalid value
            }

            if (validInput) {
                switch (choice) {
                    case 0:
                    	exitMessage();
                        return;   
                    case 1:
                    	CourseManager.addNewCourse();
                        System.out.println("\nCourse added successfully.");
                        pressEnter();
                        break;
                    case 2:
                    	CourseManager.deleteCourse();
                        break;
                    case 3:
                    	CourseManager.findCourse();
                        break;
                    case 4:
                    	CourseManager.listCourse();
                    	CourseManager.amendCourse();
                        pressEnter();
                        break;
                    case 5:
                        CourseManager.listCourse();
                        pressEnter();
                        break;
                        
                    case 6:
                    	CourseManager.generateReport();
                        pressEnter();
                    	break;
                    	
                    default:
                        // Invalid choice, let the user know
                        System.out.println("Invalid choice. Please choose a valid option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid option (0-6).");
            }
        } while (choice != 0);
    }
    public void existCourseCode() {
        System.out.println("Course Code exist.Please write a new Course Code");
    }
    
    public void successfulDeleteMessage() {
        System.out.println("\nYou have successful delete!\n");
    }
    
    public void successfulSearchMessage() {
        System.out.println("\nYou have successful search the Course:");
    }
    
    public void successfulAmendMessage() {
        System.out.println("\nYou have successful update the Course Details.\n");
    }
    
    public void enterCourseCode() {
        System.out.print("\nEnter course code (4 letters + 4 digits, max 8 characters): ");
    }
    
    public void enterCourseName() {
        System.out.print("Enter course name: ");
    }
    
    public void enterCourseSession() {
        System.out.print("Enter course session (e.g. 2): ");
    }
    
    public void enterCourseCreditHour() {
        System.out.print("Enter course credit hours (2-4): ");
    }
    
    public void invalidCourseCode(){
        System.out.println("Invalid Course Code.Please Try Again\n");
    }
    
    public void invalidCourseCreditHour() {
        System.out.println("Invalid credit hours. Please enter a value between 2 and 4.\n");
    }
    
    public void invalidCourseSession() {
        System.out.println("Invalid session! Please write a correct session!\n");
    }
    
    public void invalidLength(){
        System.out.println("Invalid length. Please enter a course code with 8 or fewer characters.\n");
    }
    
    public void invalidFormat() {
        System.out.println("Invalid format. Please enter a valid course code.\n");
    }
        
    public void invalidInput() {
        System.out.println("Invalid input. Please enter a valid integer value between 2 and 4.\n");
    }
    
    public void exitMessage() {
        System.out.println("\nYou have leave the Course Menu!\n");
    }
  
    public void invalidChoice() {
        System.out.println("\nPlease choose a correct choice!\n");
    }
    
    public void newAmendNewCourse(String output) {
       System.out.println("\nAmend New " + output + " Details");
       System.out.println("------------------------");
    }
    
    public void courseDetails(String code,String name,int creditHour,int session) {
       System.out.println("Course Code = "+code+
                "\nCourse Name = "+name+
                "\nCourse Credit Hours = "+creditHour+
                "\nCourse Session = "+session
        );
    }
    
    public void listHeader() {
        System.out.printf("\n%-15s %-50s %-20s %-10s %n","Course Code", "Course Name", "Credit Hours", "Session");
    }
    
    public void topReport() {
        System.out.println("\n================================================================================");
        System.out.println("+                               Course Report                                  +");
        System.out.println("================================================================================");
    }
    
    public void emptyReport() {
        System.out.println("No courses available to generate a report.");
    }
    
    public void headerReport() {
        System.out.printf("%-15s %-30s %-20s %-10s\n", "Course Code", "Course Name", "Credit Hours", "Session");
        System.out.println("================================================================================");
    }
    
    public void listReport(String code,String name,int creditHour,int session) {
        System.out.printf("%-15s %-30s %-20d %-10d\n",code,name,creditHour,session);
    }
    
    public void footerReport() {
        System.out.println("================================================================================");
    }
    
    public void pressEnter() {
        System.out.println("\nPress Enter key to continue....");
        
        try {
            System.in.read();
            
        }
        catch (Exception e){
            
            System.out.println("An Error, the system will end now");
        }
    }
}