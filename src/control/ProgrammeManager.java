/**
 *
 * @author Soh 
 */

package control;

import java.util.Scanner;

import adt.SortedArrayListInterface;
import adt.SetArrayList;
import adt.SetArrayListInterface;
import adt.SortedArrayList;
import entity.Course;
import entity.Programme;

public class ProgrammeManager {
	private SetArrayListInterface<Programme> programmeList = new SetArrayList<>();
	private SortedArrayListInterface<Course> courseList = new SortedArrayList<>();
	private CourseManager CourseManager;
	Scanner scan = new Scanner(System.in);

	public ProgrammeManager(CourseManager CourseManager) {
		initializeRecords();
		this.CourseManager = CourseManager; // Initialize CourseManager
		courseList = CourseManager.getCourseList();
	}

	public void initializeRecords() {
		Programme programme1 = new Programme("RDS", 1, 1, "Bachelor of Data Science");
		Programme programme2 = new Programme("RIT", 2, 1, "Bachelor of Internet Technology");
		Programme programme3 = new Programme("RMM", 3, 1, "Bachelor of Mathematical Computing");
		Programme programme4 = new Programme("RCS", 1, 2, "Bachelor of Computer Science");
		Programme programme5 = new Programme("RIS", 2, 2, "Bachelor of Information Security");

		Course course1 = new Course("BAMS2614","MATH AND GAME TECHNOLOGY", 3 , 1);
		Course course2 = new Course("BACS2063","DATA STRUCTURE AND ALGORITHM", 4 , 1);
		Course course3 = new Course("BACS3074","ARTIFICIAL INTELLIGENCE", 3 , 2);
		Course course4 = new Course("BMMS2074","STATISTIC FOR DATA SCIENCE", 4 , 2);
		Course course5 = new Course("BACS3183","ADVANCED DATBASE MANAGEMENT", 4 , 3);

		programme1.addCourse(course1);
		programme1.addCourse(course2);
		programme1.addCourse(course3);

		programme2.addCourse(course4);
		programme2.addCourse(course5);

		programme3.addCourse(course2);
		programme3.addCourse(course5);

		programme4.addCourse(course1);
		programme4.addCourse(course4);

		programme5.addCourse(course3);
		programme5.addCourse(course5);

		programmeList.add(programme1);
		programmeList.add(programme2);
		programmeList.add(programme3);
		programmeList.add(programme4);
		programmeList.add(programme5);
	}


	public void addProgramme() {
	    String programmeCode = getProgrammeCode();
	    int programmeYear = getProgrammeYear();
	    int programmeSemester = getProgrammeSemester();
	    String description = getProgrammeDescription();

	    Programme newProgramme = new Programme(programmeCode, programmeYear, programmeSemester, description);

	    // Get the list of predefined courses from CourseManager
	    SortedArrayList<Course> predefinedCourses = CourseManager.getCourseList();

	    // Display the list of predefined courses
	    System.out.println("Available Courses:");
	    for (int i = 0; i < predefinedCourses.getNumberOfEntries(); i++) {
	        System.out.println((i + 1) + ". " + predefinedCourses.getEntry(i).getCourseCode() + " - " + predefinedCourses.getEntry(i).getCourseName());
	    }

	    // Ask the user to choose courses
	    System.out.print("Enter the indexes of courses to add (comma-separated): ");
	    String[] chosenIndexes = scan.nextLine().split(",");
	    for (String indexStr : chosenIndexes) {
	        int index = Integer.parseInt(indexStr.trim()) - 1;
	        if (index >= 0 && index < predefinedCourses.getNumberOfEntries()) {
	            Course chosenCourse = predefinedCourses.getEntry(index);
	            newProgramme.addCourse(chosenCourse);
	        } else {
	            System.out.println("Invalid index: " + (index + 1));
	        }
	    }

	    programmeList.add(newProgramme);

	    System.out.println("Programme Added!");
	}


	public void removeProgramme() {
		listProgrammes();

		System.out.print("Enter the programme to remove (e.g. 1): ");
		int choice = scan.nextInt();
		scan.nextLine();

		programmeList.remove(programmeList.getEntry(choice));
		System.out.println("The chosen programme has been successfully removed!");
	}

	public void findProgramme() {
		System.out.print("Enter programme code to find: ");
		String programCodeToFind = scan.nextLine().toUpperCase();

		int position = 0;
		String outputStr = "";

		for (int i = 1; i <= programmeList.getNumberOfElements(); i++) {
			if (programmeList.getEntry(i).getName().equals(programCodeToFind)) {
				position = i;
				break;
			}
		}

		outputStr += programmeList.getEntry(position);
		System.out.println(outputStr);
	}
        
public void generateReport() {
    System.out.println("================================================================================");
    System.out.println("+                               Programme Report                               +");
    System.out.println("================================================================================");

    // Check if there are any programs to generate a report
    if (programmeList.isEmpty()) {
        System.out.println("No programs to report.");
        return;
    }

    System.out.printf("%-15s %-10s %-10s %-30s%n", "Program Code", "Year", "Semester", "Description");
    System.out.println("================================================================================");

    for (int i = 1; i <= programmeList.getNumberOfElements(); i++) {
        Programme programme = programmeList.getEntry(i);
        if (programme != null) {
            System.out.printf("%-15s %-10d %-10d %-30s%n",
                    programme.getName(), programme.getYear(),
                    programme.getSemester(), programme.getDescription());
        } else {
            System.out.println("Programme object is null.");
        }
    }

    System.out.println("================================================================================");
}




