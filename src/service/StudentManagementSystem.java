package service;

import models.Student;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class StudentManagementSystem {
    private Map<String, Student> students;
    private final String DATA_FILE = "students.dat";

    public StudentManagementSystem() {
        this.students = new HashMap<>();
        loadStudents();
    }

    public void addStudent(String studentId, String name, String email) {
        if (students.containsKey(studentId)) {
            System.out.println("Student with ID " + studentId + " already exists.");
            return;
        }
        Student student = new Student(studentId, name, email);
        students.put(studentId, student);
        saveStudents();
        System.out.println("Student added successfully.");
    }

    public void removeStudent(String studentId) {
        if (students.remove(studentId) != null) {
            saveStudents();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    public void updateStudentEmail(String studentId, String newEmail) {
        Student student = students.get(studentId);
        if (student != null) {
            student.setEmail(newEmail);
            saveStudents();
            System.out.println("Email updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void enrollCourseWithGrade(String studentId, String course, double grade) {
        Student student = students.get(studentId);
        if (student != null) {
            try {
                student.addCourseWithGrade(course, grade);
                saveStudents();
                System.out.println("Course added successfully with grade " + String.format("%.2f", grade) + ".");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public void dropCourse(String studentId, String course) {
        Student student = students.get(studentId);
        if (student != null) {
            if (student.getCourses().contains(course)) {
                student.removeCourse(course);
                saveStudents();
                System.out.println("Course removed successfully.");
            } else {
                System.out.println("Student is not enrolled in this course.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n========== ALL STUDENTS ==========");
        students.values().forEach(System.out::println);
        System.out.println("==================================\n");
    }

    public void searchByName(String name) {
        System.out.println("\n========== SEARCH RESULTS ==========");
        students.values().stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .forEach(System.out::println);
        System.out.println("====================================\n");
    }

    public void listTopStudentsByGPA() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n========== TOP STUDENTS BY GPA ==========");
        students.values().stream()
                .sorted((s1, s2) -> Double.compare(s2.calculateGPA(), s1.calculateGPA()))
                .forEach(s -> System.out.println(s.getName() + " - GPA: " + String.format("%.2f", s.calculateGPA())));
        System.out.println("========================================\n");
    }

    public void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadStudents() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            students = (Map<String, Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students: " + e.getMessage());
            students = new HashMap<>();
        }
    }
}