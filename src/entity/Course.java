package entity;
/**
 *
 * @author Ng Wei Khang
 */

public class Course implements Comparable<Course>{
    private String courseCode;
    private String courseName;
    private int courseCreditHours;
    private int courseSession;


    public Course(String courseCode, String courseName, int courseCreditHours, int courseSession) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCreditHours = courseCreditHours;
        this.courseSession = courseSession;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCreditHours() {
        return courseCreditHours;
    }

    public void setCourseCreditHours(int courseCreditHours) {
        this.courseCreditHours = courseCreditHours;
    }

    public int getCourseSession() {
        return courseSession;
    }

    public void setCourseSession(int courseSession) {
        this.courseSession = courseSession;
    }

    @Override
    public int compareTo(Course c) {
    return courseCode.compareTo(c.courseCode);
    }

    @Override
    public String toString() {
        String tableFormat = "%-16s%-51s%-21s%-10s";
        StringBuilder stringBuild = new StringBuilder();
        stringBuild.append(String.format(tableFormat, courseCode, courseName, courseCreditHours,courseSession));
    
    return stringBuild.toString();
    }
}