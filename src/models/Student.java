package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private String name;
    private String email;
    private LocalDate enrollmentDate;
    private Map<String, Double> courseGrades;

    public Student(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.enrollmentDate = LocalDate.now();
        this.courseGrades = new HashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addCourseWithGrade(String course, double grade) {
        if (grade < 0 || grade > 10.0) {
            throw new IllegalArgumentException("Grade must be between 0 and 10.0");
        }
        courseGrades.put(course, grade);
    }

    public void removeCourse(String course) {
        courseGrades.remove(course);
    }

    public double getGradeForCourse(String course) {
        return courseGrades.getOrDefault(course, -1.0);
    }

    public Set<String> getCourses() {
        return courseGrades.keySet();
    }

    public double calculateGPA() {
        if (courseGrades.isEmpty()) {
            return 0.0;
        }
        double sum = courseGrades.values().stream().mapToDouble(Double::doubleValue).sum();
        return Math.round((sum / courseGrades.size()) * 100.0) / 100.0;
    }

    public int getTotalCourses() {
        return courseGrades.size();
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Email: %s | GPA: %.2f | Courses: %d | Enrolled: %s",
                studentId, name, email, calculateGPA(), getTotalCourses(), enrollmentDate);
    }

    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== STUDENT DETAILS ==========\n");
        sb.append("ID: ").append(studentId).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Enrollment Date: ").append(enrollmentDate).append("\n");
        sb.append("Current GPA: ").append(String.format("%.2f", calculateGPA())).append("\n");

        if (courseGrades.isEmpty()) {
            sb.append("Courses: No courses enrolled\n");
        } else {
            sb.append("Courses:\n");
            courseGrades.forEach((course, grade) ->
                    sb.append("  - ").append(course).append(": ").append(String.format("%.2f", grade)).append("\n")
            );
        }
        sb.append("====================================\n");
        return sb.toString();
    }
}