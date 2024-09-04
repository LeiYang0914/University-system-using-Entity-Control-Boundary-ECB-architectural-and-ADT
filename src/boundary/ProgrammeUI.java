/**
 *
 * @author Soh 
 */

package boundary;

import java.util.Scanner;
import control.ProgrammeManager;

public class ProgrammeUI {
    private ProgrammeManager ProgrammeManager;


    // Modify the constructor to accept CourseManager
    public ProgrammeUI(ProgrammeManager ProgrammeManager) {
        this.ProgrammeManager = ProgrammeManager;
    }


    public void programmeMaintenance() {
        int choice;
        boolean validInput;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("==============================");
            System.out.println("+   Programme Management     +");
            System.out.println("==============================");
            System.out.println("+     1. Add Programme       +");
            System.out.println("+     2. Remove Programme    +");
            System.out.println("+     3. Find Programme      +");
            System.out.println("+     4. Amend Programme     +");
            System.out.println("+     5. List All Programmes +");
            System.out.println("+     6. Report ");
            System.out.println("+     0. Quit                +");
            System.out.println("==============================");
            System.out.print("\n Enter choice: ");

            validInput = false;

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (choice >= 0 && choice <= 6) {
                    validInput = true;
                }
            } else {
                scanner.nextLine(); // Consume the invalid input
                choice = -1; // Set choice to an invalid value
            }

            if (validInput) {
                switch (choice) {
                    case 0:
                        ProgrammeManager.exitMessage();
                        return;
                    case 1:
                        ProgrammeManager.addProgramme();
                        System.out.println("Programme added successfully.");
                        break;
                    case 2:
                        ProgrammeManager.removeProgramme();
                        break;
                    case 3:
                       ProgrammeManager.findProgramme();
                        break;
                    case 4:
                        ProgrammeManager.amendProgrammeDetails();
                        break;
                    case 5:
                        ProgrammeManager.listProgrammes();
                        break;
                    case 6:
                        ProgrammeManager.generateReport();
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid option (0-5).");
            }
        } while (choice != 0);
    }
}
