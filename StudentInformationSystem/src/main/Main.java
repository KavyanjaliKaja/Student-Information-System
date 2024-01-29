package main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.CourseDaoImpl;
import dao.SISDaoImpl;
import dao.TeacherDaoImpl;
import entity.model.Course;
import entity.model.Student;
import entity.model.Teacher;
import exception.CourseNotFoundException;
import exception.DuplicateEnrollmentException;
import exception.InvalidStudentDataException;
import exception.InvalidTeacherDataException;
import exception.PaymentValidationException;
import exception.StudentNotFoundException;
import exception.TeacherNotFoundException;

public class Main {

    public static void main(String[] args) throws ParseException, FileNotFoundException, ClassNotFoundException, DuplicateEnrollmentException, IOException, TeacherNotFoundException, CourseNotFoundException, SQLException, InvalidStudentDataException, InvalidTeacherDataException, StudentNotFoundException, PaymentValidationException {
        Scanner sc = new Scanner(System.in);

        int choice;
        SISDaoImpl sdi = new SISDaoImpl();
        System.out.println("Hi welcome to Student Info System");
        do {
            
            System.out.println("Please tell us what would you like to do:");
            System.out.println("1. Enroll a new Student");
            System.out.println("2. Enroll a student into a course");
            System.out.println("3. Enroll a new Teacher");
            System.out.println("4. Assign teacher to a course");
            System.out.println("5. Record a payment");
            System.out.println("6. Generate enrollment report");
            System.out.println("7. Exit from application");

            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    enrollNewStudent(sdi, sc);
                    break;

                case 2:
                    enrollStudentIntoCourse(sdi, sc);
                    break;

                case 3:
                    enrollNewTeacher(sdi, sc);
                    break;

                case 4:
                    assignTeacherToCourse(sdi, sc);
                    break;

                case 5:
                    recordPayment(sdi, sc);
                    break;

                case 6:
                    generateEnrollmentReport(sdi, sc);
                    break;

                case 7:
                    System.out.println("You are exiting the application...");
                    System.out.println("Exited ");
                    break;

                default:
                    System.out.println("Wrong input! Please enter a correct number in the given range ");
            }
        } while (choice != 7);

        sc.close();
    }

    private static void enrollNewStudent(SISDaoImpl sdi, Scanner sc) throws ParseException {
        System.out.println("Student details to insert into Students table");
        System.out.println("Enter your firstname:");
        String firstName = sc.nextLine();
        System.out.println("Enter your lastname:");
        String lastName = sc.nextLine();
        System.out.println("Enter your d-o-b:");
        String dob = sc.next();
        System.out.println("Enter your email:");
        String email = sc.next();
        System.out.println("Enter your phone number:");
        long phoneNumber = sc.nextLong();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = sdf.parse(dob);
        Student student = new Student(firstName, lastName, dateOfBirth, email, phoneNumber);
        try {
            sdi.insertStudent(student);
        } catch (DuplicateEnrollmentException | IOException | InvalidStudentDataException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void enrollStudentIntoCourse(SISDaoImpl sdi, Scanner sc) throws FileNotFoundException, ClassNotFoundException, IOException {
        System.out.println("Enter Student ID to enroll in some courses:");
        int studentID = sc.nextInt();
        
        Student student2 = new Student(studentID);

        System.out.println("Enter the number of courses you want to enroll:");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            try {
                System.out.println("Enter the name of the course:");
                String courseName = sc.nextLine();
                Course course = new Course(courseName);
                CourseDaoImpl obj = new CourseDaoImpl();
                long courseId;
				
					courseId = obj.getCourseIdByCourseName(course);
				
                Course finalCourse = new Course(courseId, courseName);
                sdi.EnrollStudentInCourse(student2, finalCourse);
            } catch (StudentNotFoundException | DuplicateEnrollmentException | CourseNotFoundException|ClassNotFoundException | IOException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private static void enrollNewTeacher(SISDaoImpl sdi, Scanner sc) {
        System.out.println("Enter Teacher details to insert into Teacher table");
        System.out.println("Enter teacher firstname:");
        String teacherFirstName = sc.nextLine();
        System.out.println("Enter teacher lastname:");
        String teacherLastName = sc.nextLine();
        System.out.println("Enter teacher email:");
        String teacherEmail = sc.nextLine();

        Teacher teacher = new Teacher(teacherFirstName, teacherLastName, teacherEmail);
        try {
            sdi.insertTeacher(teacher);
        } catch (DuplicateEnrollmentException | InvalidTeacherDataException | ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void assignTeacherToCourse(SISDaoImpl sdi, Scanner sc) {
        System.out.println("Enter teacher email:");
        String emailForAssign = sc.nextLine();
        Teacher teacherEmailForAssign = new Teacher(emailForAssign);
        System.out.println("Enter the course name to be assigned:");
        String courseName1 = sc.nextLine();
        Course course = new Course(courseName1);
        CourseDaoImpl obj = new CourseDaoImpl();

        try {
            long courseId = obj.getCourseIdByCourseName(course);
            Course finalCourse = new Course(courseId, courseName1, 5);
            TeacherDaoImpl tce = new TeacherDaoImpl();
            long teachId = tce.getTeacherIdByEmail(teacherEmailForAssign);
            Teacher finalTeacher = new Teacher(teachId);
            sdi.AssignTeacherToCourse(finalTeacher, finalCourse);
        } catch (IOException | TeacherNotFoundException | CourseNotFoundException | DuplicateEnrollmentException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void recordPayment(SISDaoImpl sdi, Scanner sc) {
        System.out.println("Making Payment for Enrolled Courses by the Students");
        try {
            System.out.println("Enter student Id:");
            long studentId = sc.nextLong();
            System.out.println("Enter the amount:");
            double amount = sc.nextDouble();
            Student paymentStudent = new Student(studentId);
            sdi.recordPayment(paymentStudent, amount, new Date());
        } catch (StudentNotFoundException | PaymentValidationException | ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void generateEnrollmentReport(SISDaoImpl sdi, Scanner sc) {
        System.out.println("Enrollment Report");
        try {
            System.out.println("Enter Course Name:");
            String courseName2 = sc.nextLine();
            Course course5 = new Course(courseName2);
            CourseDaoImpl cce = new CourseDaoImpl();
            long courseId1 = cce.getCourseIdByCourseName(course5);
            Course enrollmentReportForCourse = new Course(courseId1);
            List<String> studentNames = sdi.CalculateCourseStatistics(enrollmentReportForCourse);
            System.out.println("Students enrolled in this course are:");
            for (String studentName : studentNames) {
                System.out.println(studentName);
            }
        } catch (CourseNotFoundException | ClassNotFoundException | IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