	public void amendProgrammeDetails() {
		listProgrammes(); // Display all programmes
		do {
	        System.out.print("Enter programme code to amend: ");
	        String programCodeToAmend = scan.nextLine().toUpperCase();

	        int position = -1;

	        // Find the index of the programme to amend
	        for (int i = 1; i <= programmeList.getNumberOfElements(); i++) {
	            if (programmeList.getEntry(i).getName().equals(programCodeToAmend)) {
	                position = i;
	                break;
	            }
	        }

	        if (position != -1) {
	            Programme selectedProgramme = programmeList.getEntry(position);

	            // Display available courses
	            SortedArrayList<Course> availableCourses = new SortedArrayList<>();
	            for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
	                if (selectedProgramme.getCourses().contains(courseList.getEntry(i)) == -1) {
	                    availableCourses.add(courseList.getEntry(i));
	                }
	            }

	            if (availableCourses.isEmpty()) {
	                System.out.println("No available courses to add.");
	                return;
	            }

	            System.out.println("Available Courses:");
	            for (int i = 0; i < availableCourses.getNumberOfEntries(); i++) {
	                System.out.println((i + 1) + ". " + availableCourses.getEntry(i));
	            }

	            // Ask the user to choose courses
	            System.out.print("Enter the indexes of courses to add (comma-separated): ");
	            String[] chosenIndexes = scan.nextLine().split(",");
	            for (String indexStr : chosenIndexes) {
	                int index = Integer.parseInt(indexStr.trim()) - 1;
	                if (index >= 0 && index < availableCourses.getNumberOfEntries()) {
	                    Course chosenCourse = availableCourses.getEntry(index);

	                    // Check if the course is already in the programme
	                    if (selectedProgramme.getCourses().contains(chosenCourse) == -1) {
	                        selectedProgramme.addCourse(chosenCourse);
	                        System.out.println("Course " + chosenCourse.getCourseCode() + " added to programme.");
	                    } else {
	                        System.out.println("Course " + chosenCourse.getCourseCode() + " is already in the programme.");
	                    }
	                } else {
	                    System.out.println("Invalid index: " + (index + 1));
	                }
	            }

	            String name = getProgrammeCode();
	            int year = getProgrammeYear();
	            int semester = getProgrammeSemester();
	            String ProgramDesc = getProgrammeDescription();

	            // Update programme details
	            selectedProgramme.setName(name);
	            selectedProgramme.setYear(year);
	            selectedProgramme.setSemester(semester);
	            selectedProgramme.setDescription(ProgramDesc);

	            System.out.println("Programme details updated successfully!");
	            break; // Exit the loop if successful
	        } else {
	            System.out.println("Programme not found. Please try again.");
	        }
	    } while (true); // Keep looping until a valid program code is provided
	}


	
	// Function to get a valid programme code (cannot contain integers)
	public String getProgrammeCode() {
	    String code;
	    boolean validInput = false;

	    do {
	        System.out.print("Enter programme code (cannot contain integers): ");
	        code = scan.nextLine().toUpperCase();

	        if (code.matches(".*\\d.*")) {
	            System.out.println("Invalid input. Programme code cannot contain integers.");
	        } else {
	            validInput = true;
	        }
	    } while (!validInput);

	    return code;
	}

	// Function to get a valid programme year (1-3)
	public int getProgrammeYear() {
	    int year = 0;

	    do {
	        System.out.print("Enter programme year (1-3): ");
	        if (scan.hasNextInt()) {
	            year = scan.nextInt();
	            scan.nextLine(); // Consume the newline character

	            if (year < 1 || year > 3) {
	                System.out.println("Invalid input. Please enter a value between 1 and 3.");
	            }
	        } else {
	            scan.nextLine(); // Consume the invalid input
	            System.out.println("Invalid input. Please enter a valid integer value.");
	        }
	    } while (year < 1 || year > 3);

	    return year;
	}

	// Function to get a valid programme semester (1-3)
	public int getProgrammeSemester() {
	    int semester = 0;

	    do {
	        System.out.print("Enter programme semester (1-3): ");
	        if (scan.hasNextInt()) {
	            semester = scan.nextInt();
	            scan.nextLine(); // Consume the newline character

	            if (semester < 1 || semester > 3) {
	                System.out.println("Invalid input. Please enter a value between 1 and 3.");
	            }
	        } else {
	            scan.nextLine(); // Consume the invalid input
	            System.out.println("Invalid input. Please enter a valid integer value.");
	        }
	    } while (semester < 1 || semester > 3);

	    return semester;
	}

	// Function to get programme description
	public String getProgrammeDescription() {
	    System.out.print("Enter programme description: ");
	    return scan.nextLine().toUpperCase();
	}




	public void listProgrammes() {
		String outputStr = "";
		for (int i = 1; i <= programmeList.getNumberOfElements(); i++) {
			String num = String.format("%d. ", i);
			outputStr += num + programmeList.getEntry(i) + "\n";
		}
		System.out.println(outputStr);
	}

	public int back() {
		System.out.println("Press 0 to go back..");
		int back = scan.nextInt();
		scan.nextLine();
		return back;
	}

	public void exitMessage() {
		System.out.println("\nYou have leave the Programme Menu!\n");
	}
}

