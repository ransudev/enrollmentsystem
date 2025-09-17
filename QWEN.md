# Project Context for ESystem (Enrollment System)

## Project Overview
This is a Java-based Enrollment System application with a graphical user interface built using Swing. The system allows for student enrollment management with database connectivity to MySQL. It includes functionality for managing students, teachers, subjects, and grades.

## Technology Stack
- **Language**: Java 18
- **Build Tool**: Maven
- **Database**: MySQL
- **GUI Framework**: Swing
- **Database Driver**: MySQL Connector/J 8.0.30
- **IDE**: NetBeans (inferred from project structure)

## Project Structure
```
ESystem/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── mycompany/
│   │               └── esystem/
│   │                   ├── MaisoEnrollmentSystem.java (Main class)
│   │                   ├── Login.java (Login form)
│   │                   ├── Students.java (Student model)
│   │                   ├── StudentsForm.java (Student management UI)
│   │                   ├── Teacher.java (Teacher model)
│   │                   ├── TeachersForm.java (Teacher management UI)
│   │                   ├── Subjects.java (Subject model)
│   │                   ├── SubjectsForm.java (Subject management UI)
│   │                   ├── GradesForm.java (Grades management UI)
│   │                   └── Enrolled.java (Enrollment model)
│   └── test/
├── target/
└── pom.xml (Maven configuration)
```

## Key Components

### Main Application Class
- **MaisoEnrollmentSystem.java**: Entry point of the application that initializes the login form and manages database connections.

### Authentication
- **Login.java**: Handles user authentication with username/password validation against MySQL database.

### Data Models
- **Students.java**: Student data model
- **Teacher.java**: Teacher data model
- **Subjects.java**: Subject data model
- **Enrolled.java**: Enrollment data model

### UI Forms
- **StudentsForm.java**: GUI for managing student records
- **TeachersForm.java**: GUI for managing teacher records
- **SubjectsForm.java**: GUI for managing subject records
- **GradesForm.java**: GUI for managing grades
- **StudRegistration.java**: Student registration form

## Database Configuration
The application connects to a MySQL database with the following configuration:
- **Host**: 192.168.64.3 (local network)
- **Database Name**: 1st_SY2025_2026 (configurable)
- **Username**: root
- **Password**: maiso

## Building and Running

### Prerequisites
- Java 18 JDK
- Maven
- MySQL Server running on 192.168.64.3:3306
- MySQL database user with appropriate permissions

### Build Commands
```bash
# Compile the project
mvn compile

# Package the project into a JAR
mvn package

# Run the application
mvn exec:java
```

### Manual Execution
```bash
# Compile Java files
javac -d target src/main/java/com/mycompany/esystem/*.java

# Run the main class
java -cp target com.mycompany.esystem.MaisoEnrollmentSystem
```

## Development Guidelines

### Code Style
- Follow standard Java naming conventions
- Use meaningful variable and method names
- Comment complex logic
- Maintain consistent indentation

### Adding New Features
**IMPORTANT**: Only modify existing classes. Do not create new classes unless specifically instructed. If you believe a new class is needed, discuss it first before implementation.

1. Work within existing model classes to extend functionality
2. Modify existing form classes to add UI components
3. Update event handlers in existing forms
4. Extend database table functionality within current structures

### Database Schema
The application expects the following tables to exist in the database:
- Students table
- Teachers table
- Subjects table
- Enrolled table
- Grades table

## Common Tasks

### Modifying Existing Forms
1. Open the `.java` file in NetBeans
2. Use the visual designer to modify UI components
3. Update event handlers as needed
4. Test changes thoroughly

### Extending Data Models
1. Add new fields to existing model classes
2. Update corresponding database queries
3. Modify UI forms to accommodate new fields
4. Ensure backward compatibility

## Troubleshooting

### Database Connection Issues
- Verify MySQL server is running on 192.168.64.3:3306
- Check username/password credentials
- Ensure the database `1st_SY2025_2026` exists
- Confirm network connectivity to the database server

### Login Problems
- Verify database user permissions
- Check that the user exists in MySQL
- Confirm the password is correct