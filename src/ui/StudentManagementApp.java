package ui;

import service.StudentManagementSystem;
import models.Student;
import java.util.Scanner;

public class StudentManagementApp {
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine().trim();
                    system.addStudent(id, name, email);
                    break;

                case "2":
                    System.out.print("Enter Student ID to remove: ");
                    String removeId = scanner.nextLine().trim();
                    system.removeStudent(removeId);
                    break;

                case "3":
                    System.out.print("Enter Student ID: ");
                    String searchId = scanner.nextLine().trim();
                    Student student = system.getStudent(searchId);
                    if (student != null) {
                        System.out.println(student.getDetailedInfo());
                    } else {
                        System.out.println("Student not found.\n");
                    }
                    break;

                case "4":
                    System.out.print("Enter Student ID: ");
                    String updateId = scanner.nextLine().trim();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine().trim();
                    system.updateStudentEmail(updateId, newEmail);
                    break;

                case "5":
                    System.out.print("Enter Student ID: ");
                    String enrollId = scanner.nextLine().trim();
                    System.out.print("Enter Course Name: ");
                    String course = scanner.nextLine().trim();
                    System.out.print("Enter Grade (0-10.0): ");
                    try {
                        double grade = Double.parseDouble(scanner.nextLine().trim());
                        system.enrollCourseWithGrade(enrollId, course, grade);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid grade format. Please enter a number.\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage() + "\n");
                    }
                    break;

                case "6":
                    System.out.print("Enter Student ID: ");
                    String dropId = scanner.nextLine().trim();
                    System.out.print("Enter Course Name to drop: ");
                    String dropCourse = scanner.nextLine().trim();
                    system.dropCourse(dropId, dropCourse);
                    break;

                case "7":
                    system.listAllStudents();
                    break;

                case "8":
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine().trim();
                    system.searchByName(searchName);
                    break;

                case "9":
                    system.listTopStudentsByGPA();
                    break;

                case "10":
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n========== STUDENT MANAGEMENT SYSTEM ==========");
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. View Student Details");
        System.out.println("4. Update Email");
        System.out.println("5. Enroll in Course (with Grade)");
        System.out.println("6. Drop Course");
        System.out.println("7. List All Students");
        System.out.println("8. Search by Name");
        System.out.println("9. Top Students by GPA");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }
}