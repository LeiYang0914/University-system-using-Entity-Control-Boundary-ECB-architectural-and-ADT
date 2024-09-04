/**
 *
 * @author Soh 
 */

package entity;
import adt.SortedArrayList;

public class Programme implements Comparable<Programme>{
    private String programCode;
    private int year;
    private int semester;
    private String description;
    private SortedArrayList<Course> courses; // Use SortedArrayList to store courses

    public Programme() {
    }

    public Programme(String name, int year, int semester, String description) {
        this.programCode = name;
        this.year = year;
        this.semester = semester;
        this.description = description;
        this.courses = new SortedArrayList<>(); // Initialize the list of courses
    }
   
    public String getName() {
        return programCode;
    }

    public void setName(String name) {
        this.programCode = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addCourse(Course course) {
        courses.add(course); // Add a course to the list of courses
    }

    public boolean removeCourse(Course course) {
        return courses.remove(course); // Remove a course from the list of courses
    }

    public SortedArrayList<Course> getCourses() {
        return courses; // Get the list of courses
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Programme Code: ").append(programCode)
                     .append("\nDescription: ").append(description)
                     .append("\nYear: ").append(year)
                     .append("\nSemester: ").append(semester)
                     .append("\nCourses: \n");

        for (int i = 0; i < courses.getNumberOfEntries(); i++) {
            Course course = courses.getEntry(i);
            stringBuilder.append(" - ").append(course.getCourseCode())
                         .append(": ").append(course.getCourseName())
                         .append("\n");
        }

        return stringBuilder.toString();
    }

   public int compareTo(Programme o){
        if (o == null) {
          return 0;
        }
        Programme o2 = (Programme)o;
        for(int i=0; i< o2.programCode.length();i++){
            if(this.programCode.charAt(i) > o2.programCode.charAt(i) ){
                return 1;
            }else if(this.programCode.charAt(i) < o2.programCode.charAt(i)){
                return -1;
            }
        }
        return -1;
    }
}
