package dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import entity.model.Course;
import entity.model.Enrollment;
import entity.model.Student;
import exception.CourseNotFoundException;
import exception.StudentNotFoundException;

public interface EnrollmentDao {
	public Student getStudent(Enrollment enrollment) throws StudentNotFoundException, FileNotFoundException, ClassNotFoundException, IOException;
	public Course getCourse(Enrollment enrollment) throws CourseNotFoundException, FileNotFoundException, ClassNotFoundException, IOException;
}
