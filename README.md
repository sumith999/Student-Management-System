# Student Management System

A console-based Java application for managing student records with automatic GPA calculation based on course grades.

## Features

- **Add/Remove Students** - Manage student records with ID, name, and email
- **Enroll in Courses** - Add courses with grades on a 10-point scale
- **Grade Tracking** - Store and manage grades for each course
- **Automatic GPA Calculation** - GPA is calculated automatically from course grades
- **Search Students** - Find students by name or ID
- **View Rankings** - See top students ranked by GPA
- **Data Persistence** - Student data is saved and loads automatically

## Project Structure
```
src/
├── models/
│   └── Student.java
├── service/
│   └── StudentManagementSystem.java
└── ui/
    └── StudentManagementApp.java
```

## How to Run

1. Clone the repository
2. Open in IntelliJ IDEA
3. Run `StudentManagementApp.java`
4. Use the menu to manage students

## Technologies Used

- Java
- Object-Oriented Programming
- File Serialization (Data Persistence)
- Collections (HashMap, List)

## Example Usage
```
1. Add Student → ID: 101, Name: Sumith Poojary, Email: sumith@example.com
2. Enroll in Course → Course: Hibernate, Grade: 8.93
3. Enroll in Course → Course: Spring, Grade: 8.90
4. View Student Details → Displays GPA: 8.91 (automatically calculated)
5. Top Students by GPA → See ranking
```

## Future Enhancements

- Database integration (SQLite/MySQL)
- GUI with JavaFX or Swing
- Advanced reporting and statistics
- Student attendance tracking