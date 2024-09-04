/**
*
* @author Chan Li Yang 
* 
*/

package control;
import adt.*;
import boundary.CourseUI;
import entity.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TutorManager{

	private HashMapInterface<Integer, Tutor> tutorHashMap;
	private SortedArrayList<Course> courseList;
	private CourseManager CourseManager;
	private int tutorIdCounter = 1;

	public TutorManager(CourseManager CourseManager) {
	    this.CourseManager = CourseManager;
	    tutorHashMap = new HashMapImplementer<>();
	    courseList = CourseManager.getCourseList();
	    
	    // Add initial tutors with their associated courses
	    addInitialTutors();
	}

	private void addInitialTutors() {
	    SortedArrayList<String> course1 = new SortedArrayList<>();
	    course1.add("BAMS2614");
	    course1.add("BACS2063");
	    Tutor tutor1 = new Tutor(101, "Heng Ong Huat", "heng101@tarc.edu.my", "012-2345789", course1, 3, "Master");

	    SortedArrayList<String> course2 = new SortedArrayList<>();
	    course2.add("BACS2063");
	    course2.add("BACS3074");
	    Tutor tutor2 = new Tutor(102, "Kwan Wei Kien", "kwan102@tarc.edu.my", "017-9876543", course2, 3, "Degree");

	    SortedArrayList<String> course3 = new SortedArrayList<>();
	    course3.add("BACS3074");
	    course3.add("BMMS2074");
	    course3.add("BACS3183");
	    Tutor tutor3 = new Tutor(103, "Chin Zi Quan", "chin103@tarc.edu.my", "016-3272949", course3, 1, "Master");

	    SortedArrayList<String> course4 = new SortedArrayList<>();
	    course4.add("BMMS2074");
	    Tutor tutor4 = new Tutor(104, "Hoo Sye Qi", "hoo104@tarc.edu.my", "010-8936816", course4, 2, "Phd");

	    SortedArrayList<String> course5 = new SortedArrayList<>();
	    course5.add("BACS3183");
	    Tutor tutor5 = new Tutor(105, "Goh Ge Jing", "goh105@tarc.edu.my", "014-5250428", course5, 1, "Phd");

	    tutorHashMap.put(tutor1.getId(), tutor1);
	    tutorHashMap.put(tutor2.getId(), tutor2);
	    tutorHashMap.put(tutor3.getId(), tutor3);
	    tutorHashMap.put(tutor4.getId(), tutor4);
	    tutorHashMap.put(tutor5.getId(), tutor5);
	}

	public void addTutor() {
	    char confirmation = 'y';

	    do {
	    	System.out.println("===============================");
	        System.out.println("+      Add a New Tutor:       +");
	        System.out.println("===============================");

	        // Gather tutor details from user input
	        try {
	            int tutorId;

	            // Validate tutor ID
	            do {
	                tutorId = inputTutorId();
	                if (tutorHashMap.containsKey(tutorId)) {
	                    System.out.println("Tutor with ID " + tutorId + " already exists. Please choose a different ID (tutorID after)"+tutorId+".");
	                }
	            } while (tutorHashMap.containsKey(tutorId));
	            
	            String name = inputName();
	            String email = inputEmail();
	            String phoneNumber = inputPhoneNumber();
	            SortedArrayList<String> courseTaught =addCoursesToTutor();
	            int semester = inputSemester();
	            String degreeTitle = inputDegreeTitle();

	            // Create a new tutor
	            Tutor newTutor = new Tutor(tutorId, name, email, phoneNumber, courseTaught, semester, degreeTitle);

	            // Ask for confirmation
	            do {
	            	System.out.println("");
	                System.out.print("Are you sure you want to add this tutor? (Y/N): ");
	                confirmation = new Scanner(System.in).next().charAt(0);
	            } while (confirmation != 'N' && confirmation != 'Y' && confirmation != 'n' && confirmation != 'y');

	            if (confirmation == 'Y' || confirmation == 'y') {
	                // Add the tutor to the hashmap
	                tutorHashMap.put(newTutor.getId(), newTutor);
	                System.out.println("New tutor has been successfully added!\n");
	            } else {
	                System.out.println("Tutor not added. Thank you!\n");
	            }

	            // Ask if the user wants to add another tutor
	            do {
	                System.out.print("Do you want to add another tutor? (Y/N): ");
	                confirmation = new Scanner(System.in).next().charAt(0);
	            } while (confirmation != 'N' && confirmation != 'Y' && confirmation != 'n' && confirmation != 'y');
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input. Please enter valid data.");
	        } catch (Exception e) {
	            System.out.println("An error occurred: " + e.getMessage());
	        }
	    } while (confirmation == 'Y' || confirmation == 'y');
	}



	public void displayTutor() {
	    boolean hasRecords = false; // Flag to check if there are records

	    for (int i = 0; i < tutorHashMap.capacity(); i++) {
	        Integer tutorId = tutorHashMap.getKey(i);

	        if (tutorId != null) {
	            Tutor tutor = tutorHashMap.getValue(tutorId);
	            System.out.println(tutor.toString());
	            hasRecords = true; // Set the flag to true as there are records
	        }
	    }

	    if (!hasRecords) {
	        System.out.println("No records found.");
	    }
	}


	public void deleteTutor() {
		//show the tutor records to user
		displayTutor();
		System.out.println("\nEnter Tutor ID to delete: ");
		int tutorIdToDelete = new Scanner(System.in).nextInt();

		if (tutorHashMap.containsKey(tutorIdToDelete)) {
			System.out.println("\n"+tutorHashMap.getValue(tutorIdToDelete));
			char confirmation;
			do {
				System.out.println("Are you sure you want to delete this tutor? (Y/N): ");
				confirmation = new Scanner(System.in).next().charAt(0);
			} while (confirmation != 'N' && confirmation != 'Y' && confirmation != 'n' && confirmation != 'y');

			if (confirmation == 'Y' || confirmation == 'y') {
				tutorHashMap.remove(tutorIdToDelete);
				
				System.out.println("Tutor has been successfully removed!");
				
				//display the tutor records again to check whether the tutor has been successfully deleted
				displayTutor();
			}
		} else {
			System.out.println("Tutor not found.");
		}
	}

	public void editTutor() {
	    System.out.println("\nEditing tutor details:");
	    Scanner scanner = new Scanner(System.in);

	    displayTutor();
	    System.out.println("");
	    System.out.print("Enter tutor ID to edit: ");
	    int tutorIdToEdit = scanner.nextInt();
	    scanner.nextLine(); // Consume newline

	    if (tutorHashMap.containsKey(tutorIdToEdit)) {
	        Tutor tutorToEdit = tutorHashMap.getValue(tutorIdToEdit);

	        String name = inputName();
	        String email = inputEmail();
	        String phoneNumber = inputPhoneNumber();
	        SortedArrayList<String> courseTaught = editCoursesToTutor(tutorToEdit); // Pass the tutor object
	        int semester = inputSemester();
	        String degreeTitle = inputDegreeTitle();

	        Tutor updatedTutor = new Tutor(tutorIdToEdit, name, email, phoneNumber, courseTaught, semester, degreeTitle);
	        tutorHashMap.put(tutorIdToEdit, updatedTutor);

	        System.out.println("Tutor details updated successfully.");
	    } else {
	        System.out.println("Tutor with ID " + tutorIdToEdit + " not found.");
	    }
	}


	public void search() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("");
	    System.out.println("==================================");
	    System.out.println("+     Search for a Tutor         +");
	    System.out.println("==================================");
	    System.out.println("+ Search Options:                +");
	    System.out.println("+   1. Search by Tutor ID        +");
	    System.out.println("+   2. Search by Semester        +");
	    System.out.println("+   3. Search by Course          +");
        System.out.println("+   4. Back to Main Menu         +");
	    System.out.println("==================================");
	    System.out.println("Enter your choice (1/2/3/4): ");
	    
	    int choice = scanner.nextInt();
	    
	    switch (choice) {
	        case 1:
	            System.out.print("Enter Tutor ID to search: ");
	            int tutorId = scanner.nextInt();
	            searchByTutorId(tutorId);
	            break;
	        case 2:
	            System.out.print("Enter Semester to search: ");
	            int semester = scanner.nextInt();
	            searchBySemester(semester);
	            break;
	        case 3:
	            displayCourses(); // Display the course list
	            System.out.print("Enter the number of the course you want to search: ");
	            int courseChoice = scanner.nextInt();
	            if (courseChoice >= 1 && courseChoice <= courseList.getNumberOfEntries()) {
	                String course = courseList.getEntry(courseChoice - 1).getCourseCode();
	                searchByCourse(course);
	            } else {
	                System.out.println("Invalid choice!");
	            }
	            break;
            case 4:
                return; // Exit the report menu and return to the main menu
	        default:
	            System.out.println("Invalid choice!");
	            break;
	    }
	}

	public void displayCourses() {
	    System.out.println("Available Courses:");
	    for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
	        System.out.println(i + 1 + ". " + courseList.getEntry(i).getCourseCode() + " - " + courseList.getEntry(i).getCourseName());
	    }
	}

	public void searchByTutorId(int tutorId) {
	    // Search for tutors by tutorId and display the results
	    Tutor foundTutor = tutorHashMap.getValue(tutorId);

	    if (foundTutor != null) {
	    	System.out.println("");
	        System.out.println("Tutor found by Tutor ID:");
	        System.out.println(foundTutor.toString());
	    } else {
	        System.out.println("No tutors found by Tutor ID.");
	    }
	}


	public void searchBySemester(int semester) {
	    // Search for tutors by semester and display the results
	    SortedArrayList<Tutor> foundTutors = new SortedArrayList<>();
	    for (int i = 0; i < tutorHashMap.capacity(); i++) {
	        Integer tutorId = tutorHashMap.getKey(i);
	        if (tutorId != null) {
	            Tutor tutor = tutorHashMap.getValue(tutorId);
	            if (tutor.getSemester() == semester) {
	                foundTutors.add(tutor);
	            }
	        }
	    }

	    if (foundTutors.getNumberOfEntries() > 0) {
	        System.out.println("");
	        System.out.println("Tutors found by Tutor Semester:");
	        for (int i = 0; i < foundTutors.getNumberOfEntries(); i++) {
	            Tutor tutor = foundTutors.getEntry(i);
	            System.out.println(tutor.toString());
	        }
	    } else {
	        System.out.println("No tutors found by Semester.");
	    }
	}


	public void searchByCourse(String course) {
	    System.out.println("");
	    System.out.println("Searching for tutors teaching course: " + course);

	    // Search for tutors by subject and display the results
	    SortedArrayList<Tutor> foundTutors = new SortedArrayList<>();

	    for (int i = 0; i < tutorHashMap.capacity(); i++) {
	        Integer tutorId = tutorHashMap.getKey(i);
	        if (tutorId != null) {
	            Tutor tutor = tutorHashMap.getValue(tutorId);
	            if (tutor.teachesCourse(course)) {
	                foundTutors.add(tutor);
	            }
	        }
	    }

	    if (!foundTutors.isEmpty()) {
	    	System.out.println("");
	        System.out.println("Tutors found by Tutor Course:");
	        for (int i = 0; i < foundTutors.getNumberOfEntries(); i++) {
	            Tutor tutor = foundTutors.getEntry(i);
	            System.out.println(tutor.toString());
	        }
	    } else {
	        System.out.println("No tutors found by course.");
	    }

	}

	public void clearAll() {
		System.out.println("\nClearing all tutors:");
		tutorHashMap.clear();
		tutorIdCounter = 1;
		System.out.println("All tutors cleared.");
	}

	public void displayHeader() {
	    System.out.println("\n=======================================================================================================================");
	    System.out.println("+                                       Displaying all tutors:                                                        +");
	    System.out.println("=======================================================================================================================");
	    System.out.printf("%-8s %-22s %-30s %-15s %-20s %-10s %-20s%n", "ID", "Name", "Email", "Phone", "course", "Semester", "Degree");
	}
	
	public void getReport() {
	    Scanner scanner = new Scanner(System.in);

	    while (true) {
	        System.out.println("==========================================");
	        System.out.println("+      Select a report to generate       +");
	        System.out.println("==========================================");
	        System.out.println("       1. Tutor by Semester Report       +");
	        System.out.println("       2. Tutor by Course Report         +");
	        System.out.println("       3. Tutor by Degree Report         +");
	        System.out.println("       4. Back to Main Menu              +");
	        System.out.println("==========================================");
	        System.out.print("\nEnter your choice (1/2/3/4): ");

	        int choice;
	        try {
	            choice = scanner.nextInt();
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input! Please enter a valid number (1/2/3/4).");
	            scanner.nextLine(); // Consume the invalid input
	            continue; // Skip the rest of the loop and restart
	        }

	        switch (choice) {
	            case 1:
	                TutorBySemesterReport();
	                break;
	            case 2:
	                generateTutorBySubjectReport();
	                break;
	            case 3:
	                generateTutorByDegreeReport();
	                break;
	            case 4:
	                return; // Exit the report menu and return to the main menu
	            default:
	                System.out.println("Invalid choice! Please try again.");
	                System.out.println("");
	                break;
	        }
	    }
	}


	public void TutorBySemesterReport() {

		System.out.println("=============================================");
		System.out.println("+           Tutor by Semester Report        +");
		System.out.println("=============================================");

		for (int semester = 1; semester <= 3; semester++) {
			System.out.println("Semester " + semester + " Tutors:");
			for (int i = 0; i < tutorHashMap.capacity(); i++) {
				Integer tutorId = tutorHashMap.getKey(i);
				if (tutorId != null) {
					Tutor tutor = tutorHashMap.getValue(tutorId);
					if (tutor.getSemester() == semester) {
						System.out.println(tutor.toString());
					}
				}
			}
			System.out.println("=============================================");
		}


	    }

	public void generateTutorBySubjectReport() {
		System.out.println("=============================================");
		System.out.println("+            Tutor by Course Report         +");
		System.out.println("=============================================");

		SortedArrayList<String> allCourses = new SortedArrayList<>();

		// Collect all unique courses taught
		for (int i = 0; i < tutorHashMap.capacity(); i++) {
		    Integer tutorId = tutorHashMap.getKey(i);
		    if (tutorId != null) {
		        Tutor tutor = tutorHashMap.getValue(tutorId);
		        SortedArrayList<String> coursesTaught = tutor.getcoursesTaught();
		        
		        // Manually add each course to allCourses
		        for (int j = 0; j < coursesTaught.getNumberOfEntries(); j++) {
		            String course = coursesTaught.getEntry(j);
		            if (allCourses.contains(course) == -1) {
		                allCourses.add(course);
		            }
		        }
		    }
		}

		
		// Remove duplicates
		SortedArrayList<String> uniqueCourses = new SortedArrayList<>();
		for (int i = 0; i < allCourses.getNumberOfEntries(); i++) {
		    String course = allCourses.getEntry(i);
		    if (uniqueCourses.contains(course) == -1) {
		        uniqueCourses.add(course);
		    }
		}

		// Generate report for each course
		for (int i = 0; i < uniqueCourses.getNumberOfEntries(); i++) {
		    String course = uniqueCourses.getEntry(i);
		    System.out.println("Tutors teaching " + course + ":");
		    for (int j = 0; j < tutorHashMap.capacity(); j++) {
		        Integer tutorId = tutorHashMap.getKey(j);
		        if (tutorId != null) {
		            Tutor tutor = tutorHashMap.getValue(tutorId);
		            if (tutor.teachesCourse(course)) {
		                System.out.println(tutor.toString());
		            }
		        }
		    }
		    System.out.println("---------------------------------------------");
		}

	}

	public void generateTutorByDegreeReport() {
		System.out.println("=============================================");
		System.out.println("+            Tutor by Degree Report         +");
		System.out.println("=============================================");

		SortedArrayList<String> allDegrees = new SortedArrayList<>();

		// Collect all unique degrees
		for (int i = 0; i < tutorHashMap.capacity(); i++) {
			Integer tutorId = tutorHashMap.getKey(i);
			if (tutorId != null) {
				Tutor tutor = tutorHashMap.getValue(tutorId);
				allDegrees.add(tutor.getDegreeTitle());
			}
		}

		// Remove duplicates
		SortedArrayList<String> uniqueDegrees = new SortedArrayList<>();
		for (int i = 0; i < allDegrees.getNumberOfEntries(); i++) {
		    String degree = allDegrees.getEntry(i);
		    if (uniqueDegrees.contains(degree) == -1) {
		        uniqueDegrees.add(degree);
		    }
		}


		for (int i = 0; i < uniqueDegrees.getNumberOfEntries(); i++) {
			String degree = uniqueDegrees.getEntry(i);
			System.out.println("Tutors with " + degree + " degree:");
			for (int j = 0; j < tutorHashMap.capacity(); j++) {
				Integer tutorId = tutorHashMap.getKey(j);
				if (tutorId != null) {
					Tutor tutor = tutorHashMap.getValue(tutorId);
					if (tutor.getDegreeTitle().equals(degree)) {
						System.out.println(tutor.toString());
					}
				}
			}
			System.out.println("---------------------------------------------");
		}
	}
	    
	    
	    public int inputTutorId() {
	        int tutorId = 0;
	        boolean validInput = false;
	        Scanner scanner = new Scanner(System.in);

	        do {
	            System.out.print("Enter Tutor ID (3 digits): ");
	            String input = scanner.nextLine();

	            if (input.matches("\\d{3}")) {
	                tutorId = Integer.parseInt(input);
	                validInput = true;
	            } else {
	                System.out.println("Invalid input. Please enter a 3-digit integer.");
	            }
	        } while (!validInput);

	        return tutorId;
	    }



	    public String inputName() {
	        String name;
	        boolean validInput = false;
	        Scanner scanner = new Scanner(System.in);

	        do {
	            System.out.print("Enter Name: ");
	            name = scanner.nextLine().trim(); // Trim leading and trailing spaces
	            name = name.toLowerCase(); // Convert the entire name to lowercase

	            // Split the name by spaces and capitalize the first letter of each word
	            String[] words = name.split("\\s+");
	            StringBuilder formattedName = new StringBuilder();
	            for (String word : words) {
	                if (!word.isEmpty()) {
	                    formattedName.append(word.substring(0, 1).toUpperCase()); // Uppercase the first letter
	                    if (word.length() > 1) {
	                        formattedName.append(word.substring(1).toLowerCase()); // Lowercase the rest
	                    }
	                    formattedName.append(" "); // Add space between words
	                }
	            }
	            
	            name = formattedName.toString().trim(); // Remove trailing space

	            if (name.matches("^[A-Z][a-z]*( [A-Z][a-z]*)*$")) {
	                validInput = true;
	            } else {
	                System.out.println("Invalid input. Please enter a valid name with proper capitalization (e.g., John Doe).");
	            }
	        } while (!validInput);

	        return name;
	    }



	    public String inputEmail() {
	        String email;
	        boolean validInput = false;
	        Scanner scanner = new Scanner(System.in);

	        do {
	            System.out.print("Enter Email: ");
	            email = scanner.nextLine();

	            if (email.matches("^.+@tarc\\.edu\\.my$")) {
	                validInput = true;
	            } else {
	                System.out.println("Invalid input. Please enter a valid email in the format 'example@tarc.edu.my'.");
	            }
	        } while (!validInput);

	        return email;
	    }


	    public String inputPhoneNumber() {
	        String phoneNumber;
	        boolean validInput = false;
	        Scanner scanner = new Scanner(System.in);

	        do {
	            System.out.print("Enter Phone Number (e.g., 011-12345678 or 010-1234567): ");
	            phoneNumber = scanner.nextLine();

	            if (phoneNumber.matches("^(011|010|012|013|014|015|016|017|018|019)-\\d{7,8}$")) {
	                validInput = true;
	            } else {
	                System.out.println("Invalid input. Please enter a valid phone number in the format '011-12345678' or '010-1234567'.");
	            }
	        } while (!validInput);

	        return phoneNumber;
	    }


	    public SortedArrayList<String> addCoursesToTutor() {
	        SortedArrayList<String> selectedCourses = new SortedArrayList<>();
	        
	        System.out.println("");
	        
	        displayCourses();

	        int choice;
	        do {
	        	System.out.println("");
	            System.out.print("Enter the number of the course you teach (1-" + courseList.getNumberOfEntries() + "), or 0 to finish: ");
	            choice = new Scanner(System.in).nextInt();

	            if (choice >= 1 && choice <= courseList.getNumberOfEntries()) {
	                String courseCode = courseList.getEntry(choice - 1).getCourseCode();
	                if (selectedCourses.contains(courseCode) == -1) {
	                    selectedCourses.add(courseCode);
	                    System.out.println(courseCode + " added to your taught courses.");
	                } else {
	                    System.out.println("You have already selected this course.");
	                }
	            } else if (choice < 0 || choice > courseList.getNumberOfEntries()) {
	                System.out.println("Invalid input. Please enter a number between 1 and " + courseList.getNumberOfEntries() + " or 0 to finish.");
	            }
	        } while (choice != 0);

	        System.out.println("");
	        return selectedCourses;
	    }
	    
	    public SortedArrayList<String> editCoursesToTutor(Tutor tutor) {
	        SortedArrayList<String> selectedCourses = new SortedArrayList<>();

	        // Add the current courses taught by the tutor to selectedCourses
	        System.out.println("\nCurrent Courses Taken by Tutor:");
	        SortedArrayList<String> coursesTaught = tutor.getcoursesTaught();
	        for (int i = 0; i < coursesTaught.getNumberOfEntries(); i++) {
	            selectedCourses.add(coursesTaught.getEntry(i));
	            System.out.println(coursesTaught.getEntry(i));
	        }

	        System.out.println("\nAvailable Courses:");
	        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
	            System.out.println(i + 1 + ". " + courseList.getEntry(i).getCourseCode() + " - " + courseList.getEntry(i).getCourseName());
	        }

	        int choice;
	        do {
	            System.out.println("");
	            System.out.print("Enter the number of the course you teach (1-" + courseList.getNumberOfEntries() + "), or 0 to finish: ");
	            choice = new Scanner(System.in).nextInt();

	            if (choice >= 1 && choice <= courseList.getNumberOfEntries()) {
	                String courseCode = courseList.getEntry(choice - 1).getCourseCode();
	                if (selectedCourses.contains(courseCode) == -1) {
	                    selectedCourses.add(courseCode);
	                    System.out.println(courseCode + " added to your taught courses.");
	                } else {
	                    System.out.println("You have already selected this course.");
	                }
	            } else if (choice < 0 || choice > courseList.getNumberOfEntries()) {
	                System.out.println("Invalid input. Please enter a number between 1 and " + courseList.getNumberOfEntries() + " or 0 to finish.");
	            }
	        } while (choice != 0);

	        System.out.println("");
	        return selectedCourses;
	    }

	    public int inputSemester() {
	        int semester=0;
	        boolean validInput = false;
	        Scanner scanner = new Scanner(System.in);

	        do {
	            System.out.print("Enter Semester (1-3): ");
	            if (scanner.hasNextInt()) {
	                semester = scanner.nextInt();
	                if (semester >= 1 && semester <= 3) {
	                    validInput = true;
	                } else {
	                    System.out.println("Invalid input. Please enter a semester between 1 and 3.");
	                }
	            } else {
	                System.out.println("Invalid input. Please enter a valid integer between 1 and 3.");
	                scanner.next(); // Consume invalid input
	            }
	        } while (!validInput);

	        return semester;
	    }


	    public String inputDegreeTitle() {
	        String degreeTitle;
	        boolean validInput = false;
	        Scanner scanner = new Scanner(System.in);

	        do {
	            System.out.print("Enter Degree Title (Master/PhD/Degree): ");
	            degreeTitle = scanner.nextLine().toUpperCase(); // Convert to uppercase 

	            if (degreeTitle.equals("MASTER") || degreeTitle.equals("PHD") || degreeTitle.equals("DEGREE")) {
	                validInput = true;
	            } else {
	                System.out.println("Invalid input. Please enter a valid degree title (Master/PhD/Degree).");
	            }
	        } while (!validInput);

	        return degreeTitle;
	    }
	    
	    public SortedArrayList<Course> getCourseList() {
	        return (SortedArrayList<Course>) courseList;
	    }
 
}



