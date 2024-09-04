/**
*
* @author Chan Li Yang 
* 
*/

package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

import control.CourseManager;
import control.TutorManager;

public class TutorUI {	

    private CourseManager CourseManager;
    private TutorManager tutorManager;

    public TutorUI(CourseManager CourseManager) {
        this.CourseManager = CourseManager;
        this.tutorManager = new TutorManager(CourseManager);
    }
    
    public void runTutorManagementSystem() {
		
		int choice = 0;
		do{
			System.out.println("+====================================+");
			System.out.println("+              Menu                  +");
			System.out.println("+====================================+");
			System.out.println("+       1. Add Tutor                 +");
			System.out.println("+       2. Display Tutor             +");
			System.out.println("+       3. Remove Tutor              +");
			System.out.println("+       4. Edit Tutor                +");
			System.out.println("+       5. Search for Tutor Record   +");
			System.out.println("+       6. Clear All Record          +");
			System.out.println("+       7. Report                    +");
			System.out.println("+       8. Exit                      +");
			System.out.println("+====================================+");  
			System.out.print("Enter your choice : ");
			try {
				choice = new Scanner(System.in).nextInt();
				} catch (InputMismatchException e) {}
				 
			switch(choice){
				 case 1: tutorManager.addTutor();
				 break;
				 
				 case 2: tutorManager.displayTutor();
				 break;
				 
				 case 3: tutorManager.deleteTutor();
				 break;
				 
				 case 4: tutorManager.editTutor();
				 break;
				 
				 case 5: tutorManager.search();
				 break;
				 
				 case 6: tutorManager.clearAll();
				 break;
				 
				 case 7: tutorManager.getReport();
				 break;
				 
				 case 8:  System.out.println("Exiting Tutor Management System...");
				 		  System.out.println();
                 return; // Return to the main menu
				 
				 default:System.out.println("Invalid choices ! ");
				}
			} while(choice !=8);
		}
}
