import java.util.Scanner;
import boundary.CourseUI;
import boundary.ProgrammeUI;
import boundary.TutorUI;
import control.CourseManager;
import control.ProgrammeManager;
import control.TutorManager;

public class main {
    public static void main(String[] args) {
        CourseManager CourseManager = new CourseManager();
        TutorManager tutorManager = new TutorManager(CourseManager);
    	ProgrammeManager progControl = new ProgrammeManager(CourseManager);
		ProgrammeUI programmeUI = new ProgrammeUI(progControl);
        CourseUI CourseManagerUI = new CourseUI(CourseManager, tutorManager);
        TutorUI TutorUI = new TutorUI(CourseManager);

        int choice;
        
        do {
            System.out.println("==========================================");
            System.out.println("+              Main Menu:                +");
            System.out.println("==========================================");
            System.out.println("+   1. Programme Management System       +");
            System.out.println("+   2. Course Management System          +");
            System.out.println("+   3. Tutor Management System           +");
            System.out.println("+   0. Exit                              +");
            System.out.println("==========================================");
            System.out.print("Enter your choice: ");

            choice = new Scanner(System.in).nextInt();

            switch (choice) {
                case 1:
                    // Run Programme Management System
                	programmeUI.programmeMaintenance();
                    break;
                case 2:
                	// Run Course Management System
                	CourseManagerUI.courseMaintenance();
                    break;
                case 3:
                	// Run Tutor Management System
                	TutorUI.runTutorManagementSystem();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }

        } while (choice != 0);
    }
}
