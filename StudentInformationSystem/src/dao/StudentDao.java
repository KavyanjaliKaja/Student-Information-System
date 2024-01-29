package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import entity.model.Course;
import entity.model.Payment;
import entity.model.Student;
import exception.DuplicateEnrollmentException;
import exception.StudentNotFoundException;

public interface StudentDao {
	 public void enrollInCourse(Student student, Course course) throws DuplicateEnrollmentException;
	 public void updateStudentInfo(Student student, String firstName, String lastName, Date dateOfBirth,
	            String email, long phoneNumber);
	 public void makePayment(Student student, double amount, Date paymentDate1);
	 public void displayStudentInfo(Student student) ;
	 public List<Course> getEnrolledCourses(Student student);
	 public List<Payment> getPaymentHistory(Student student) throws FileNotFoundException, ClassNotFoundException, IOException, StudentNotFoundException;
}
